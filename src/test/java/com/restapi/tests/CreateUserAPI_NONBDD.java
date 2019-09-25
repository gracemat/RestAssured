package com.restapi.tests;

import static io.restassured.RestAssured.*;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class CreateUserAPI_NONBDD 
{
	@Test 
	public void creating_User_test()
	{
		RestAssured.baseURI="https://gorest.co.in";
		RequestSpecification req = RestAssured.given().log().all();
		req.contentType("application/json");
		String userDetails = "{\r\n" + 
				"	\"first_name\": \"Dallas\",\r\n" + 
				"            \"last_name\": \"Tx\",\r\n" + 
				"            \"gender\": \"male\",\r\n" + 
				"             \"email\": \"dallas.tx@example.net\",\r\n" + 
				"            \"phone\": \"879.492.3705 x5350\",\r\n" + 
				"            \"website\": \"http://moen.com/rerum-magni-enim-suscipit-eos-inventore.html\",\r\n" + 
				"            \"address\": \"600 Dejah Trace Suite 813\\nMcKenziestad, MI 27424-3644\",\r\n" + 
				"            \"status\": \"active\"\r\n" + 
				"            }";
		req.header("Authorization", "Bearer 2EkM0agfe828Kt2-DGbGqt9DmAqOj0GjsdXd");
		//req.contentType("application/json");
		req.body(userDetails);
		
		Response resp = req.post("/public-api/users");
		
		System.out.println(resp.prettyPrint());
		
		System.out.println("Status code is " +resp.getStatusCode());
			
	}
	

}
