package lib;

import io.github.bonigarcia.wdm.WebDriverManager;
import lib.browser.BrowserType;
import lib.exceptions.UnknownBrowserException;
import lib.utils.Tools;
import lombok.Getter;
import net.thucydides.core.webdriver.DriverSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class Driver implements DriverSource {

    private static final String DRIVER_BASEURL = "driver.baseUrl";
    private static final String DRIVER_BROWSER = "driver.browser";

    @Getter
    public String baseUrl;
    @Getter
    private final BrowserType browser;

    private static Tools tools = new Tools();

    public Driver() {
        baseUrl = tools.getPropertyOrException(DRIVER_BASEURL);
        browser = BrowserType.getType(tools.getPropertyOrException(DRIVER_BROWSER));
    }

    private WebDriver driver;

    @Override
    public WebDriver newDriver() {
        if (driver == null) {
            switch (browser) {
                case FIREFOX:
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                case CHROME:
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;
                case CHROME_HEADLESS:
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--disable-infobars",
                            "--disable-notifications", "--disable-extensions",
                            "--disable-gpu", "enable-automation");
                    // --
                    driver = new ChromeDriver(options);
                    break;
                default:
                    throw new UnknownBrowserException("Cannot create driver for unknown browser type");
            }
            driver.manage().timeouts().pageLoadTimeout(30L, TimeUnit.SECONDS);
            driver.manage().timeouts().setScriptTimeout(5L, TimeUnit.SECONDS);
            driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
            driver.manage().window().maximize();
        }
        return driver;
    }

    @Override
    public boolean takesScreenshots() {
        return false;
    }
}
