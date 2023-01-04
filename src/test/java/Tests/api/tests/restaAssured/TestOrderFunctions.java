package Tests.api.tests.restaAssured;

import Dto_Objects.Order;
import com.google.gson.Gson;
import io.restassured.response.Response;

import java.io.FileInputStream;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class TestOrderFunctions {
    public String baseUrl;

    public TestOrderFunctions() {
        try (InputStream input = new FileInputStream("src/main/resources/settings.properties")) {
            Properties properties = new Properties();
            properties.load(input);
            baseUrl = properties.getProperty("baseUrl");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Order postNewOrder(Order body) {
        Gson gson = new Gson();
        String stringRequestBody = gson.toJson(body);
        Response response =
                given().
                        header("Content-type", "application/json").
                                body(stringRequestBody).
                        when().
                                post( baseUrl + "/orders").
                        then().
                                statusCode(200).
                                extract().response();
        return gson.fromJson(response.body().asString(), Order.class);
    }

    public Order postNewOrder(Order body, Map<String, String> headers) {
        Gson gson = new Gson();
        String stringRequestBody = gson.toJson(body);
        Response response =
                given().
                        headers(headers).
                        body(stringRequestBody).
                        when().
                        post(baseUrl + "/orders").
                        then().
                        statusCode(200).
                        extract().response();
        return gson.fromJson(response.body().asString(), Order.class);
    }


        public Order getOrderById(Map<String,String> headers, String orderId){
            Gson gson = new Gson();
            Response responseOrderById =
                    given().
                            headers(headers).
                            when().
                            get(baseUrl + "/orders/" + orderId).
                            then().
                            statusCode(200).extract().response();
            return gson.fromJson(responseOrderById.body().asString(), Order.class);
        }

        public Boolean deleteOrderById(Map<String,String> headers, String orderId){
            Response response =
                    given().
                            headers(headers).
                            when().
                            delete(baseUrl + "/orders/" + orderId).
                            then().
                            statusCode(200).extract().response();
            return Boolean.valueOf(response.body().asString());
        }
    }
