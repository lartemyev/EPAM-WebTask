package pages;

import lib.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GoogleSearchPage extends BaseTest {

    @FindBy(xpath = "//input[@name='q']")
    public WebElement searchField;
    @FindBy(xpath = "//a[@id='pnnext']")
    private WebElement nextPage;

    public static String expectedDomainElementXPath(String keyWord) {
        return "//a[contains(@href, '" + keyWord + "')]";
    }

    public WebElement expectedFirstLink(String keyWord) {
        return driver.findElement(By.partialLinkText(keyWord));
    }

    public WebElement NextPageLink() {
        return nextPage;
    }

    public void searchFor(String string) {
        searchField.clear();
        searchField.sendKeys(string, Keys.ENTER);
    }
}
