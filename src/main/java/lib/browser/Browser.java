package lib.browser;

import lib.utils.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static base.BaseTest.conf;

public class Browser {

    private WebDriver driver = conf.newDriver();

    public void openBaseUrl() {
        driver.get(conf.getBaseUrl());
    }

    public WebElement expectedFirstLink(String keyWord) {
        return driver.findElement(By.partialLinkText(keyWord));
    }

    public String getPageTitle() {
        String returnResult = "";
        try {
            returnResult = driver.getTitle().toLowerCase();
            Log.print("Title of the window: " + returnResult);
        } catch (Exception e) {
            Log.print("Unable to navigate to the title of the window: " + e.toString());
        }
        return returnResult;
    }

    public boolean isElementPresent(String xpath) {
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
