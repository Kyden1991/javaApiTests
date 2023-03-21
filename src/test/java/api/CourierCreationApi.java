package api;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CourierCreationApi {
    @Before
    public void baseUrl() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru";
    }

    @After
    public void deleteTestCourier() {
        String json = "{\"id\": 3}";
        Response response =
                given()
                        .body(json).log().all()
                        .when()
                        .post("/api/v1/courier/:id");
        response.then().log().all().assertThat().statusCode(200);
    }


    @Test
    public void postCourierCreationReturnStatusCode201() {
        File json = new File("src/main/resources/CreateCourier.json");
        Response response =
                given()
                        .body(json).log().all()
                        .when()
                        .post("/api/v1/courier");
        response.then().log().all().assertThat().statusCode(201);
    }

    @Test
    public void postCourierCreationReturnStatusCode201WithCorrectBody() {
        File json = new File("src/main/resources/CreateCourier.json");
        Response response =
                given()
                        .body(json).log().all()
                        .when()
                        .post("/api/v1/courier");
        response.then().log().all().assertThat().statusCode(201).body("ok", equalTo("true"));
    }

    @Test
    public void postCourierCreationReturnStatusCode400WithoutRequiredField() {
        String json = "{\"password\": \"1234\"," +
                "\"firstName\": \"saske\"}";
        Response response =
                given()
                        .body(json).log().all()
                        .when().post("/api/v1/courier");
        response.then().log().all().assertThat().statusCode(400);
    }

    // todo rename variable names
    @Test
    public void postCourierCreationReturnStatusCode409WithDuplicateLogin() {
        File json = new File("src/main/resources/CreateCourier.json");
        Response response1 =
                given()
                        .body(json).log().all()
                        .when()
                        .post("/api/v1/courier");
        response1.then().log().all().assertThat().statusCode(201);

        Response response2 =
                given()
                        .body(json).log().all()
                        .when()
                        .post("/api/v1/courier");
        response2.then().log().all().assertThat().statusCode(409);
    }
}
