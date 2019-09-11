package com.restapi.tests;

import static io.restassured.RestAssured.*;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class GETCallRestAPI 
{
	@Test
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
}
