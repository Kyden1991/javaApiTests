package api;

import api.pojoClasses.ResponseOrderList;
import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GettingOrderListTest {
    @Before
    public void baseUrl() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru";
    }

    @Description("HTTP 202 status code is returned by the GET/api/v1/ method with valid request parameters and return list of orders like in json schema")
    @Test
    public void getOrderListReturnStatusCode200AndListOfOrders(){
        ResponseOrderList orderList = given()
                .get("/api/v1/orders?limit=1&page=0&nearestStation=[\"110\"]")
                .body().as(ResponseOrderList.class);
        System.out.println(orderList);

//        response.then().assertThat().statusCode(200)
//                .body(response, Matchers.equalTo(responseBody)).log().body();
//        JSONObject responseBody = new JSONObject(response.getBody().as(ResponseOrderList.class));
    }
}
