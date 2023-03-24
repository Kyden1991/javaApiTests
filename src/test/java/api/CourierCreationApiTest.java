package api;

import api.specifications.courierCreationSpec;
import io.qameta.allure.Description;
import apiTestsStuding.dto.CreateCourierDto;
import apiTestsStuding.dto.DeleteCourierDto;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import java.util.ArrayList;
import java.util.List;

import static api.specifications.courierCreationSpec.createCourier;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CourierCreationApiTest {

    public static List<Integer> createdCourierIds = new ArrayList<>();

//    @Before
//    public void baseUrl() {
//        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru";
//
//        CreateCourierDto createCourier = new CreateCourierDto("ninja", 1234, "saske");
//        Response response =
//                given()
//                        .body(createCourier).log().all()
//                        .when()
//                        .post("/api/v1/courier");
//        response.then().log().all().assertThat().statusCode(201);
//
//        JSONObject responseBody = new JSONObject(response.getBody().asString());
//        createdCourierIds.add(responseBody.getInt("id"));
//    }

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


    @Description("HTTP 201 status code is returned by the POST/api/v1/courier method with valid request parameters in all required fields")
    @Test
    public void postCourierCreationReturnStatusCode201() {
        BaseClass.installSpecification(BaseClass.responseStatusCode(201));

        courierCreationSpec.createCourier("hokages", 12345, "naruto");
    }

    @Description("HTTP 409 status code is returned by the POST/api/v1/courier method when create a duplicate courier")
    @Test
    public void postCourierCreationReturnStatusCode409WithDuplicateLogin() {
        BaseClass.installSpecification(BaseClass.responseStatusCode(201));

        courierCreationSpec.createCourier("hokages", 12345, "naruto");
    }

    @Description("HTTP 201 status code and correct body is returned by the POST/api/v1/courier method with valid request parameters")
    @Test
    public void postCourierCreationReturnStatusCode201WithCorrectBody() {
        CreateCourierDto createCourier = new CreateCourierDto("hokage", 12345, "naruto");
        Response response =
                given()
                        .body(createCourier).log().all()
                        .when()
                        .post("/api/v1/courier");
        response.then().log().all().assertThat().statusCode(201).body("ok", equalTo("true"));
    }

    @Description("HTTP 400 status code is returned by the POST/api/v1/courier method without login field")
    @Test
    public void postCourierCreationReturnStatusCode400WithoutLoginRequiredField() {
        String json = "{\"password\": \"1234\"," +
                "\"firstName\": \"saske\"}";
        Response response =
                given()
                        .body(json).log().all()
                        .when().post("/api/v1/courier");
        response.then().log().all().assertThat().statusCode(400);
    }

    @Description("HTTP 400 status code is returned by the POST/api/v1/courier method without password field")
    @Test
    public void postCourierCreationReturnStatusCode400WithoutPasswordRequiredField() {
        String json = "{\"login\": \"ninja\"," +
                "\"firstName\": \"saske\"}";
        Response response =
                given()
                        .body(json).log().all()
                        .when().post("/api/v1/courier");
        response.then().log().all().assertThat().statusCode(400);
    }

    @Description("HTTP 400 status code is returned by the POST/api/v1/courier method without first name required field")
    @Test
    public void postCourierCreationReturnStatusCode400WithoutFirstNameRequiredField() {
        String json = "{\"login\": \"ninja\","  +
                "\"password\": \"1234\"}";
        Response response =
                given()
                        .body(json).log().all()
                        .when().post("/api/v1/courier");
        response.then().log().all().assertThat().statusCode(400);
    }
}


