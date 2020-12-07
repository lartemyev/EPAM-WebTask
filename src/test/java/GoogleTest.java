import lib.BaseTest;
import lib.Log;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static lib.Log.Verify;
import static lib.Tools.getPageTitle;


public class GoogleTest extends BaseTest {

    private final String TEST12_SearchKeyWord = "automation";
    private final String TEST2_ExpectedDomain = "testautomationday.com"; // automationgame.com
    private final String TEST2_SearchToPageNumber = "5";

    @Parameters({"searchKeyWord"})
    @Test
    public void TEST1_SearchForKeywordAndVerifyTitle(@Optional (TEST12_SearchKeyWord) String searchKeyWord) {
        Log.info("Verify that title contains searched word: " + searchKeyWord);
        webPage.openBaseUrl();
        webPage.searchFor(searchKeyWord);
        webPage.expectedFirstLink(searchKeyWord).click();
        Verify (getPageTitle().contains(searchKeyWord),
                true,
                "Page title contains keyword - " + searchKeyWord);
    }

    @Parameters ({"searchKeyWord", "searchToPage", "expectedDomain"})
    @Test
        public void TEST2_SearchForExpectedDomain (@Optional(TEST12_SearchKeyWord) String searchKeyWord,
                                                   @Optional(TEST2_SearchToPageNumber) String searchToPage,
                                                   @Optional(TEST2_ExpectedDomain) String expectedDomain) {
        Log.info("Verifying that there is expected domain - " + expectedDomain);
        webPage.openBaseUrl();
        webPage.searchFor(searchKeyWord);
        Verify(webPage.searchDomain(expectedDomain, Integer.parseInt(searchToPage)),
                true,
                "There is expected domain on search results  pages (page: 1-" + searchToPage +") - " + expectedDomain);
    }
}
