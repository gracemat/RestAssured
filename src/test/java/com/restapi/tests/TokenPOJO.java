package com.restapi.tests;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class TokenPOJO {
	@Test
	public void getToken()
	{
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		RequestSpecification requ = RestAssured.given().log().all();
		String credentialsJson = null ;
//		String credentialsJson2= null ;
//		String credentialsJson3= null ;
		
		requ.contentType("application/json");
		
		UnPw userPw = new UnPw("admin","password123");
//		UnPw userPw2 = new UnPw("Adam","passwd1");
//		UnPw userPw3 = new UnPw("Jersey","password#1");
		
		ObjectMapper mapping = new ObjectMapper();
		
		try {
			 credentialsJson = mapping.writeValueAsString(userPw);
//			 credentialsJson2 = mapping.writeValueAsString(userPw2);
//			 credentialsJson3= mapping.writeValueAsString(userPw3);
			 
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(" User Credentials are : "+credentialsJson);
//		System.out.println(" User Credentials are : " +credentialsJson2);
//		System.out.println(" User Credentials are : " +credentialsJson3);
		
		requ.body(credentialsJson);
		Response respo = requ.post("/auth");
		
		System.out.println(respo.prettyPrint());
		
		JsonPath jsonResponse = respo.jsonPath();
		System.out.println(jsonResponse.get("token"));
		System.out.println(respo.statusCode());
		System.out.println(respo.getStatusCode());
		
		Assert.assertEquals(respo.getStatusCode(),HttpStatusCodes.HTTP_STATUS_CODE_200);
		
		
		Assert.assertNotNull(jsonResponse.get("token"));
	}
	
	@Test
	public void getToken_WithBadCredentials()
	{
		
		RestAssured.baseURI="https://restful-booker.herokuapp.com";
		RequestSpecification req = RestAssured.given().log().all();
		String  credJson = null;
		req.contentType("application/json");
		
		UnPw jsonPayLoad = new UnPw("admin", "admins");
		
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			credJson = mapper.writeValueAsString(jsonPayLoad);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(" User Credentials are : "+credJson);
		Response resp = req.post("/auth");
	 req.body(credJson);
		System.out.println(resp.getStatusCode());
		System.out.println(resp.prettyPrint());
		
		JsonPath json = resp.jsonPath();
		String reason = json.get("reason");
		System.out.println("REASON IS: " +reason);
		
		Assert.assertEquals(reason, HttpStatusCodes.wrongCredentialsMsg);
		}
	
	@Test
	public void badJson() {
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		RequestSpecification request = RestAssured.given().log().all();
		
		String badJsonPayload = "{\"username\":\"admin\"\"password\":\"password123\"}";
		request.contentType("application/json");
		request.body(badJsonPayload);
		
		Response response = request.post("/auth");
		System.out.println(response.prettyPrint());
		System.out.println(response.getStatusCode());
		Assert.assertEquals(response.getStatusCode(), HttpStatusCodes.HTTP_STATUS_CODE_400);
	}
		
}
