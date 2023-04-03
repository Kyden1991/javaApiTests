package api.BaseTest;

import api.specifications.CourierCreationSpec;
import api.specifications.CourierLoginSpec;
import apiTestsStuding.dto.DeleteCourierDto;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.security.SecureRandom;

import org.junit.After;
import org.junit.Before;
import static apiTestsStuding.constants.Constants.BASE_URL;

import static io.restassured.RestAssured.given;


public class BaseMethods {
    public static List<Integer> createdCourierIds = new ArrayList<>();
//    @Before
//    public void prepareCourier() {
//        Response response1 =
//            CourierCreationSpec.createCourier("hokage", 12345, "naruto");
//        response1.then().log().all();
//
//        Response response2 =
//            CourierLoginSpec.courierLogin("hokage", 12345);
//        response2.then().log().all();
//
//        JSONObject responseBody = new JSONObject(response2.getBody().asString());
//        createdCourierIds.add(responseBody.getInt("id"));
//    }
//
//    @After
//    public void deleteTestCourier() {
//        DeleteCourierDto courierId = new DeleteCourierDto(createdCourierIds.get(0));
//        Response response =
//                given()
//                        .body(courierId).log().all()
//                        .when()
//                        .delete("/api/v1/courier/:id");
//        response.then().log().all().assertThat().statusCode(200);
//    }

    public static String randomString() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    public static int randomPassword() {
        // Instance of SecureRandom class
        SecureRandom rand = new SecureRandom();
        // Setting the upper bound to generate
        // the random numbers between the specific range
        int upperbound = 999999;
        return rand.nextInt(upperbound);
    }

}
