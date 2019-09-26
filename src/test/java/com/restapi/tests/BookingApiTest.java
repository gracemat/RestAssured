package com.restapi.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.restapi.booking.BookingApi;
import com.restapi.booking.Bookingdates;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BookingApiTest
{
	@Test
	public void doBookingTest()
	{
		String bookingJson = null;
	RestAssured.baseURI = "https://restful-booker.herokuapp.com";
	RequestSpecification bookingReq = RestAssured.given().log().all();
	bookingReq.contentType("application/json");
	
	Bookingdates bd = new Bookingdates("2018-01-02","2019-01-02");
	
	BookingApi booking = new BookingApi("Prady","M",151,false,bd,"breakfast-light");
	
	ObjectMapper mapper = new ObjectMapper();
	
	try {
		bookingJson = mapper.writeValueAsString(booking);
	} catch (JsonProcessingException e) {
		e.printStackTrace();
	}
	
	System.out.println(bookingJson);
	
	bookingReq.body(bookingJson);
	
	Response bookingResponse = bookingReq.post("/booking");
	
	System.out.println(bookingResponse.prettyPrint());
	
System.out.println(bookingResponse.prettyPrint());

JsonPath js = bookingResponse.jsonPath();

int statuscodeOfResponse = bookingResponse.getStatusCode();

Assert.assertEquals(statuscodeOfResponse, HttpStatusCodes.HTTP_STATUS_CODE_200);

int bookingId = js.get("bookingid");
Assert.assertNotNull(bookingId);

System.out.println(js.get("booking.lastname"));

Assert.assertEquals(js.get("booking.firstname"), booking.getFirstname());
Assert.assertEquals(js.get("booking.lastname"), booking.getLastname());
Assert.assertEquals(js.get("booking.totalprice"), booking.getTotalprice());
Assert.assertEquals(js.get("booking.depositpaid"), booking.isDepositpaid());
Assert.assertEquals(js.get("booking.additionalneeds"), booking.getAdditionalneeds());

Assert.assertEquals(js.get("booking.bookingdates.checkin"), bd.getCheckin());
Assert.assertEquals(js.get("booking.bookingdates.checkout"), bd.getCheckout());

	}
}
