package api;

import api.pojoClasses.ResponseOrderList;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GettingOrderList {
    @Before
    public void baseUrl() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru";
    }

    @Test
    public void getOrderListReturnStatusCode200AndListOfOrders(){
        ResponseOrderList orderList = given()
                .get("/api/v1/orders?limit=1&page=0&nearestStation=[\"110\"]")
                .body().as(ResponseOrderList.class);
    }
}
