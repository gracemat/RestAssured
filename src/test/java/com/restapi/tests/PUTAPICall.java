package com.restapi.tests;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PUTAPICall 
{
	@Test
	public void putCall()
	{
		RestAssured.baseURI = "https://gorest.co.in";
		RequestSpecification reques = RestAssured.given().log().all();
		reques.contentType("application/json");
		reques.header("Authorization","Bearer 2EkM0agfe828Kt2-DGbGqt9DmAqOj0GjsdXd");
		
		
		Response responseGetBefore = reques.get("/public-api/users/2027");
		//System.out.println(responseGetBefore.prettyPrint());
		
		JsonPath jsonGet = responseGetBefore.jsonPath();
		String statusGetOfUserBefore = jsonGet.get("result.status");
		System.out.println("Before Updating Status of user is : "+statusGetOfUserBefore);
		
		File file = new File("C:\\Users\\prave\\eclipse-workspace\\RestAssuredStart\\src\\test\\java\\com\\restapi\\tests\\UpdateUSer.json");
		
		reques.body(file);
		
		
		Response responsePut = reques.put("/public-api/users/2027");
		
		//System.out.println(responsePut.prettyPrint());
		JsonPath jsonPut = responsePut.jsonPath();
		String statusPutOfUser = jsonPut.get("result.status");
		
		System.out.println("After Updating Status of user is : "+statusPutOfUser);
		
		//GETCALL to check the update
		Response responseGetAfter = reques.get("/public-api/users/2027");
		
		
		JsonPath jsonGetAfter = responseGetAfter.jsonPath();
		String statusGetOfUserAfter = jsonGetAfter.getJsonObject("result.status");
		System.out.println("Getting another user data:: "+jsonGetAfter.get("result.last_name"));
		//System.out.println("After Updating Status of user : "+statusGetOfUserAfter);
		Assert.assertEquals(statusPutOfUser, statusGetOfUserAfter);
		
	
		System.out.println("Status of a user got changed");
		
		
		
	}

}
