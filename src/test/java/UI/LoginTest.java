package UI;

import UI.Functions.BaseTest;
import UI.Functions.CreateOrderPage;
import UI.Functions.LoginPage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Selenide.open;

public class LoginTest {

    LoginPage loginPage;
    @BeforeEach
    public void openStartPage() {
        loginPage = open("http://51.250.6.164:3000/signin", LoginPage.class);
    }

    @Test
    public void loginExeptionTest() {

        loginPage.login("user", "hellouser2");
        loginPage.getWrongCredentialsPopUpText("Incorrect credentials");
        loginPage.WrongCredentialsPopUpClose();
    }

    @Test
    public void loginInputErrors() {

        loginPage.loginWithoutClick("S", "1234");
        loginPage.getUsernameErrorText("Поле должно содержать минимум символов: 2");
        loginPage.getPasswordErrorText("Поле должно содержать минимум символов: 8");
    }

    @Test
    public void loginPositiveTest() {

        CreateOrderPage createOrderPage = loginPage.login("user2", "hellouser2");
        createOrderPage.CheckCreateOrderButton();
        createOrderPage.CheckOpenStatusPopUp();
        createOrderPage.ClickLogOutButton();
    }
}
