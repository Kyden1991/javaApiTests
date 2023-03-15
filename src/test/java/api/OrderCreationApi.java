package api;

import dto.CreateOrderDto;
import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(Parameterized.class)
public class OrderCreationApi {
    @Before
    public void baseUrl() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru";
    }
    private final String color;


    public OrderCreationApi(String color) {
        this.color = color;
    }

//    List<String> colors = new ArrayList<>();
//    colors.add(new("BLACK", "GREY"))
//static String[] colors = {"BLACK", "GREY", ""};
    @Parameterized.Parameters
    public static Object[][] setColorForOrderCreation() {
        return new Object[][]{
                {"BLACK"},
                {"GREY"},
                {"BLACK", "GREY"},
                {""}
        };
    }

    @Test
    @Description("HTTP 201 status code is returned by the POST/api/v1/courier method with valid request parameters and different colors")
    public void postOrderCreationReturnStatusCode201WithDiffParameters() {
        CreateOrderDto order = new CreateOrderDto("Art",
                "Kaz",
                "MARRO",
                1,
                "+12345678910",
                2,
                "2024-01-31",
                "Test",
                color);
        Response response =
                given()
                        .body(order).log().all()
                        .when()
                        .post("/api/v1/orders");
        response.then().log().all().assertThat().statusCode(201).body("track", notNullValue());
    }
}