package tests;

import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import base.BaseTest;
import endpoints.Endpoints;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import utils.ExtentLogger;
import utils.JsonReader;
import utils.LoggerUtil;
import static org.hamcrest.Matchers.*;

public class UpdateUserTest extends BaseTest{
	
	private static Logger log = LoggerUtil.getLogger(UpdateUserTest.class);
	
	@Test (groups = {"regression"})
	public void actualizarUsuario() {
		log.info("iniciando test actualizar usuario");
		
		String payload = JsonReader.leerJsonComoString("src\\test\\resources\\payloads\\actualizar_usuario.json");
		
		ExtentLogger.logRequest(payload);
		
		Response response = RestAssured
					.given()
						.pathParam("id", 2)
						.header("Content-Type", "application/json")
						.body(payload)
					.when()
						.put(Endpoints.UPDATE_USER);
		
		log.info("Status Code: " + response.statusCode());
		log.info("body: " + response.asPrettyString());
		ExtentLogger.logInfo("status code: " + response.statusCode());
		ExtentLogger.logInfo(response.asPrettyString());
		try {
			response
				.then()
					.assertThat()
					.statusCode(200)
					.body("name", equalTo("Rodri QA Avanzado"));
			ExtentLogger.logInfo("el usuario se actualizó exitosamente");
			log.info("el usuario se actualizó exitosamente");
		} catch (AssertionError e) {
			log.error("Falló al actualizarse el usuario");
			ExtentLogger.logFail("error al actualizar usuario");
			throw e;
		}
	}

}
