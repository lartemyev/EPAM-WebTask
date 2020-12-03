import lib.BaseTest;
import lib.Log;
import org.testng.annotations.Test;

import static lib.Log.Verify;
import static lib.Tools.getPageTitle;


public class GoogleTest extends BaseTest {

    private final String TEST12_SearchKeyWord = "automation";
    private final String TEST2_ExpectedDomain = "testautomationday.com"; // automationgame.com

    @Test
    public void Test_One() {
        Log.info("Verify that title contains searched word: " + TEST12_SearchKeyWord);
        webPage.openBaseUrl();
        webPage.searchFor(TEST12_SearchKeyWord);
        webPage.expectedFirstLink(TEST12_SearchKeyWord).click();
        Verify(getPageTitle().contains(TEST12_SearchKeyWord), true,
                "Page title contains keyword - " + TEST12_SearchKeyWord);
    }

    @Test
    public void Test_Two() {
        Log.info("Verifying that there is expected domain - " + TEST2_ExpectedDomain);
        webPage.openBaseUrl();
        webPage.searchFor(TEST12_SearchKeyWord);
        Verify(webPage.searchDomain(TEST2_ExpectedDomain), true,
                "There is expected domain on search results  pages (page: 1-5) - " + TEST2_ExpectedDomain);
    }
}
