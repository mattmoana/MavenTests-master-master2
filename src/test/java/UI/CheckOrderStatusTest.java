package UI;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CheckOrderStatusTest {

    @Test
    public void checkOrderStatusPositive() {
        open("http://51.250.6.164:3000/signin");
        $(By.id("username")).setValue("user2");
        $(By.id("password")).setValue(("hellouser2"));
        $(By.xpath("//*[@data-name='signIn-button']")).click();
        $(By.xpath("//button[@class='header__button_check-order']")).click();
        $(By.xpath("//input[@type='number']")).setValue("1890");
        $(By.xpath("//button[@type='submit']")).click();
        $(By.xpath("//h3[@class='status-list__description status-list__description_active']")).shouldBe(Condition.visible);
        $(By.xpath("//a[@data-name='logout-button']")).click();
    }

    @Test
    public void checkOrderStatusWrongNumber() {
        open("http://51.250.6.164:3000/signin");
        $(By.id("username")).setValue("user2");
        $(By.id("password")).setValue(("hellouser2"));
        $(By.xpath("//*[@data-name='signIn-button']")).click();
        $(By.xpath("//button[@class='header__button_check-order']")).click();
        $(By.xpath("//input[@type='number']")).setValue("9890");
        $(By.xpath("//button[@type='submit']")).click();
        String errorMessage = $(By.xpath("//h1[@class='not-found__title']")).shouldBe(Condition.visible).getText();
        Assertions.assertEquals("Заказ не найден", errorMessage, "Wrong text");
    }
}
