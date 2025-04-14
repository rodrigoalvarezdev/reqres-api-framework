package utils;

import com.aventstack.extentreports.Status;

public class ExtentLogger {

    public static void logPass(String message) {
        ExtentTestManager.getTest().log(Status.PASS, message);
    }

    public static void logFail(String message) {
        ExtentTestManager.getTest().log(Status.FAIL, message);
    }

    public static void logInfo(String message) {
        ExtentTestManager.getTest().log(Status.INFO, message);
    }

    public static void logRequest(String request) {
        ExtentTestManager.getTest().log(Status.INFO, "📤 REQUEST:\n" + request);
    }

    public static void logResponse(String response) {
        ExtentTestManager.getTest().log(Status.INFO, "📥 RESPONSE:\n" + response);
    }
}

