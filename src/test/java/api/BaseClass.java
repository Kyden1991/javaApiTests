package api;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static apiTestsStuding.constants.Constants.BASE_URL;
import static apiTestsStuding.constants.Constants.PATH_COURIER_CREATION;

public class BaseClass {

    public static RequestSpecification requestSpec(){
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .setContentType(ContentType.JSON)
                .build();
    }

    public static ResponseSpecification responseStatusCode(int statusCode){
        return new ResponseSpecBuilder()
                .expectStatusCode(statusCode)
                .log(LogDetail.ALL)
                .build();
    }

    public static void installSpecification(ResponseSpecification response) {

        RestAssured.responseSpecification = response;
    }




//    public void sendRequest(Method method) {
//        Response response = given()
//            .body(createCourier).log().all()
//            .when()
//            .post(PATH_COURIER_CREATION);
//    }
//
//    public void sendRequest(Method method, int expectedStatusCode, String body) {
//        response = given().spec(requestSpecBuilder.build()).log().all().body(body)
//                .when().request(method);
//        response.then().log().all().assertThat().statusCode(expectedStatusCode);
//        assertResponseBody();
//    }
}
