package UI.Functions;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.$;

public class CreateOrderPage {

    private SelenideElement createOrderButton = $(By.xpath("//*[@data-name='createOrder-button']"));
    private SelenideElement createOrderStatusPopUpButton = $(By.xpath("//button[@data-name='openStatusPopup-button']"));
    private SelenideElement logOutButton = $(By.xpath("//a[@data-name='logout-button']"));
    private SelenideElement createOrderNameInput = $(By.xpath("//input[@data-name='username-input']"));
    private SelenideElement createOrderPhoneInput = $(By.xpath("//input[@data-name='phone-input']"));
    private SelenideElement createOrderCommentInput = $(By.xpath("//input[@data-name='comment-input']"));
    private SelenideElement createOrderPopUp = $(By.xpath("//h3[@class='notification-popup__text']"));
    private SelenideElement createOrderNameErrorMessage = $(By.xpath("//span[@data-name='username-input-error']"));
    private SelenideElement createOrderPhoneErrorMessage = $(By.xpath("//span[@data-name='phone-input-error']"));
    private SelenideElement createOrderPopUpCloseButton = $(By.xpath("//button[@data-name='orderSuccessfullyCreated-popup-close-button']"));
    public void CheckNameInputErrorMessage(String errorText) {
        createOrderNameErrorMessage.shouldBe(Condition.text(errorText));
    }
    public void CheckPhoneInputErrorMessage(String errorText) {
        createOrderPhoneErrorMessage.shouldBe(Condition.text(errorText));
    }
    public void getCreateOrderPopUpText(String popUpText) {
        createOrderPopUp.shouldBe(Condition.text(popUpText));
    }
    public void CheckCreateOrderButton(){
        createOrderButton.shouldBe(Condition.visible);
    }
    public void CheckOpenStatusPopUp(){
        createOrderStatusPopUpButton.shouldBe(Condition.visible);
    }
    public void ClickLogOutButton(){
        logOutButton.click();
    }
    public void ClickClosePopUpButton() {
        createOrderPopUpCloseButton.click();
    }
    public void FillAllTheInputsInOrderForm(String name, String phone, String comment) {
        createOrderNameInput.setValue(name);
        createOrderPhoneInput.setValue(phone);
        createOrderCommentInput.setValue(comment);
    }
    public void ClickOrderButton(){
        createOrderButton.click();
    }
    @FindBy(how = How.XPATH, using = "//button[@class='header__button_check-order']")
    private SelenideElement StatusButton;
    public void clickStatusButton() {
        StatusButton.click();
    }
    @FindBy(how = How.XPATH, using = "//label[@class='order-search-popup__label']")
    private SelenideElement popUpText;
    public void checkPopUpText(String text){
        popUpText.shouldBe(Condition.text(text));
    }
    @FindBy(how = How.XPATH, using = "//input[@type='number']")
    private SelenideElement OrderNumberInput;
    public void typeOrderNumber(String orderNumber){
        OrderNumberInput.setValue(orderNumber);
    }
    @FindBy(how = How.XPATH, using = "//button[@type='submit']")
    private SelenideElement SubmitButton;
    public StatusPage clickSubmitButton(){
        SubmitButton.click();
        return Selenide.page(StatusPage.class);
    }
    public StatusPage login(){
        return Selenide.page(StatusPage.class);
    }
}
