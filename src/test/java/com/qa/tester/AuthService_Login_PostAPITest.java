package com.qa.tester;

import java.io.IOException;
import java.sql.SQLException;
import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import org.testng.Assert;
import org.testng.annotations.*;
import com.qa.base.testbase;
import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class AuthService_Login_PostAPITest extends testbase {

	testbase testbases;
	public String response_datas;
	Response request;
	public static String response_access_token;
	public static String response_refresh_token;
	static String apiURL;

	@BeforeTest
	public void AgJunction_login_initialization()
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
				.contentType("application/x-www-form-urlencoded; charset=UTF-8").with().header("x-api-key", testbase_x_api_key)
				.formParam("email", env_email).formParam("password", env_password).when().post(apiURL).then().extract()
				.response();

		// Extracting Response data
		ResponseBody body = request.getBody();
		response_datas = body.asString();
		//System.out.println(response_datas);
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

	@Test
	public void AgJunction_login_timeTaken() {
		long time_taken = request.getTime();
		if (time_taken > 15000) {
			System.out.println("==============================================");
			System.out.println("RESPONSE TIME OF LOGIN API IS TAKING LONG TIME");
			System.out.println("==============================================");
			System.out.println("");
		} else {
			System.out.println("==========================================");
			System.out.println("  RESPONSE TIME OF LOGIN API IS "+time_taken); 
			System.out.println("==========================================");
			System.out.println("");
		}
	}

	@Test
	public void AgJunction_login_Status() {
		int status_code = request.getStatusCode();
		Assert.assertEquals(status_code, RESPONSE_STATUS_CODE_200, "ASSERTION Failed :STATUS CODE OF LOGIN API IS 200!!!");
		System.out.println("==========================================");
		System.out.println("  STATUS CODE OF LOGIN API IS "+status_code); 
		System.out.println("==========================================");
		System.out.println("");
	}

	@Test
	public void AgJunction_loginvalidateResponse() {
		boolean id_token = response_datas.contains("id_token");
		boolean access_token = response_datas.contains("access_token");
		boolean token_type = response_datas.contains("token_type");
		boolean refresh_token = response_datas.contains("refresh_token");
		boolean expires_in = response_datas.contains("expires_in");
		if (id_token == true && access_token == true && token_type == true && refresh_token == true
				&& expires_in == true) {
			System.out.println("==========================================");
			System.out.println("     Login API response is validated!!!   ");
			System.out.println("==========================================");
			System.out.println("");
		} else {
			System.out.println("==========================================");
			System.out.println("            Invalid Login Response        ");
			System.out.println("==========================================");
			System.out.println("");
			System.out.println("Invalid Login Response");
		}
	}

	@AfterClass
	public void teardown() {
		System.out.println("==========================================");
		System.out.println("        OUT OF LOGIN POST API Class       ");
		System.out.println("==========================================");
		System.out.println("");

	}
}
