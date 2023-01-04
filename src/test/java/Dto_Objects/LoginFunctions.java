package Dto_Objects;

import com.google.gson.Gson;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class LoginFunctions {
    private String baseUrl;
    private String username;
    private String password;

    public String getBaseUrl() {
        return baseUrl;
    }

    public LoginFunctions(){
        try(InputStream input = new FileInputStream("src/main/resources/settings.properties")) {
            Properties properties = new Properties();
            properties.load(input);
            baseUrl = properties.getProperty("baseUrl");
            username = properties.getProperty("username");
            password = properties.getProperty("password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String createUser() {
        User user = new User(username, password);
        Gson gson = new Gson();
        return gson.toJson(user);
    }

    public String getToken() {
        LoginFunctions loginFunctions = new LoginFunctions();
        Response response =
                given().
                        header("Content-type", "application/json").
                        body(createUser()).
                        when().
                        post(baseUrl + "/login/student").
                        then().
                        statusCode(200).extract().response();
        //Saving bearer token to a string
        return response.body().asString();
    }
}