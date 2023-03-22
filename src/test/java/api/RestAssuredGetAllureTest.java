package api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class RestAssuredGetAllureTest {

    String bearerToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2M2Y0YjZkNDYzNDYzYTAwM2Q0N2Q0MWEiLCJpYXQiOjE2Nzk0MTAzMDAsImV4cCI6MTY4MDAxNTEwMH0.5yzQJe9PSCJ59SF29oOKFGlIb9srerQ-mUDLJ5mRXOw";

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-mesto.praktikum-services.ru";
    }

    @Test
    public void getMyInfoStatusCode() {
        given()
                .auth().oauth2(bearerToken)
                .get("/api/users/me")
                .then().statusCode(200);
    }

    @Test
    public void checkUserName() {
        given()
                .auth().oauth2(bearerToken)
                .get("/api/users/me")
                .then().assertThat().body("data.name",equalTo("Жак-Ив Кусто"));
    }

    @Test
    public void checkUserNameAndPrintResponseBody() {

        Response response =given().auth().oauth2(bearerToken).get("/api/users/me");
        // отправили запрос и сохранили ответ в переменную response - экземпляр класса Response

        response.then().assertThat().body("data.name",equalTo("Жак-Ив Кусто"));
        // проверили, что в теле ответа ключу name соответствует нужное имя пользователя

        System.out.println(response.body().asString()); // вывели тело ответа на экран

    }

}
