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
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class AuthService_Logout_GETAPITest extends AuthService_Login_PostAPITest{
	
	testbase testbases;
	public String response_datas;
	Response request;
	static String apiURL;
	AuthService_Login_PostAPITest auth;
	
	public AuthService_Logout_GETAPITest() {
		super();
	}
	
	@BeforeClass
	public void AuthService_logout_initialization() 
			throws ClientProtocolException, IOException, JSONException, ClassNotFoundException, SQLException {
		
		// Fetching URL
		String baseURL = env_url;
		// System.out.println(baseURL);
		String endPointURL = "/auth/logout";
		// System.out.println(endPointURL);
		apiURL = baseURL + endPointURL;

		System.out.println("");
		System.out.println("==========================================");
		System.out.println("        INSIDE GET LOGOUT API CLASS       ");
		System.out.println("==========================================");
		System.out.println("");
		request = RestAssured.given()
				.config(RestAssured.config().encoderConfig(
						EncoderConfig.encoderConfig().encodeContentTypeAs("x-www-form-urlencoded", ContentType.URLENC)))
				.contentType("application/x-www-form-urlencoded; charset=UTF-8")
				.with()
				.header("x-api-key", testbase_x_api_key)
				.header("Authorization", auth.response_access_token)
				.when()
				.get(apiURL)
				.then()
				.extract()
				.response();

		// Extracting Response data
		ResponseBody body = request.getBody();
		response_datas = body.asString();
		//System.out.println(response_datas);
		String response_data = body.asString();
	}

	@Test
	public void AuthService_logout_timeTaken() {
		long time_taken = request.getTime();
		if (time_taken > 15000) {
			System.out.println("===============================================");
			System.out.println("RESPONSE TIME OF LOGOUT API IS TAKING LONG TIME");
			System.out.println("===============================================");
			System.out.println("");
		} else {
			System.out.println("==========================================");
			System.out.println("  RESPONSE TIME OF LOGOUT API IS "+time_taken); 
			System.out.println("==========================================");
			System.out.println("");
		}
	}

	@Test
	public void AuthService_logout_Status() {
		int status_code = request.getStatusCode();
		Assert.assertEquals(status_code, RESPONSE_STATUS_CODE_200, "ASSERTION Failed :Status code is not 200!!!");
		System.out.println("==========================================");
		System.out.println("  STATUS CODE OF LOGOUT API IS "+status_code); 
		System.out.println("==========================================");
		System.out.println("");
	}

	//@Test
	public void AuthService_logout_headers() {
		Headers header_count = request.getHeaders();
		System.out.println(header_count);
	}
	
	@AfterClass
	public void teardown() {
		System.out.println("==========================================");
		System.out.println("        OUT OF LOGOUT GET API Class       ");
		System.out.println("==========================================");
		System.out.println("");

	}
}
