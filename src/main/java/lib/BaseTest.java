package lib;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import lib.utils.Log;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import pages.GoogleSearchPage;

import java.io.File;
import java.lang.reflect.Method;

import static lib.utils.Log.printTestCaseEnd;
import static lib.utils.Log.printTestCaseStart;

public class BaseTest {

    public static WebDriver driver;
    private int testNumber = 1;
    public static Driver conf;
    protected static ExtentReports extentReport;
    public static ExtentTest extentTest;

    protected static <T extends BaseTest> T initPage(Class<T> pageClass) {
        return PageFactory.initElements(driver, pageClass);
    }
    protected static GoogleSearchPage googleSearchPage;

    @BeforeSuite()
    public void setUpSuite() {
        extentReport = new ExtentReports(System.getProperty("user.dir") + "\\Reports\\Report.html", true);
        extentReport.loadConfig(new File(System.getProperty("user.dir") + "\\extent-config.xml"));
    }

    public void setUp() {
        conf = new Driver();
        driver = conf.getDriver();
        googleSearchPage = initPage(GoogleSearchPage.class);
    }

    @BeforeMethod(alwaysRun = true)
    public void Prepare(Method method) {
        setUp();
        extentTest = extentReport.startTest(("#" + testNumber++ + ": " + this.getClass().getSimpleName() + " -> " + method.getName()), method.getName());
        extentTest.assignAuthor("Leonid Artemiev");
        extentTest.assignCategory("EPAM Test Automation Task");
        printTestCaseStart(method.getName());
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            extentTest.log(LogStatus.FAIL, "Test FAILED " + result.getThrowable());
        } else if (result.getStatus() == ITestResult.SKIP) {
            extentTest.log(LogStatus.SKIP, "Test SKIPPED " + result.getThrowable());
        } else {
            extentTest.log(LogStatus.PASS, "Test PASSED");
        }
        printTestCaseEnd();
        extentReport.endTest(extentTest);
        extentReport.flush();
        driver.close();
    }

    @AfterSuite
    public static void tearDownSuite() {
        try { // firefox has exception here
            driver.quit();
        } catch (Exception e) {
            Log.print("Unable to driver.quit()");
        }
        extentTest.log(LogStatus.INFO, "Browser closed successfully");
        extentReport.close();
    }
}
