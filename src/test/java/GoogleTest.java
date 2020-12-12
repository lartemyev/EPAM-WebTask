import lib.BaseTest;
import lib.Log;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static lib.BrowserOperations.*;
import static lib.Log.Verify;
import static lib.Tools.getPageTitle;


public class GoogleTest extends BaseTest {

    private final String TEST12_SearchKeyWord = "automation";
    private final String TEST2_ExpectedDomain = "testautomationday.com"; // automationgame.com
    private final String TEST2_SearchToPageNumber = "5";

    @Parameters({"searchKeyWord"})
    @Test
    public void TEST1_Search_For_Keyword_And_Verify_Title(@Optional (TEST12_SearchKeyWord) String searchKeyWord) {
        Log.information("Verify that title contains keyword: " + searchKeyWord);
        openBaseUrl();
        webPage.searchFor(searchKeyWord);
        webPage.expectedFirstLink(searchKeyWord).click();
        Verify (getPageTitle().contains(searchKeyWord),
                true,
                "Page title contains keyword - " + searchKeyWord);
    }

    @Parameters ({"searchKeyWord", "searchToPage", "expectedDomain"})
    @Test
        public void TEST2_Search_For_Expected_Domain(@Optional(TEST12_SearchKeyWord) String searchKeyWord,
                                                     @Optional(TEST2_SearchToPageNumber) String searchToPage,
                                                     @Optional(TEST2_ExpectedDomain) String expectedDomain) {
        Log.information("Verifying that google search results have expected domain - " + expectedDomain);
        openBaseUrl();
        webPage.searchFor(searchKeyWord);
        Verify(searchDomain(expectedDomain, Integer.parseInt(searchToPage)),
                true,
                "There is expected domain on search results  pages (page: 1-" + searchToPage +") - " + expectedDomain);
    }
}
