package com.restapi.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.*;

public class POSTCall_NoBDD {
	@Test
	public void token_APITest()
	{
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		
		RequestSpecification request = RestAssured.given().log().all();
		
		String payLoad = "{\"username\":\"admin\",\"password\":\"password123\"}";
		
		request.contentType("application/json");
		request.body(payLoad);
		
		
		Response response = request.post("/auth");
		
		System.out.println(response.prettyPrint());
		
		System.out.println(response.getStatusCode());
		
		JsonPath js = response.jsonPath();
		String tokenId = js.get("token");
		
		System.out.println("Token id is : " + tokenId);
		
		Assert.assertNotNull(tokenId);	
	}

}
