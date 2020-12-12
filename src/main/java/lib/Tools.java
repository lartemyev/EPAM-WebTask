package lib;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Tools {

    protected static WebDriver driver = BaseTest.driver;

    public static String getPageTitle(){
        String returnResult = "";
        try {
            returnResult = driver.getTitle().toLowerCase();
            Log.information("Title of the window: " + returnResult);
        } catch (Exception e) {
            Log.information("Unable to navigate to the title of the window: " + e.toString());
        }
        return returnResult;
    }

    public static boolean isElementPresent(String xpath) {
        boolean returnResult = false;
        try
        {
            returnResult = !driver.findElements(By.xpath(xpath)).isEmpty();
        }
        catch (Exception e) {}
        return returnResult;
    }

    public static boolean isElementClickable(WebElement element) {
        return element.isDisplayed() && element.isEnabled();
    }

}