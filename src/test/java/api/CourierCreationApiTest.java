package api;

import api.BaseTest.BaseMethods;
import api.BaseTest.BaseSpecClass;
import api.specifications.CourierCreationSpec;
import io.qameta.allure.Description;
import org.junit.Test;

import static api.BaseTest.BaseSpecClass.responseStatusCode;
import static api.BaseTest.BaseMethods.randomPassword;
import static api.BaseTest.BaseMethods.randomString;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CourierCreationApiTest extends BaseMethods {

    @Description("HTTP 201 status code is returned by the POST/api/v1/courier method with valid request parameters in all required fields")
    @Test
    public void postCourierCreationReturnStatusCode201() {
        BaseSpecClass.installSpecification(responseStatusCode(201));

        CourierCreationSpec.createCourier(randomString(), randomPassword(), randomString());
    }

    @Description("HTTP 409 status code is returned by the POST/api/v1/courier method when create a duplicate courier")
    @Test
    public void postCourierCreationReturnStatusCode409WithDuplicateLogin() {

        BaseSpecClass.installSpecification(responseStatusCode(409));

        CourierCreationSpec.createCourier("hokage", 12345, "naruto");
    }

    @Description("HTTP 201 status code and correct body is returned by the POST/api/v1/courier method with valid request parameters")
    @Test
    public void postCourierCreationReturnStatusCode201WithCorrectBody() {
        BaseSpecClass.responseWithBodyAssert(CourierCreationSpec
                        .createCourier(randomString(), randomPassword(), randomString()),
                        201, "ok", equalTo("true"));
    }

    @Description("HTTP 400 status code is returned by the POST/api/v1/courier method without login field")
    @Test
    public void postCourierCreationReturnStatusCode400WithoutLoginRequiredField() {
        BaseSpecClass.installSpecification(responseStatusCode(400));

        CourierCreationSpec.createCourierWithoutLoginField(randomPassword(), randomString());
    }

    @Description("HTTP 400 status code is returned by the POST/api/v1/courier method without password field")
    @Test
    public void postCourierCreationReturnStatusCode400WithoutPasswordRequiredField() {
        BaseSpecClass.installSpecification(responseStatusCode(400));

        CourierCreationSpec.createCourierWithoutPasswordField(randomString(), randomString());
    }

    @Description("HTTP 400 status code is returned by the POST/api/v1/courier method without first name required field")
    @Test
    public void postCourierCreationReturnStatusCode400WithoutFirstNameRequiredField() {
        BaseSpecClass.installSpecification(responseStatusCode(400));

        CourierCreationSpec.createCourierWithoutFirstnameField(randomString(), randomPassword());
    }
}


