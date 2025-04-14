package utils;

import java.io.IOException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    private static ExtentReports extent;

    public static ExtentReports getInstance() throws IOException {
        if (extent == null) {
            createInstance();
        }
        return extent;
    }

    private static void createInstance() throws IOException {
        // Ruta donde se va a generar el reporte HTML
        String reportPath = System.getProperty("user.dir") + "/test-output/ExtentReport.html";
        
        // Configurar el reporter
        ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
        reporter.loadXMLConfig("src/main/resources/extent-config.xml"); // Lee tu extent-config.xml
        
        // Crear instancia principal
        extent = new ExtentReports();
        extent.attachReporter(reporter);

        // Opcional: Información general del proyecto (metadatos del reporte)
        extent.setSystemInfo("Tester", "Rodri");
        extent.setSystemInfo("Proyecto", "Reqres API Framework");
        extent.setSystemInfo("Ambiente", "Testing");
    }
}
