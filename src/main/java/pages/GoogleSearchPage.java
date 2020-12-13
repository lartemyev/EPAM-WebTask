package pages;

import lib.BaseTest;
import lib.utils.Log;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import static lib.browser.Browser.isElementClickable;
import static lib.browser.Browser.isElementPresent;

public class GoogleSearchPage extends BaseTest {

    private WebDriver driver = BaseTest.driver;

    @FindBy(xpath = "//input[@name='q']")
    private WebElement searchField;
    @FindBy(xpath = "//a[@id='pnnext']")
    private WebElement nextPage;

    private static String expectedDomainElementXPath(String keyWord) {
        return "//a[contains(@href, '" + keyWord + "')]";
    }

    public boolean searchDomain(String domainName, int toPage) {
        boolean foundDomain = false;
        for (int page = 1; page <= toPage; page++) {
            if (page > 1) nextPage.click();
            if (isElementPresent(expectedDomainElementXPath(domainName))) {
                Log.print("PAGE #" + page + ": Found link on a page (" + domainName + ")");
                foundDomain = isElementClickable(driver.findElement(By.xpath(expectedDomainElementXPath(domainName))));
                break;
            } else
                Log.print("PAGE #" + page + ": Link is not available on a page (" + domainName + ")");
        }
        return foundDomain;
    }

    public void searchFor(String string) {
        searchField.clear();
        searchField.sendKeys(string, Keys.ENTER);
    }
}
