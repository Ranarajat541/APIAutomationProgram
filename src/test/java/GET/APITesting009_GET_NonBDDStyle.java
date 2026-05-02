package GET;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.testng.annotations.Test;

public class APITesting009_GET_NonBDDStyle {
    RequestSpecification r;
    Response response;
    ValidatableResponse vr;


    @Test
    public void test_GET_NonBdd() {
        String pincode = "110048";

        //Given
        r = RestAssured.given();
        r.baseUri("https://api.zippopotam.us");
        r.basePath("/IN/" + pincode);

        //when
        response = r.when().log().all().get();

        System.out.println(response.asString());

        //Then
        vr = response.then().log().all();
        vr.statusCode(200);

    }

    @Test
    public void test_GET_NonBDD_Neagtive() {

        String pincode = "@";
        // GIVEN
        r = RestAssured.given();
        r.baseUri("https://api.zippopotam.us");
        r.basePath("/IN/" + pincode);

        // WHEN
        response = r.when().log().all().get();

        System.out.println(response.asString());

        // THEN
        vr = response.then().log().all();
        vr.statusCode(404);


    }
}
