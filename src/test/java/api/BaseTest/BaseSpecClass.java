package api.BaseTest;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matcher;

import static api.constants.Constants.BASE_URL;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class BaseSpecClass {

    public static void installSpecification(ResponseSpecification response) {
        RestAssured.responseSpecification = response;
    }

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

    public static ValidatableResponse responseWithBodyAssert(Response response, int statusCode, String path, Matcher<?> matcher) {
        return response
                .then()
                .spec(responseStatusCode(statusCode))
                .body(path, matcher);
    }

    public static ValidatableResponse responseWithMatchToJsonSchema(Response response, int statusCode, String jsonPath) {
        return response
                .then()
                .spec(responseStatusCode(statusCode))
                .body(matchesJsonSchemaInClasspath(jsonPath));
    }
}
