package base;

import org.json.simple.JSONObject;

import endpoints.Endpoints;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class TokenManager {
	private static String token;
	
	@SuppressWarnings("unchecked")
	public static String obtenerToken() {
		if(token == null) {
			JSONObject loginPayload = new JSONObject();
			loginPayload.put("email", "eve.holt@reqres.in");
			loginPayload.put("password", "cityslicka");
			
			Response response = RestAssured
					.given()
						.header("Content-Type", "application/json")
						.body(loginPayload.toJSONString())
					.when()
						.post(Endpoints.LOGIN);
			
			response.then().statusCode(200);
			
			token = response.jsonPath().getString("token");
		}
		
		return token;
	}
}
