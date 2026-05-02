package ex_01_RA_basics;

import io.restassured.RestAssured;


public class Lab_001_RA {
    public static void main(String[] args) {
        RestAssured.given()
                .baseUri("https://restful-booker.herokuapp.com/ping")
                .when()
                .get()
                .then()
                .log().all().statusCode(201);


    }
}
