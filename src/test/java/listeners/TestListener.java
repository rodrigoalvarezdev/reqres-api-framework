package listeners;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;

import utils.ExtentLogger;
import utils.ExtentManager;
import utils.ExtentTestManager;

public class TestListener implements ITestListener {

    @Override
    public void onStart(ITestContext context) {
        System.out.println("🌐 BASE URL configurada: " + context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("📝 Finalizando Suite: " + context.getName());
        try {
            ExtentManager.getInstance().flush(); // 🔥 Guarda el reporte
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("🚀 Iniciando test: " + result.getMethod().getMethodName());
        ExtentTestManager.startTest(result.getMethod().getMethodName(), "Test en ejecución");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("✅ Test PASÓ: " + result.getMethod().getMethodName());
        ExtentLogger.logPass("✅ Test PASÓ: " + result.getMethod().getMethodName());
        ExtentTestManager.getTest().log(Status.PASS, "Test PASÓ exitosamente.");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("❌ Test FALLÓ: " + result.getMethod().getMethodName());
        ExtentTestManager.getTest().log(Status.FAIL, "Test FALLÓ: " + result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("⚠️ Test SKIPPEADO: " + result.getMethod().getMethodName());
        ExtentTestManager.getTest().log(Status.SKIP, "Test SKIPPEADO: " + result.getThrowable());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // No lo usamos por ahora
    }
}
