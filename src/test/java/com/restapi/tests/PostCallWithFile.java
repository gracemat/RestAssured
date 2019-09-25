package com.restapi.tests;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PostCallWithFile 
{
@Test
public void  getTokenWithFile_Test()
{
	RestAssured.baseURI = "https://restful-booker.herokuapp.com";
	RequestSpecification req = RestAssured.given().log().all();
	req.contentType("application/json");
	
	File file = new File("C:\\Users\\prave\\eclipse-workspace\\RestAssuredStart\\src\\test\\java\\com\\restapi\\tests\\credentials.json");
	
	req.body(file);
	Response resp = req.post("/auth");
	System.out.println(resp.prettyPrint());
	JsonPath jsonObj = resp.jsonPath();
	System.out.println("TOKEN == "+jsonObj.get("token"));
	
	Assert.assertEquals(resp.getStatusCode(), HttpStatusCodes.HTTP_STATUS_CODE_200);
	Assert.assertNotNull(jsonObj.get("token"));
}
@Test
public void  getTokenWith_InvalidCred_File_Test()
{
	RestAssured.baseURI = "https://restful-booker.herokuapp.com";
	RequestSpecification req = RestAssured.given().log().all();
	req.contentType("application/json");
	
	File file = new File("C:\\Users\\prave\\eclipse-workspace\\RestAssuredStart\\src\\test\\java\\com\\restapi\\tests\\WrongCredentials.json");
	
	req.body(file);
	Response resp = req.post("/auth");
	System.out.println(resp.prettyPrint());
	JsonPath jsonObj = resp.jsonPath();
	System.out.println("TOKEN == "+jsonObj.get("token"));
	
	Assert.assertEquals(resp.getStatusCode(), HttpStatusCodes.HTTP_STATUS_CODE_200);
	
	Assert.assertEquals(jsonObj.get("reason"), HttpStatusCodes.wrongCredentialsMsg);
	
}


}
