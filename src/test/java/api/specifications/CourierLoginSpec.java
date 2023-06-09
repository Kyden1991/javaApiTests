package api.specifications;

import api.BaseTest.BaseSpecClass;
import apiTestsStuding.dto.LoginCourierDto;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matcher;

import static apiTestsStuding.constants.Constants.PATH_COURIER_LOGIN;
import static io.restassured.RestAssured.given;

public class CourierLoginSpec {
    public static Response courierLogin(String login, int password) {
        LoginCourierDto loginCourierDto = new LoginCourierDto(login, password);
        return given()
                .spec(BaseSpecClass.requestSpec())
                .body(loginCourierDto).log().all()
                .when()
                .post(PATH_COURIER_LOGIN);
    }

    public static Response courierLoginWithoutLoginField(int password) {
        LoginCourierDto loginCourierDto = new LoginCourierDto(password);
        return given()
                .spec(BaseSpecClass.requestSpec())
                .body(loginCourierDto).log().all()
                .when()
                .post(PATH_COURIER_LOGIN);
    }

    public static Response courierLoginWithoutPasswordField(String login) {
        LoginCourierDto loginCourierDto = new LoginCourierDto(login);
        return given()
                .spec(BaseSpecClass.requestSpec())
                .body(loginCourierDto).log().all()
                .when()
                .post(PATH_COURIER_LOGIN);
    }
}
