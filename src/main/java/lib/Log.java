package lib;

import com.relevantcodes.extentreports.LogStatus;
import lombok.SneakyThrows;

import java.util.logging.Logger;

import static lib.BaseTest.extentTest;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class Log {

    private static Logger Log = Logger.getLogger(Log.class.getName());//

    public static void startTC(String sTestCaseName){
        Log.info("***********************************************");
        info("Test STARTED: " + sTestCaseName);
        Log.info("***********************************************");
    }

    public static void endTC(){
        Log.info("***********************************************");
        info("Test FINISHED");
        Log.info("***********************************************");
    }

    public static void info(String message) {
        Log.info(message);
        extentTest.log(LogStatus.INFO, message);
    }

    public static void info(String message, LogStatus logStatus) {
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
            info(infoMessage, LogStatus.PASS);
            result = true;
        } catch (Exception ex) {
            String errorMsg = "*** FAILED VERIFICATION FOR: " + verifyMessage;
            info("      ACTUAL: " + actual, LogStatus.FAIL);
            info("    EXPECTED: " + expected, LogStatus.FAIL);
            if (softVerify)
            {
                info(errorMsg);
            }
            else {
                //info(errorMsg, LogStatus.FAIL);
                // throw new Exception (verifyMessage);
                fail(errorMsg);
            }
        }
        return result;
    }

    //public static void reportInfo(Class T, String message) {
    //    extentTest = extentReport.startTest(("[TEST]: " + T.getSimpleName() + " -> " + T.getEnclosingMethod().getName()), message);
    //}
}
