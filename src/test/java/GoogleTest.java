import lib.BaseTest;
import lib.utils.Log;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.GoogleSearchPage;

import static lib.utils.Log.Verify;


public class GoogleTest extends BaseTest {

    private final String TEST12_SearchKeyWord = "automation";
    private final String TEST2_ExpectedDomain = "testautomationday.com"; // automationgame.com
    private final String TEST2_SearchToPageNumber = "5";

    @Parameters({"searchKeyWord"})
    @Test
    public void TEST1_Search_For_Keyword_And_Verify_Title(@Optional (TEST12_SearchKeyWord) String searchKeyWord) {
        GoogleSearchPage googleSearchPage = new GoogleSearchPage(conf.newDriver());
        Log.print("Verify that title contains keyword: " + searchKeyWord);
        browser.openBaseUrl();
        googleSearchPage.searchFor(searchKeyWord);
        browser.expectedFirstLink(searchKeyWord).click();
        Verify (browser.getPageTitle().contains(searchKeyWord),
                true,
                "Page title contains keyword - " + searchKeyWord);
    }

    @Parameters ({"searchKeyWord", "searchToPage", "expectedDomain"})
    @Test
        public void TEST2_Search_For_Expected_Domain(@Optional(TEST12_SearchKeyWord) String searchKeyWord,
                                                     @Optional(TEST2_SearchToPageNumber) String searchToPage,
                                                     @Optional(TEST2_ExpectedDomain) String expectedDomain) {
        GoogleSearchPage googleSearchPage = new GoogleSearchPage(conf.newDriver());
        Log.print("Verifying that google search results have expected domain - " + expectedDomain);
        browser.openBaseUrl();
        googleSearchPage.searchFor(searchKeyWord);
        Verify(googleSearchPage.searchDomain(expectedDomain, Integer.parseInt(searchToPage)),
                true,
                "There is expected domain on search results  pages (page: 1-" + searchToPage +") - " + expectedDomain);
    }
}
