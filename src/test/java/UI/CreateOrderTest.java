package UI;

import UI.Functions.CreateOrderPage;
import UI.Functions.LoginPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;

public class CreateOrderTest {

    LoginPage loginPage;
    CreateOrderPage createOrderPage = new CreateOrderPage();
    @BeforeEach
    public void openStartPage() {
        loginPage = open("http://51.250.6.164:3000/signin", LoginPage.class);
        createOrderPage = loginPage.login("user2", "hellouser2");
    }

    @Test
    public void createOrderPositiveTest() {
        createOrderPage.FillAllTheInputsInOrderForm("Matt", "12345678", "wazzup");
        createOrderPage.ClickOrderButton();
        createOrderPage.getCreateOrderPopUpText("Заказ создан!");
        createOrderPage.ClickClosePopUpButton();
        createOrderPage.ClickLogOutButton();
    }

    @Test
    public void createOrderErrorMessagesTest() {
        createOrderPage.FillAllTheInputsInOrderForm("M", "123", "wazzup");
        createOrderPage.CheckNameInputErrorMessage("Поле должно содержать минимум символов: 2");
        createOrderPage.CheckPhoneInputErrorMessage("Поле должно содержать минимум символов: 6");
        createOrderPage.ClickLogOutButton();
    }
}
