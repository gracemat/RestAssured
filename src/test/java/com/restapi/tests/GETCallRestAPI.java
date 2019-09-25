package com.restapi.tests;

import static io.restassured.RestAssured.*;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class GETCallRestAPI 
{
	@Test(priority = 1 , enabled = false)
	public void getUsers_APITest()
	{
	RestAssured.baseURI = "https://gorest.co.in";
	given().log().all()
	.contentType("application/json")
		.header("Authorization","Bearer tZOFX-5H7fWfexq4QK2rNY84Dolble_k-y3W")
	.when().log().all()
		.get("/public-api/users/")
	.then().log().all()
		.statusCode(200)
		.and()
			.header("Server", "nginx")
			.header("X-Rate-Limit-Limit","59");
	}
	
	@Test(priority = 2, enabled = false)
	public void getOneUserDetails_APITest()
	{
	RestAssured.baseURI = "https://gorest.co.in";
	given().log().all()
		.contentType("application/json")
		.header("Authorization","Bearer tZOFX-5H7fWfexq4QK2rNY84Dolble_k-y3W")
	.when().log().all()
		.get("/public-api/users/?first_name=john")
	.then().log().all()
		.statusCode(200)
		.and()
			.header("Server", "nginx")
			.header("X-Rate-Limit-Limit","59");
	}
	
	@Test(priority = 3 )
	public void getQuerryParameter_Users_APITest()
	{
	RestAssured.baseURI = "https://gorest.co.in";
	given().log().all()
		.contentType("application/json")
		.header("Authorization","Bearer tZOFX-5H7fWfexq4QK2rNY84Dolble_k-y3W")
		.queryParam("first_name", "john")
		.queryParam("status", "inactive")
	.when().log().all()
		.get("/public-api/users/")
	.then().log().all()
		.statusCode(200)
		.and()
			.header("Server", "nginx")
			.header("X-Rate-Limit-Limit","59");
	}
	
	
	
	
}
