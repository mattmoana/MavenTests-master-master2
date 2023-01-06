package Tests.api.tests;

import Dto_Objects.LoginFunctions;
import Dto_Objects.Order;
import Tests.api.tests.restaAssured.TestOrderFunctions;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class DeliveryTest {
    static Map<String, String> headers = new HashMap<>();

    @BeforeAll
    public static void getBearerToken() {

        headers.put("Content-type", "application/json");
        LoginFunctions loginFunctions = new LoginFunctions();
        String token = loginFunctions.getToken();
        headers.put("Authorization", "Bearer " + token);
    }

    @Test
    public void postOrderTest() {
        Order requestOrder = new Order();

        requestOrder.setStatus("OPEN");
        requestOrder.setCustomerName("Matt");
        requestOrder.setCustomerPhone("56776879");
        requestOrder.setCourierId(777L);
        requestOrder.setComment("wazzup");

        TestOrderFunctions testOrderFunctions = new TestOrderFunctions();
        Order responseOrder = testOrderFunctions.postNewOrder(requestOrder, headers);
        Assertions.assertEquals(
                requestOrder.getStatus(),
                requestOrder.getStatus(),
                "Wrong status code");
        System.out.println(responseOrder.toString());
    }

    @Test
    public void getOrdersTest() {

        LoginFunctions loginFunctions = new LoginFunctions();
        Response responseOrders =
                given().
                        headers(headers).
                        when().
                        get(loginFunctions.getBaseUrl() + "/orders").
                        then().
                        statusCode(200).extract().response();
        Assertions.assertFalse(responseOrders.body().asString().isBlank());
    }

    @Test
    public void orderLifecycleTest() {
        Order requestOrder = new Order(
                "OPEN",
                123L,
                "Matt",
                "56775879",
                "wazzzzup"
                );

        TestOrderFunctions testOrderFunctions = new TestOrderFunctions();
        Order responseOrder = testOrderFunctions.postNewOrder(requestOrder, headers);
        String orderId = String.valueOf(responseOrder.getId());
        Order orderById = testOrderFunctions.getOrderById(headers, orderId);
        Assertions.assertAll(
                () -> Assertions.assertEquals(requestOrder.getStatus(), orderById.getStatus()),
                () -> Assertions.assertEquals(requestOrder.getCustomerName(), orderById.getCustomerName()),
                () -> Assertions.assertEquals(requestOrder.getCustomerPhone(), orderById.getCustomerPhone())
        );

        Assertions.assertTrue(testOrderFunctions.deleteOrderById(headers, orderId));
    }
}

