package tests;

import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import base.BaseTest;
import endpoints.Endpoints;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import utils.LoggerUtil;

public class DeleteUserTest extends BaseTest {
	private static final Logger log = LoggerUtil.getLogger(DeleteUserTest.class);
	
	@Test
	public void eliminarUsuario() {
		
		log.info("Iniciando test: Eliminar usuario");
		
		Response response = RestAssured
				.given()
					.pathParams("id", 2)
				.when()
					.delete(Endpoints.DELETE_USER);
		
		log.info("Status Code: " + response.statusCode());
		
		try {
			response
				.then()
					.assertThat()
					.statusCode(204);
			
			log.info("Usuario eliminado existosamente");
		} catch (AssertionError e) {
			log.error("Falló la eliminación del usuario");
			throw e;
		}
	}
}
