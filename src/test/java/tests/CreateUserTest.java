package tests;

import org.apache.logging.log4j.Logger;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import com.aventstack.extentreports.gherkin.model.Given;

import base.BaseTest;
import endpoints.Endpoints;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import utils.ExtentLogger;
import utils.JsonReader;
import utils.LoggerUtil;
import static org.hamcrest.Matchers.*;
public class CreateUserTest extends BaseTest {
	private static final Logger log = LoggerUtil.getLogger(CreateUserTest.class);
	
	@Test (groups = {"regression"})
	public void crearNuevoUsuario() {
		log.info("iniciando test: Crear nuevo usuario");
		
		
		String user = JsonReader.leerJsonComoString("src/test/resources/payloads/crear_usuario.json");
		/*JSONObject user = new JSONObject();
		user.put("name", "Rodrigo");
		user.put("job", "Lider QA");*/
		
		ExtentLogger.logRequest(user);
		
		Response response = RestAssured
				.given()
					.header("Content-Type", "application/json")
					.body(user)
				.when()
					.post(Endpoints.CREATE_USER);
		
        ExtentLogger.logResponse(response.asPrettyString());
		
		log.info("statusCode: " + response.statusCode());
		log.info("body: " + response.asPrettyString());
		
		try {
			response
				.then()
					.assertThat()
					.statusCode(201)
					.body("name", equalTo("Rodrigo"))
					.body(matchesJsonSchemaInClasspath("schemas/crear_usuario_schema.json"));
			log.info("usuario creado exitosamente");
			 ExtentLogger.logInfo("✅ Usuario creado exitosamente");
		} catch (Exception e) {
			e.printStackTrace(); // 🔥 Imprime el error completo en consola
		    log.error("error al crear el usuario: " + e.getMessage());
		    ExtentLogger.logFail("❌ Error al crear el usuario: " + e.getMessage());
		    throw e;
		}
	}

}
