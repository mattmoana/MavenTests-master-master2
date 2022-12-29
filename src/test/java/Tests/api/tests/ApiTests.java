package Tests.api.tests;

import Dto_Objects.Order;
import com.google.gson.Gson;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;


public class ApiTests {

    @Test
    public void deserialisedOrderTest() {
        Order requestOrder = new Order();

        requestOrder.setStatus("OPEN");
        requestOrder.setCustomerName("Matt");
        requestOrder.setCustomerPhone("56776879");
        requestOrder.setCourierId(777L);
        requestOrder.setComment("wazzup");

        Gson gson = new Gson();
        String stringRequestOrder = gson.toJson(requestOrder);

        Response response =
                given()
                        .contentType("application/json")
                        .body(stringRequestOrder)
                .when()
                        .post("http://51.250.6.164:8080/test-orders/")
                .then()
                        .statusCode(200)
                        .extract().response();

        String responseBody = response.body().asString();
        Order responseOrder = gson.fromJson(responseBody, Order.class);

        Assertions.assertEquals("OPEN", responseOrder.getStatus());
    }
}
