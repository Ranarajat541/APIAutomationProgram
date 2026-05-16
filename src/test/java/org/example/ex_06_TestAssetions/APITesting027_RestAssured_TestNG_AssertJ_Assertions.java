package org.example.ex_06_TestAssetions;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class APITesting027_RestAssured_TestNG_AssertJ_Assertions {


    RequestSpecification requestSpecification;
    ValidatableResponse validatableResponse;
    Response response;
    String token;
    Integer bookingId;

    @Test
    public void test_POST() {

        String payload_POST = "{\n" +
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
        requestSpecification.baseUri("https://restful-booker.herokuapp.com/");
        requestSpecification.basePath("/booking");
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.body(payload_POST).log().all();

        Response response = requestSpecification.when().post();


        // Get Validatable response to perform validation
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);

        // Rest Assured -> import org.hamcrest.Matchers; %4-%5
        // Matchers.equalto()

        validatableResponse.body("booking.firstname", Matchers.equalTo("rajat"));
        validatableResponse.body("booking.lastname", Matchers.equalTo("rana"));
        validatableResponse.body("booking.depositpaid", Matchers.equalTo(true));
        validatableResponse.body("bookingid", Matchers.notNullValue());

        // TestNG - Extract the details of the firstname, bookingId, lastname from Response.

        bookingId = response.then().extract().path("bookingid");
        String firstname = response.then().extract().path("booking.firstname");
        String lastname = response.then().extract().path("booking.lastname");



        // TestNG Assertions - 75%
        // SoftAssert vs
        // HardAssert -
        // This means that if any assertion fails,
        // the remaining statements in that test method will not be executed.
        Assert.assertEquals(firstname,"rajat");
        Assert.assertEquals(lastname,"rana");
        Assert.assertNotNull(bookingId);



        //
        // AssertJ( 3rd- Lib to Assertions) - 20%
        assertThat(bookingId).isNotZero().isNotNull().isPositive();
        assertThat(firstname).isEqualTo("rajat").isNotBlank().isNotEmpty().isNotNull().isAlphanumeric();

        //        String s = ""; //Empty
        //        String s2 = " "; //Blank





    }

}

