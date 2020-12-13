package lib.browser;

import lib.BaseTest;
import lib.utils.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static lib.BaseTest.conf;

public class Browser {

    private static WebDriver driver = BaseTest.driver;

    public static void openBaseUrl() {
        driver.get(conf.getBaseUrl());
    }

    public static WebElement expectedFirstLink(String keyWord) {
        return driver.findElement(By.partialLinkText(keyWord));
    }

    public static String getPageTitle() {
        String returnResult = "";
        try {
            returnResult = driver.getTitle().toLowerCase();
            Log.print("Title of the window: " + returnResult);
        } catch (Exception e) {
            Log.print("Unable to navigate to the title of the window: " + e.toString());
        }
        return returnResult;
    }

    public static boolean isElementPresent(String xpath) {
        boolean returnResult = false;
        try
        {
            returnResult = !driver.findElements(By.xpath(xpath)).isEmpty();
        }
        catch (Exception e) {
            Log.print("Unable to find element by xpath: " + xpath);
        }
        return returnResult;
    }

    public static boolean isElementClickable(WebElement element) {
        return element.isDisplayed() && element.isEnabled();
    }
}
