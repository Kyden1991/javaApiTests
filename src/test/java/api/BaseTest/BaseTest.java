package api.BaseTest;

import apiTestsStuding.dto.CreateCourierDto;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import org.junit.Before;

import static apiTestsStuding.constants.Constants.BASE_URL;

import static io.restassured.RestAssured.given;

public class BaseTest {
    @Before
    public void prepareCourier() {
        courierCreation();
    }

    public void courierCreation() {
        RestAssured.baseURI = BASE_URL;

    }

}
