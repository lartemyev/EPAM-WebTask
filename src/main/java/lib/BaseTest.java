package lib;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.fail;

public class BaseTest {

    protected static WebDriver driver;
    protected static Configuration conf = new Configuration();
    private static StringBuffer verificationErrors = new StringBuffer();
    protected static ExtentReports extent;
    protected static ExtentTest test;
    protected static <T extends BaseTest> T initPage(Class<T> pageClass) {
        return PageFactory.initElements(driver, pageClass);
    }

    @BeforeSuite()
    public void setUp() throws Exception {
      driver = conf.getDriver();
      driver.manage().window().maximize();
      driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
        extent = new ExtentReports(System.getProperty("user.dir")+"\\Report.html",true);
        extent.loadConfig(new File(System.getProperty("user.dir")));
    }

    @AfterSuite
    public static void tearDown() throws Exception {
      driver.quit();
      String verificationErrorString = verificationErrors.toString();
      if (!"".equals(verificationErrorString)) {
        fail(verificationErrorString);
      }
        test.log(LogStatus.INFO, "Browser closed successfully");
        extent.close();
    }
}
