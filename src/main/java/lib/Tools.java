package lib;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Tools {

    protected static WebDriver driver = BaseTest.driver;

    public static String getPageTitle(){
        return driver.getTitle().toLowerCase();
    }

    public static boolean isElementPresent(String xpath) {
        return driver.findElements(By.xpath(xpath)).size() > 0;
    }

    public static boolean isElementClickable(WebElement element) {
        return element.isDisplayed() && element.isEnabled();
    }

}