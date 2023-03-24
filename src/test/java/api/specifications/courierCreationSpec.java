package api.specifications;

import api.BaseClass;
import apiTestsStuding.dto.CreateCourierDto;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static api.BaseClass.responseStatusCode;
import static apiTestsStuding.constants.Constants.BASE_URL;
import static apiTestsStuding.constants.Constants.PATH_COURIER_CREATION;
import static io.restassured.RestAssured.given;

public class courierCreationSpec {
    public static Response createCourier(String login, int password, String firstName) {
        CreateCourierDto createCourier = new CreateCourierDto(login, password, firstName);
        return given()
                .spec(BaseClass.requestSpec())
                        .body(createCourier).log().all()
                        .when()
                        .post(PATH_COURIER_CREATION);
    }
}
