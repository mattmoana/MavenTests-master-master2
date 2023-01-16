package UI;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CreateOrderTest {

    @Test
    public void createOrderPositiveTest() {
        open("http://51.250.6.164:3000/signin");
        $(By.id("username")).setValue("user2");
        $(By.id("password")).setValue(("hellouser2"));
        $(By.xpath("//*[@data-name='signIn-button']")).click();
        $(By.xpath("//input[@data-name='username-input']")).setValue("Matt");
        $(By.xpath("//input[@data-name='phone-input']")).setValue("55555555");
        $(By.xpath("//input[@data-name='comment-input']")).setValue("comment");
        $(By.xpath("//button[@data-name='createOrder-button']")).click();
        $(By.xpath("//h3[@class='notification-popup__text']")).shouldBe(Condition.visible);
    }

    @Test
    public void createOrderErrorMessagesTest() {
        open("http://51.250.6.164:3000/signin");
        $(By.id("username")).setValue("user2");
        $(By.id("password")).setValue(("hellouser2"));
        $(By.xpath("//*[@data-name='signIn-button']")).click();
        $(By.xpath("//input[@data-name='username-input']")).setValue("M");
        $(By.xpath("//input[@data-name='phone-input']")).setValue("5");
        String errorMessageNameInput = $(By.xpath("//span[@data-name='username-input-error']")).shouldBe(Condition.visible).getText();
        Assertions.assertEquals("Поле должно содержать минимум символов: 2", errorMessageNameInput, "Wrong text");
        String errorMessagePhoneInput = $(By.xpath("//span[@data-name='phone-input-error']")).shouldBe(Condition.visible).getText();
        Assertions.assertEquals("Поле должно содержать минимум символов: 6", errorMessagePhoneInput, "Wrong text");
    }
}
