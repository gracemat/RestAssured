package com.restapi.tests;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
public class GetSingleUserSchemaTest 
{
	@Test
public void getSingleUSerSchemaTest()
{
		RestAssured.baseURI = "";
		RestAssured.given().log().all()
			.contentType("application/json")
			.header("Authorization","Bearer 2EkM0agfe828Kt2-DGbGqt9DmAqOj0GjsdXd")
			
			.when().log().all()
				.get("https://gorest.co.in/public-api/users/2027")
				.then().log().all()
				.assertThat()
				.body(matchesJsonSchemaInClasspath("SingleUSerSchema.json"));
}
	
}
