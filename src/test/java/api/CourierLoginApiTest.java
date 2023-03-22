package api;

import io.qameta.allure.Description;
import dto.LoginCourierDto;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import javax.swing.text.FlowView;
import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class CourierLoginApiTest {
    @Before
    public void baseUrl() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru";
    }

    @Description("HTTP 200 status code is returned by the POST/api/v1/courier/login method with valid request parameters")
    @Test
    public void postCourierCreationReturnStatusCode200() {
        LoginCourierDto authorization = new LoginCourierDto("ninja", 1234);
        Response response =
                given()
                        .body(authorization).log().all()
                        .when()
                        .post("/api/v1/courier/login");
        response.then().log().all().assertThat().statusCode(200);
    }

    @Description("HTTP 200 status code and correct body is returned by the POST/api/v1/courier/login method with valid request parameters")
    @Test
    public void postCourierCreationReturnStatusCode200WithCorrectBody() {
        LoginCourierDto authorization = new LoginCourierDto("ninja", 1234);
        Response response =
                given()
                        .body(authorization).log().all()
                        .when()
                        .post("/api/v1/courier/login");
        response.then().log().all().assertThat().statusCode(200).body("id", notNullValue());
    }

    @Description("HTTP 400 status code is returned by the POST/api/v1/courier/login method without required login field")
    @Test
    public void postCourierCreationReturnStatusCode400WithoutLoginField() {
        String json = "{\"password\": \"1234\"}";
        Response response =
                given()
                        .body(json).log().all()
                        .when()
                        .post("/api/v1/courier/login");
        response.then().log().all().assertThat().statusCode(400);
    }

    @Description("HTTP 400 status code is returned by the POST/api/v1/courier/login method without required password field")
    @Test
    public void postCourierCreationReturnStatusCode400WithoutPasswordField() {
        String json = "{\"login\": \"Art\"}";
        Response response =
                given()
                        .body(json).log().all()
                        .when()
                        .post("/api/v1/courier/login");
        response.then().log().all().assertThat().statusCode(400);
    }

    @Description("HTTP 404 status code is returned by the POST/api/v1/courier/login method with non exist login")
    @Test
    public void postCourierCreationReturnStatusCode404WithNonExistLogin() {
        LoginCourierDto authorization = new LoginCourierDto("samurai", 1234);
        Response response =
                given()
                        .body(authorization).log().all()
                        .when()
                        .post("/api/v1/courier/login");
        response.then().log().all().assertThat().statusCode(404);
    }

    @Description("HTTP 404 status code is returned by the POST/api/v1/courier/login method with non exist password")
    @Test
    public void postCourierCreationReturnStatusCode404WithNonExistPassword() {
        LoginCourierDto authorization = new LoginCourierDto("ninja", 12345);
        Response response =
                given()
                        .body(authorization).log().all()
                        .when()
                        .post("/api/v1/courier/login");
        response.then().log().all().assertThat().statusCode(404);
    }
}
