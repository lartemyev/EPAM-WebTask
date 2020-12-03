package lib;

import io.github.bonigarcia.wdm.WebDriverManager;
import lib.exceptions.UnknownBrowserException;
import lib.exceptions.UnknownPropertyException;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.io.*;
import java.util.Properties;

public class Configuration {
    private static final String PROPERTIES_FILE = "driver.properties";
    private static final String DRIVER_BASEURL = "driver.baseUrl";
    private static final String DRIVER_BROWSER = "driver.browser";

    private static Properties properties = new Properties();

    @Getter
    public String baseUrl;
    @Getter
    private final Browser browser;

    public Configuration() {
        properties = loadPropertiesFile();
        baseUrl = getPropertyOrException(DRIVER_BASEURL);
        browser = Browser.init(getPropertyOrException(DRIVER_BROWSER));
    }

    public WebDriver getDriver() {
        WebDriver driver;
        switch (browser) {
            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case CHROME:
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--headless");
                options.addArguments("--disable-gpu");
                driver = new ChromeDriver(options);
                break;
            default:
                throw new UnknownBrowserException("Cannot create driver for unknown browser type");
        }
        return driver;
    }

    private Properties loadPropertiesFile() {
        try {
            String filename = getPropertyOrNull(PROPERTIES_FILE);
            filename = filename == null? PROPERTIES_FILE :filename;

            InputStream stream = getClass().getClassLoader().getResourceAsStream(filename);
            stream = stream == null? new FileInputStream(new File(filename)) : stream;

            Properties result = new Properties();
            result.load(stream);
            return result;
        } catch (IOException e) {
            throw new UnknownPropertyException("Property file is not found");
        }
    }

    private static String getPropertyOrNull(String name) {
        return getProperty(name, false);
    }

    private static String getPropertyOrException(String name) { return getProperty(name, true); }

    private static String getProperty(String name, boolean forceExceptionIfNotDefined) {
        String result;
        if ((result = System.getProperty(name, null)) != null && result.length() > 0) {
            return result;
        } else if ((result = getPropertyFromPropertiesFile(name)) != null && result.length() > 0) {
            return result;
        } else if (forceExceptionIfNotDefined) {
            throw new UnknownPropertyException("Unknown property: [" + name + "]");
        }
        return result;
    }

    private static String getPropertyFromPropertiesFile(String name) {
        Object result = properties.get(name);
        return result == null? null : result.toString();
    }
}
