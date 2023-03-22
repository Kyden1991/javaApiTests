package api;

import apiTestsStuding.dto.CreateOrderDto;
import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

//@RunWith(Parameterized.class)
public class OrderCreationApiTest {
    @Before
    public void baseUrl() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru";
    }

    private final String[][] color =
            new String[][]{
                    {"BLACK"},
                    {"GREY"},
                    {"BLACK", "GREY"},
                    {""}
            };



//    public OrderCreationApi(String[] color, String[] color2) {
//        this.color = color;
//    }

    //    List<String> colors = new ArrayList<>();
//    colors.add(new("BLACK", "GREY"))

//static String[] colors = {"BLACK", "GREY", ""};

//    @Parameterized.Parameters
//    public static String[][] setColorForOrderCreation() {
//        return new String[][]{
//                {"BLACK"},
//                {"GREY"},
//                {"BLACK", "GREY"},
//                {""}
//        };
//    }
//    @Parameterized.Parameters
//    public static Collection<String[]> setColorForOrderCreation() {
//        return List.of(new String[][]{
//                {"BLACK"},
//                {"GREY"},
//                {"BLACK", "GREY"},
//                {""}
//        });
//    }
    @Test
    @Description("Return HTTP 201 status code and track in body by the POST/api/v1/orders method with valid request parameters and different colors")
    public void postOrderCreationReturnStatusCode201WithDiffParameters() {
        for (String[] color : this.color) {
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
}
