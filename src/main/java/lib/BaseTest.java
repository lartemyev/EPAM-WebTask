package lib;

import com.relevantcodes.extentreports.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.WebPage;

import java.io.File;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import static lib.Log.endTC;
import static lib.Log.startTC;
import static org.testng.Assert.fail;

public class BaseTest {

    protected static WebDriver driver;
    private int testNumber = 1;
    protected static Configuration conf = new Configuration();
    private static StringBuffer verificationErrors = new StringBuffer();
    protected static ExtentReports extentReport;
    protected static ExtentTest extentTest;
    protected static <T extends BaseTest> T initPage(Class<T> pageClass) {
        return PageFactory.initElements(driver, pageClass);
    }
    protected static WebPage webPage;

    @BeforeSuite()
    public void setUp() {
        driver = conf.getDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
        extentReport = new ExtentReports(System.getProperty("user.dir") + "\\Report.html", true);
        extentReport.loadConfig(new File(System.getProperty("user.dir")));
    }

    @BeforeMethod(alwaysRun = true)
    public void Prepare(Method method) {
        extentTest = extentReport.startTest(("#" + testNumber++ + ": " + this.getClass().getSimpleName() + " -> " + method.getName()), method.getName());
        extentTest.assignAuthor("Leonid Artemiev");
        extentTest.assignCategory("EPAM Test Automation Task");
        webPage = initPage(WebPage.class);
        startTC(method.getName());
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
        endTC();
        extentReport.endTest(extentTest);
        extentReport.flush();
    }

    @AfterSuite
    public static void tearDown() {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
        extentTest.log(LogStatus.INFO, "Browser closed successfully");
        extentReport.close();
    }
}
