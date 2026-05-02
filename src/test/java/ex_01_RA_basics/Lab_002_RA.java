package ex_01_RA_basics;

import io.restassured.RestAssured;

import java.util.Scanner;

public class Lab_002_RA {
    public static void main(String[] args) {


        //Gherkin syntax
        //Given() -> Pre Req URl, header, Auth, Body.....
        //When() -> HTTP method?  - Get/Post/Put/Patch, Delete..
        //Then () - Validation  -> 200 ok ,firstname == Rajat

        //Full URL - https://api.zippopotam.us/90210
        //base URI - https://api.zippopotam.us
        //bath path - /us/90210


        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the pincode!");
        String pincode = sc.next();



        RestAssured.given()
                .baseUri("http://api.zippopotam.us/IN/560016")
        .basePath("/IN/" +pincode)
                .when()
                .get()
                .then().log().all()
                .statusCode(200);


    }
}
