package UI.Functions;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class StatusPage {

    @FindBy(how = How.XPATH, using  ="//h1[@class='not-found__title']")
    private SelenideElement NotFoundText;
    public void getNotFoundText(String errorText){
        NotFoundText.shouldBe(Condition.text(errorText));
    }
    @FindBy(how = How.XPATH, using = "//span[@class='status-list__status status-list__status_active']")
    private SelenideElement OrderCreatedStatus;
    public void getOrderCreatedStatus(String statusName){
        OrderCreatedStatus.shouldBe(Condition.text(statusName));
    }
    @FindBy(how = How.XPATH, using = "//h3[@class='status-list__description status-list__description_active']")
    private SelenideElement OrderCreatedText;
    public void getOrderCreatedText(String orderText){
        OrderCreatedText.shouldBe(Condition.text(orderText));
    }

}
