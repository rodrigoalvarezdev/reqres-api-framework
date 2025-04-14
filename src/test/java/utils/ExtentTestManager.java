package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import java.util.HashMap;
import java.util.Map;
import java.io.IOException;

public class ExtentTestManager {

    private static Map<Integer, ExtentTest> extentTestMap = new HashMap<>();
    private static ExtentReports extent;

    static {
        try {
            extent = ExtentManager.getInstance();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error inicializando ExtentReports", e);
        }
    }

    public static synchronized ExtentTest startTest(String testName, String description) {
        ExtentTest test = extent.createTest(testName, description);
        extentTestMap.put((int) Thread.currentThread().getId(), test);
        return test;
    }

    public static synchronized ExtentTest getTest() {
        return extentTestMap.get((int) Thread.currentThread().getId());
    }
}
