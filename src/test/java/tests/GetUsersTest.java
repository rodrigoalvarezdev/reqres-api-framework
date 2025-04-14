package tests;

import org.apache.logging.log4j.Logger;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import org.testng.annotations.Test;

import base.BaseTest;
import endpoints.Endpoints;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.ExtentLogger;
import utils.LoggerUtil;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GetUsersTest extends BaseTest{
	private static Logger log = LoggerUtil.getLogger(GetUsersTest.class);
	
	@Test (groups = {"regression", "smoke"})
	public void listarUsuarioPagina2() throws InterruptedException {
		log.info("iniciando test: Listar usuarios en la página 2");
		
		ExtentLogger.logRequest(RestAssured.baseURI + "users?page=2");
		
		Response response =
				given()
					.pathParams("page", 2)
				.when()
					.get(Endpoints.LIST_USERS);
		
		log.info("statusCode: " + response.statusCode());
		log.info("body: " + response.asPrettyString());
		
		ExtentLogger.logResponse(response.asPrettyString());
		
		try {
			response
			.then()
				.assertThat()
				.statusCode(200)
				.body("page", equalTo(2))
				.body("data.size()", greaterThan(0))
				.body(matchesJsonSchemaInClasspath("schemas/listar_usuarios_schema.json"));
		
		log.info("test finalizando exitosamente.-");
		ExtentLogger.logInfo("✅ se obtuvo la lista de usuarios exitosamente");
		} catch (AssertionError e) {
			e.printStackTrace(); // 🔥 Imprime el error completo en consola
			ExtentLogger.logFail("❌ Error al obtener el usuario: " + e.getMessage());
		    log.error("error al listar usuarios: " + e.getMessage());
		    throw e;
		}
		
		
		
	}
}
