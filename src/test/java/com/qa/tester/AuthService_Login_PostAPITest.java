package com.qa.tester;

import java.io.IOException;
import java.sql.SQLException;
import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import org.testng.Assert;
import org.testng.annotations.*;
import com.qa.base.testbase;
import com.qa.util.TestUtil;

import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class AuthService_Login_PostAPITest extends testbase {

	public static String response_access_token;
	public static String response_refresh_token;
	public static String response_error_message;
	public static String header_sheet = "Login";
	public static String apiURL;
	public static String response_status_code;
	public static int status_code;
	public static int counter = 1;
	public static long response_time_taken;
	public String response_data;
	Response request;
	
	public void AgJunction_login_main()
			throws ClientProtocolException, IOException, JSONException, ClassNotFoundException, SQLException {

		// Fetching URL
		String baseURL = env_url;
		// System.out.println(baseURL);
		String endPointURL = "/auth/login";
		// System.out.println(endPointURL);
		apiURL = baseURL + endPointURL;

		System.out.println("");
		System.out.println("==========================================");
		System.out.println("       INSIDE LOGIN POST API CLASS        ");
		System.out.println("==========================================");
		System.out.println("");
		request = RestAssured.given()
				.config(RestAssured.config().encoderConfig(
				 EncoderConfig.encoderConfig().encodeContentTypeAs("x-www-form-urlencoded", ContentType.URLENC)))
				.contentType("application/x-www-form-urlencoded; charset=UTF-8").with()
				.header("x-api-key", testbase_x_api_key).formParam("email", env_email)
				.formParam("password", env_password).when().post(apiURL).then().extract().response();

		// Extracting Response data
		ResponseBody body = request.getBody();
		String response_datas = body.asString();
		// System.out.println(response_datas);
		String response_data = body.asString();
		String[] refresh_token = response_data.split("\"refresh_token\":\"");
		String[] refresh_token1 = refresh_token[1].split("\",\"expires_in\":3600}");
		response_refresh_token = refresh_token1[0];
		System.out.println("============ Access Token ================");
		System.out.println("         FETCHING ACCESS TOKEN...         ");
		System.out.println("==========================================");
		System.out.println("");
		String[] access_token = response_data.split("\",\"access_token\":\"");
		String[] access_token1 = access_token[1].split("\",\"token_type\"");
		response_access_token = access_token1[0];
		System.out.println("============= Refresh Token ==============");
		System.out.println("        FETCHING REFRESH TOKEN...         ");
		System.out.println("==========================================");
		System.out.println("");
	}

	public String AgJunction_login_timeTaken() {
		response_time_taken = request.getTime();
		if (response_time_taken > 1000) {
			return response_time_taken +"ms taking long time!!!";
		} else {
			return response_time_taken +"ms";
		}
	}
	
	public String AgJunction_login_response_code() {
		// Fetch Response error message
		String[] response_status = response_data.split("\"status\":");
		String[] response_status1 = response_status[1].split(",");
		response_status_code = response_status1[0];
		if ( Integer.parseInt(response_status_code) != status_code ) {
			return response_status_code +" doesn't matched with the response status code.";
		} else {
			return response_status_code +" is matched with the response status code.";
		}
	}

	@DataProvider
	public Object[][] getExcelData() {
		Object data[][] = TestUtil.getTestData(header_sheet);
		return data;
	}

	@Test(dataProvider = "getExcelData")
	public void AgJunction_login_test(String Testdata, String x_api_key, String emailId, String password) {

		// Fetching URL
		String baseURL = env_url;
		String endPointURL = "/auth/login";
		apiURL = baseURL + endPointURL;
		System.out.println("TC_00" + counter + "_" + Testdata);
		System.out.println("=========================================================");
		System.out.println(" INSIDE POST LOGIN API WITH " + Testdata);
		System.out.println("=========================================================");
		System.out.println("");
		request = RestAssured.given()
				.config(RestAssured.config().encoderConfig(
						EncoderConfig.encoderConfig().encodeContentTypeAs("x-www-form-urlencoded", ContentType.URLENC)))
				.contentType("application/x-www-form-urlencoded; charset=UTF-8").with().header("x-api-key", x_api_key) // Entering
																														// headers
				.formParam("email", emailId) // Passing
				.formParam("password", password).when().post(apiURL).then().extract().response();

		status_code = request.getStatusCode();

		// Fetching Headers:
		Headers Response_headers = request.getHeaders();
		
		// Extracting Response data
		ResponseBody body = request.getBody();
		response_data = body.asString();
		
		// Fetch Response error message
		String[] message = response_data.split("\"message\":\"");
		String[] message1 = message[1].split("\",");
		response_error_message = message1[0];
		
		if (status_code == 200) {

			// Fetching Refresh token from the response:
			String[] refresh_token = response_data.split("\"refresh_token\":\"");
			String[] refresh_token1 = refresh_token[1].split("\",\"expires_in\":3600}");
			response_refresh_token = refresh_token1[0];

			// Fetching Access token from the response:
			String[] access_token = response_data.split("\",\"access_token\":\"");
			String[] access_token1 = access_token[1].split("\",\"token_type\"");
			response_access_token = access_token1[0];

			// Console Output:
			System.out.println("1. STATUS CODE     -> " + status_code);
			System.out.println("2. RESPONSE CODE   -> " + AgJunction_login_response_code());
			System.out.println("3. ACCESS TOKEN    -> " + response_access_token);
			System.out.println("4. REFRESH TOKEN   -> " + response_refresh_token);
			System.out.println("5. TIME TAKEN      -> " + AgJunction_login_timeTaken());
			System.out.println("6. RESPONSE HEADERs-> ");
			System.out.println("---------------------------------------------------------");
			System.out.println(                  Response_headers                         );
			System.out.println("---------------------------------------------------------");
			System.out.println("");
			System.out.println("=========================================================");
			System.out.println("  OUT OF POST LOGIN API WITH " + Testdata);
			System.out.println("=========================================================");
			System.out.println("");
			counter++;
		}

		else {
			
			// Console Output:
			System.out.println("1. STATUS CODE     -> " + status_code);
			System.out.println("2. RESPONSE CODE   -> " + AgJunction_login_response_code());
			System.out.println("3. MESSAGE         -> " + response_error_message);
			System.out.println("4. TIME TAKEN      -> " + AgJunction_login_timeTaken());
			System.out.println("5. RESPONSE HEADERs-> ");
			System.out.println("---------------------------------------------------------");
			System.out.println(                  Response_headers                         );
			System.out.println("---------------------------------------------------------");
			System.out.println("");
			System.out.println("=========================================================");
			System.out.println("  OUT OF POST LOGIN API WITH " + Testdata);
			System.out.println("=========================================================");
			System.out.println("");
			counter++;
		}
	}
}
