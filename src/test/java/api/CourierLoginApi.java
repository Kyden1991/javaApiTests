package api;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import javax.swing.text.FlowView;
import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class CourierLoginApi {
    @Before
    public void baseUrl() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru";
    }

    @Test
    @Description("HTTP 201 status code is returned by the POST/api/v1/courier/login method with valid request parameters")
    public void postCourierCreationReturnStatusCode200() {
        File json = new File("src/test/java/api/CourierLoginApi.java");
        Response response =
                given()
                        .body(json).log().all()
                        .when()
                        .post("/api/v1/courier/login");
        response.then().log().all().assertThat().statusCode(200);
    }

    @Test
    @Description("HTTP 201 status code is returned by the POST/api/v1/courier method with valid request parameters return id in body")
    public void postCourierCreationReturnStatusCode200WithCorrectBody() {
        File json = new File("src/test/java/api/CourierLoginApi.java");
        Response response =
                given()
                        .body(json).log().all()
                        .when()
                        .post("/api/v1/courier/login");
        response.then().log().all().assertThat().statusCode(200).body("id", notNullValue());
    }

    @Test
    @Description("HTTP 400 status code is returned by the POST/api/v1/courier/login method without login field")
    public void postCourierCreationReturnStatusCode400WithoutLoginField() {
        String json = "{\"password\": \"1234\"}";
        Response response =
                given()
                        .body(json).log().all()
                        .when()
                        .post("/api/v1/courier/login");
        response.then().log().all().assertThat().statusCode(400);
    }

    @Test
    @Description("HTTP 400 status code is returned by the POST/api/v1/courier/login method without password field")
    public void postCourierCreationReturnStatusCode400WithoutPasswordField() {
        String json = "{\"login\": \"Art\"}";
        Response response =
                given()
                        .body(json).log().all()
                        .when()
                        .post("/api/v1/courier/login");
        response.then().log().all().assertThat().statusCode(400);
    }

    @Test
    @Description("HTTP 201 status code is returned by the POST/api/v1/courier method with non-existed login name")
    public void postCourierCreationReturnStatusCode404() {
        String json = "{\n" +
                "    \"login\": \"dcdc\",\n" +
                "    \"password\": \"1234\"\n" +
                "}";
        Response response =
                given()
                        .body(json).log().all()
                        .when()
                        .post("/api/v1/courier/login");
        response.then().log().all().assertThat().statusCode(404);
    }
}
