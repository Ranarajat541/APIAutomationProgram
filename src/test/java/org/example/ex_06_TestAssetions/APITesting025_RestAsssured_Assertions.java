package org.example.ex_06_TestAssetions;


import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import io.qameta.allure.Description;

public class APITesting025_RestAsssured_Assertions {

    RequestSpecification requestSpecification;
    Response response;
    ValidatableResponse validatableResponse;
    String token;
    Integer bookingID;



    @Owner("Rajat")
    @Severity(SeverityLevel.CRITICAL)
    @Description("TC#1  Verify that the Create Booking is working fine, booking ID is not null")
    @Test

    public void test_createBooking_POST(){

        String payload = "{\n" +
                "    \"firstname\" : \"rajat\",\n" +
                "    \"lastname\" : \"rana\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2018-01-01\",\n" +
                "        \"checkout\" : \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";

        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/booking");
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.body(payload).log().all();

        Response response = requestSpecification.when().post();

        // Get Validatable response to perform validation
        validatableResponse = response.then().log().all();

        // Rest Assured.
        validatableResponse.statusCode(200);


        // firstname == rajat , Last Name = rana
        // Booking ID shouldn't null


        validatableResponse.body("booking.firstname", Matchers.equalTo("rajat"));
        validatableResponse.body("booking.lastname", Matchers.equalTo("rana"));
        validatableResponse.body("booking.depositpaid",Matchers.equalTo(true));
        validatableResponse.body("bookingid",Matchers.notNullValue());
    }
}
