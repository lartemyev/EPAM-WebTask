package lib;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.GoogleSearchPage;

import static lib.BaseTest.conf;
import static lib.Tools.isElementClickable;
import static lib.Tools.isElementPresent;
import static pages.GoogleSearchPage.expectedDomainElementXPath;

public class BrowserOperations {

    private static WebDriver driver = BaseTest.driver;

    private static GoogleSearchPage googleSearchPage;

    public static void openBaseUrl(){
        driver.get(conf.getBaseUrl());
    }

    public static boolean searchDomain(String domainName, int toPage) {
        boolean foundDomain = false;
        for (int page = 1; page <= toPage; page++) {
            if (page > 1) googleSearchPage.NextPageLink().click();
            if (isElementPresent(expectedDomainElementXPath(domainName))) {
                Log.information("PAGE #" + page + ": Found link on a page (" + domainName + ")");
                foundDomain = isElementClickable(driver.findElement(By.xpath(expectedDomainElementXPath(domainName))));
                break;
            } else
                Log.information("PAGE #" + page + ": Link is not available on a page (" + domainName + ")");
        }
        return foundDomain;
    }
}
