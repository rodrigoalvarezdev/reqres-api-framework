package tests;

import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import base.BaseTest;
import endpoints.Endpoints;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import utils.JsonReader;
import utils.LoggerUtil;
import static org.hamcrest.Matchers.*;

public class UpdateUserTest extends BaseTest{
	
	private static Logger log = LoggerUtil.getLogger(UpdateUserTest.class);
	
	@Test
	public void actualizarUsuario() {
		log.info("iniciando test actualizar usuario");
		
		String payload = JsonReader.leerJsonComoString("src\\test\\resources\\payloads\\actualizar_usuario.json");
		/*JSONObject payload = new JSONObject();
		payload.put("name", "morpheus");
		payload.put("job", "zion resident");*/
		
		Response response = RestAssured
					.given()
						.pathParam("id", 2)
						.header("Content-Type", "application/json")
						.body(payload)
					.when()
						.put(Endpoints.UPDATE_USER);
		
		log.info("Status Code: " + response.statusCode());
		log.info("body: " + response.asPrettyString());
		
		try {
			response
				.then()
					.assertThat()
					.statusCode(200)
					.body("name", equalTo("Rodri QA Avanzado"));
			
			log.info("el usuario se actualizó exitosamente");
		} catch (AssertionError e) {
			log.error("Falló al actualizarse el usuario");
			throw e;
		}
	}

}
