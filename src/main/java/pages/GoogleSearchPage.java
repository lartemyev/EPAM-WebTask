package pages;

import lib.browser.Browser;
import lib.utils.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static lib.browser.Browser.isElementClickable;

public class GoogleSearchPage extends BasePage {

    private Browser browser;

    @FindBy(xpath = "//input[@name='q']")
    private WebElement searchField;
    @FindBy(xpath = "//a[@id='pnnext']")
    private WebElement nextPage;

    public GoogleSearchPage(WebDriver driver) {
        super(driver);
        browser = new Browser();
    }

    private static String expectedDomainElementXPath(String keyWord) {
        return "//a[contains(@href, '" + keyWord + "')]";
    }

    public boolean searchDomain(String domainName, int toPage) {
        boolean foundDomain = false;
        for (int page = 1; page <= toPage; page++) {
            if (page > 1) nextPage.click();
            if (browser.isElementPresent(expectedDomainElementXPath(domainName))) {
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
