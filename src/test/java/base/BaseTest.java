package base;

import org.testng.annotations.Listeners;
import listeners.TestListener;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeSuite;
import utils.ConfigReader;

@Listeners(TestListener.class) // 🔥 ENGANCHADO AQUÍ
public class BaseTest {

    @BeforeSuite(groups = {"regression", "smoke"})
    public void setup() {
        RestAssured.baseURI = ConfigReader.obtenerPropiedad("base.url");
        System.out.println("🌐 BASE URL configurada: " + RestAssured.baseURI);
    }
}
