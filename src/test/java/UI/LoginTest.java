package UI;

import UI.Functions.BaseTest;
import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginTest extends BaseTest{

    @Test
    public void loginExeptionTest() {
        open("http://51.250.6.164:3000/signin");
        $(By.id("username")).setValue("user");
        $(By.id("password")).setValue(("123456789"));
        $(By.xpath("//*[@data-name='signIn-button']")).click();
        $(By.xpath("//*[@data-name='authorizationError-popup-close-button']")).shouldBe(Condition.visible);
        String errorMessage = $(By.xpath("//span[@class='error-popup__title']")).shouldBe(Condition.visible).getText();
        Assertions.assertEquals("Incorrect credentials", errorMessage, "Wrong text");
    }

    @Test
    public void loginInputErrors() {
        open("http://51.250.6.164:3000/signin");
        $(By.id("username")).setValue("s");
        $(By.id("password")).setValue(("1234"));
        $(By.xpath("//*[@data-name='username-input-error']")).shouldBe(Condition.visible);
        String errorMessageLogin = $(By.xpath("//*[@id=\"root\"]/div/div[1]/main/form/fieldset[1]/span")).shouldBe(Condition.visible).getText();
        Assertions.assertEquals("Поле должно содержать минимум символов: 2", errorMessageLogin, "Wrong text");
        String errorMessagePassword = $(By.xpath("//*[@id=\"root\"]/div/div[1]/main/form/fieldset[2]/span")).shouldBe(Condition.visible).getText();
        Assertions.assertEquals("Поле должно содержать минимум символов: 8", errorMessagePassword, "Wrong text");
    }

    @Test
    public void loginPositiveTest() {
        open("http://51.250.6.164:3000/signin");
        $(By.id("username")).setValue("user2");
        $(By.id("password")).setValue(("hellouser2"));
        $(By.xpath("//*[@data-name='signIn-button']")).click();
        $(By.xpath("//*[@data-name='createOrder-button']")).shouldBe(Condition.visible);
        $(By.xpath("//*[@data-name='openStatusPopup-button']")).shouldBe(Condition.visible);
        $(By.xpath("//a[@data-name='logout-button']")).click();
    }
}
