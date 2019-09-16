package com.restapi.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GETCall_NonBDD {
	
	@Test(enabled = false)
	public void getUsersCall()
	{
		RestAssured.baseURI = "https://gorest.co.in";
		RequestSpecification req = RestAssured.given().log().all();
		req.header("Authorization","Bearer tZOFX-5H7fWfexq4QK2rNY84Dolble_k-y3W");
		
		Response resp = req.get("/public-api/users/");
		//System.out.println(resp);
		//System.out.println(resp.asString());//
		System.out.println(resp.prettyPrint());
		int statusCode = resp.getStatusCode();
		Assert.assertEquals(statusCode, 200);
		String server = resp.getHeader("Server");
		Assert.assertEquals(server, "nginx");
	}
	
	@Test
	public void getUsers_QuerryCall()
	{
		RestAssured.baseURI = "https://gorest.co.in";
		RequestSpecification request = RestAssured.given();
		request.header("Authorization","Bearer tZOFX-5H7fWfexq4QK2rNY84Dolble_k-y3W");
		request.queryParam("first_name", "Tom");
		request.queryParam("Status", "active");
		
		Response resp = request.get("/public-api/users/?first_name=Tom&Status=active");
		//System.out.println(resp);
		//System.out.println(resp.asString());//
		System.out.println(resp.prettyPrint());
		int statusCode = resp.getStatusCode();
		Assert.assertEquals(statusCode, 200);
		String server = resp.getHeader("Server");
		Assert.assertEquals(server, "nginx");
	}
}
