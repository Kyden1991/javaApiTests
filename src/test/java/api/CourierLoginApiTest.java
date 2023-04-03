package api;

import api.BaseTest.BaseMethods;
import api.BaseTest.BaseSpecClass;
import api.specifications.CourierLoginSpec;
import io.qameta.allure.Description;
import apiTestsStuding.dto.LoginCourierDto;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static api.BaseTest.BaseMethods.randomPassword;
import static api.BaseTest.BaseMethods.randomString;
import static api.BaseTest.BaseSpecClass.responseStatusCode;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class CourierLoginApiTest extends BaseMethods {

    @Description("HTTP 200 status code is returned by the POST/api/v1/courier/login method with valid request parameters")
    @Test
    public void postCourierLoginReturnStatusCode200() {
        BaseSpecClass.installSpecification(responseStatusCode(200));

       CourierLoginSpec.courierLogin("hokage", 12345);
    }

    @Description("HTTP 200 status code and correct body is returned by the POST/api/v1/courier/login method with valid request parameters")
    @Test
    public void postCourierLoginReturnStatusCode200WithCorrectBody() {
        BaseSpecClass.responseWithBodyAssert(CourierLoginSpec.courierLogin("hokage", 12345),
                200, "id", notNullValue());
    }

    @Description("HTTP 400 status code is returned by the POST/api/v1/courier/login method without required login field")
    @Test
    public void postCourierLoginReturnStatusCode400WithoutLoginField() {
        BaseSpecClass.installSpecification(responseStatusCode(400));

        CourierLoginSpec.courierLoginWithoutLoginField(12345);
    }

    @Description("HTTP 400 status code is returned by the POST/api/v1/courier/login method without required password field")
    @Test
    public void postCourierLoginReturnStatusCode400WithoutPasswordField() {
        BaseSpecClass.installSpecification(responseStatusCode(400));

        CourierLoginSpec.courierLoginWithoutPasswordField("hokage");
    }

    @Description("HTTP 400 status code is returned by the POST/api/v1/courier/login method with invalid password field")
    @Test
    public void postCourierLoginReturnStatusCode400WithInvalidPassword() {
        BaseSpecClass.installSpecification(responseStatusCode(404));

        CourierLoginSpec.courierLogin("hokage", -123);
    }

    @Description("HTTP 404 status code is returned by the POST/api/v1/courier/login method with non exist login")
    @Test
    public void postCourierLoginReturnStatusCode404WithNonExistLogin() {
        BaseSpecClass.installSpecification(responseStatusCode(404));

        CourierLoginSpec.courierLogin("qwerty", 12345);
    }

    @Description("HTTP 404 status code is returned by the POST/api/v1/courier/login method with non exist password")
    @Test
    public void postCourierLoginReturnStatusCode404WithNonExistPassword() {
        BaseSpecClass.installSpecification(responseStatusCode(404));

        CourierLoginSpec.courierLogin("hokage", 00001);
    }
}
