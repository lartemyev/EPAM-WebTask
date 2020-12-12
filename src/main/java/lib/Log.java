package lib;

import com.relevantcodes.extentreports.LogStatus;
import lombok.SneakyThrows;

import java.util.logging.Logger;

import static lib.BaseTest.extentTest;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class Log {

    private static Logger Log = Logger.getLogger(Log.class.getName());

    public static void printTestCaseStart(String sTestCaseName){
        information("***********************************************");
        information("Test STARTED: " + sTestCaseName);
        information("***********************************************");
    }

    public static void printTestCaseEnd(){
        information("***********************************************");
        information("Test FINISHED");
        information("***********************************************");
    }

    public static void information(String message) {
        information(message, LogStatus.INFO);
    }

    public static void information(String message, LogStatus logStatus) {
        Log.info(message);
        extentTest.log(logStatus, message);
    }
    public static boolean Verify (Object actual, Object expected, String verifyMessage) {
        return Verify (actual, expected, verifyMessage, false);
    }

    @SneakyThrows
    public static boolean Verify (Object actual, Object expected, String verifyMessage, boolean softVerify) {
        boolean result = false;
        String infoMessage;
        try {
            assertEquals(expected, actual);
            infoMessage = "*** PASSED VERIFICATION FOR: " + verifyMessage;
            information(infoMessage, LogStatus.PASS);
            result = true;
        } catch (Exception ex) {
            String errorMsg = "*** FAILED VERIFICATION FOR: " + verifyMessage;
            information("      ACTUAL: " + actual, LogStatus.FAIL);
            information("    EXPECTED: " + expected, LogStatus.FAIL);
            if (softVerify)
            {
                information(errorMsg);
            }
            else {
                fail(errorMsg);
            }
        }
        return result;
    }
}
