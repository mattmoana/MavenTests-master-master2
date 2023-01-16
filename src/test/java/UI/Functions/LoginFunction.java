package UI.Functions;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class LoginFunction {
    public void userLogin() {
        open("http://51.250.6.164:3000/signin");
        $(By.id("username")).setValue("user2");
        $(By.id("password")).setValue(("hellouser2"));
        $(By.xpath("//*[@data-name='signIn-button']")).click();

    }
}
