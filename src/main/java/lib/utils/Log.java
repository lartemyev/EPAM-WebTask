package lib.utils;
import com.relevantcodes.extentreports.LogStatus;
import lombok.SneakyThrows;

import java.util.logging.Logger;

import static lib.BaseTest.extentTest;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class Log {

    private static Logger logger = Logger.getLogger(Log.class.getName());

    public static void printTestCaseStart(String sTestCaseName){
        print("***********************************************");
        print("Test STARTED: " + sTestCaseName);
        print("***********************************************");
    }

    public static void printTestCaseEnd(){
        print("***********************************************");
        print("Test FINISHED");
        print("***********************************************");
    }

    public static void print(String message) {
        print(message, LogStatus.INFO);
    }

    public static void print(String message, LogStatus logStatus) {
        logger.info(message);
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
            print(infoMessage, LogStatus.PASS);
            result = true;
        } catch (Exception ex) {
            String errorMsg = "*** FAILED VERIFICATION FOR: " + verifyMessage;
            print("      ACTUAL: " + actual, LogStatus.FAIL);
            print("    EXPECTED: " + expected, LogStatus.FAIL);
            if (softVerify)
            {
                print(errorMsg);
            }
            else {
                fail(errorMsg);
            }
        }
        return result;
    }
}
