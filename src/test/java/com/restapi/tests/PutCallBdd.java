package com.restapi.tests;

import org.hamcrest.text.MatchesPattern;
import org.testng.annotations.Test;

//import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;


public class PutCallBdd
{
	@Test
	public void putcallTest()
	{
	RestAssured.baseURI = "https://gorest.co.in";
	given().log().all()
		.contentType("application/json")
		.header("Authorization","Bearer 2EkM0agfe828Kt2-DGbGqt9DmAqOj0GjsdXd")
	.when().log().all()
		.put("/public-api/users/2027")
		//.put
	.then().log().all()
		.statusCode(200);

		//.assertThat()
		
		//.body(matc)//		.and()
//		.assertThat()
		//.body(("status","active"));
		
	}
}
