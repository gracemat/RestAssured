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
	given()
	.contentType("application/json")
	.header("Authorization","Bearer tZOFX-5H7fWfexq4QK2rNY84Dolble_k-y3W")
	.when()
	.get("/public-api/users/")
	.then()
	.statusCode(200)
	.and()
	.header("Server", "nginx")
	.header("X-Rate-Limit-Limit","59");
	}
}
