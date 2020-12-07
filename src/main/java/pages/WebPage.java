package pages;

import lib.BaseTest;
import lib.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static lib.Tools.isElementClickable;
import static lib.Tools.isElementPresent;

public class WebPage extends BaseTest {

    @FindBy(xpath = "//input[@name='q']")
    WebElement searchField;
    @FindBy(xpath = "//a[@id='pnnext']")
    WebElement nextPage;

    String expectedDomainElementXPath (String keyWord) {
        return "//a[contains(@href, '" + keyWord + "')]";
    }
    //WebElement expectedDomainElement(String keyWord) {
    //    return driver.findElements(By.xpath(expectedDomainElementXPath(keyWord))).stream().findAny().get();
    //}

    public WebElement expectedFirstLink(String keyWord) {
        return driver.findElement(By.partialLinkText(keyWord));
    }

    public void openBaseUrl(){
        driver.get(conf.getBaseUrl());
    }

    public void searchFor(String string) {
        searchField.clear();
        searchField.sendKeys(string, Keys.ENTER);
    }

    public boolean searchDomain(String domainName, int toPage) {
        boolean foundDomain = false;
        for (int page = 1; page <= toPage; page++) {
            if (page > 1) nextPage.click();
            if (isElementPresent(expectedDomainElementXPath(domainName))) {
                Log.info("PAGE #" + page + ": Found link on a page (" + domainName + ")");
                foundDomain = isElementClickable(driver.findElement(By.xpath(expectedDomainElementXPath(domainName))));
                break;
            } else
                Log.info("PAGE #" + page + ": Link is not available on a page (" + domainName + ")");
        }
        return foundDomain;
    }
}
