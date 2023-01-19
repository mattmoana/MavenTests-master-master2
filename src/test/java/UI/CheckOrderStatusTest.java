package UI;

import UI.Functions.CreateOrderPage;
import UI.Functions.LoginPage;
import UI.Functions.StatusPage;;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Selenide.open;

public class CheckOrderStatusTest {
    LoginPage loginPage = new LoginPage();
    StatusPage statusPage = new StatusPage();
    CreateOrderPage createOrderPage = new CreateOrderPage();
    @BeforeEach
    public void openStartPage() {
        loginPage = open("http://51.250.6.164:3000/signin", LoginPage.class);
        createOrderPage = loginPage.login("user2", "hellouser2");
        statusPage = createOrderPage.login();
    }
    @Test
    public void checkOrderStatusWrongNumber() {
        createOrderPage.clickStatusButton();
        createOrderPage.checkPopUpText("Введите номер заказа");
        createOrderPage.typeOrderNumber("9890");
        createOrderPage.clickSubmitButton();
        statusPage.getNotFoundText("Заказ не найден");
    }

    @Test
    public void checkOrderPositiveCase(){
        createOrderPage.clickStatusButton();
        createOrderPage.typeOrderNumber("1890");
        createOrderPage.clickSubmitButton();
        statusPage.getOrderCreatedStatus("OPEN");
        statusPage.getOrderCreatedText("Заказ создан");
    }
}
