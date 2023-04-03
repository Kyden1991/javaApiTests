package api.specifications;

import api.BaseTest.BaseSpecClass;
import apiTestsStuding.dto.CreateCourierDto;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matcher;

import static apiTestsStuding.constants.Constants.PATH_COURIER_CREATION;
import static io.restassured.RestAssured.given;

public class CourierCreationSpec {
    public static Response createCourier(String login, int password, String firstName) {
        CreateCourierDto createCourier = new CreateCourierDto(login, password, firstName);
        return given()
                .spec(BaseSpecClass.requestSpec())
                        .body(createCourier).log().all()
                        .when()
                        .post(PATH_COURIER_CREATION);
    }

    public static Response createCourierWithoutLoginField(int password, String firstName) {
        CreateCourierDto createCourier = new CreateCourierDto(password, firstName);
        return given()
                .spec(BaseSpecClass.requestSpec())
                .body(createCourier).log().all()
                .when()
                .post(PATH_COURIER_CREATION);
    }

    public static Response createCourierWithoutPasswordField(String login, String firstName) {
        CreateCourierDto createCourier = new CreateCourierDto(login, firstName);
        return given()
                .spec(BaseSpecClass.requestSpec())
                .body(createCourier).log().all()
                .when()
                .post(PATH_COURIER_CREATION);
    }

    public static Response createCourierWithoutFirstnameField(String login, int password) {
        CreateCourierDto createCourier = new CreateCourierDto(login, password);
        return given()
                .spec(BaseSpecClass.requestSpec())
                .body(createCourier).log().all()
                .when()
                .post(PATH_COURIER_CREATION);
    }
}
