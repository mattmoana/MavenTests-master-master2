package UI.Functions;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import static com.codeborne.selenide.Selenide.$;


public class LoginPage {
    private SelenideElement usernameField = $(By.id("username"));
    private SelenideElement passwordField = $(By.id("password"));
    private SelenideElement signInButton = $(By.xpath("//*[@data-name='signIn-button']"));
    private SelenideElement usernameErrorMessage = $(By.xpath("/html/body/div/div/div[1]/main/form/fieldset[1]/span"));
    public void getUsernameErrorText(String usernameError) {
        usernameErrorMessage.shouldBe(Condition.text(usernameError));
    }
    private SelenideElement passwordErrorMessage = $(By.xpath("/html/body/div/div/div[1]/main/form/fieldset[2]/span"));
    public void getPasswordErrorText(String passwordError) {
        passwordErrorMessage.shouldBe(Condition.text(passwordError));
    }
    private SelenideElement wrongCredentialsPopUp = $(By.xpath("//span[@class='error-popup__title']"));
    public void getWrongCredentialsPopUpText(String errorText){
        wrongCredentialsPopUp.shouldBe(Condition.text(errorText));
    }
    private SelenideElement wrongCredentialsPopUpClose = $(By.xpath("//button[@data-name='authorizationError-popup-close-button']"));

    public CreateOrderPage login(String username, String password) {
        usernameField.setValue(username);
        passwordField.setValue((password));
        signInButton.click();
        return Selenide.page(CreateOrderPage.class);
    }

    public void loginWithoutClick(String username, String password) {
        usernameField.setValue(username);
        passwordField.setValue(password);
    }
    public void WrongCredentialsPopUpClose(){
        wrongCredentialsPopUpClose.click();
    }

}

