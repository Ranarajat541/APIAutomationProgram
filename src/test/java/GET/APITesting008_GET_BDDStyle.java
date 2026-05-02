package GET;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class APITesting008_GET_BDDStyle {

    @Test
    public void test_GET_positive(){
        String pin = "110048";
        RestAssured
                .given()
                .baseUri("https://api.zippopotam.us")
                .basePath("/IN/" + pin)

                .when()
                .log().all().get()

                .then()
                .log().all().statusCode(200);


    }
}
