package pageObjects.user;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUIs.user.CustomerPageUI;

public class CustomerPageObject extends MyAccountSidebarPageObject {
    WebDriver driver;
    public CustomerPageObject(WebDriver driver){
        super(driver);
        this.driver = driver;
    }
    @Step("Get email text box attibute value")
    public String getEmailTextBoxAttributeValue() {
        waitForElementVisible(driver, CustomerPageUI.EMAIL_TEXTBOX);
        return getElementAttribute(driver, CustomerPageUI.EMAIL_TEXTBOX, "value");
    }

    public String getLastNameTextBoxAttributeValue() {
        waitForElementVisible(driver, CustomerPageUI.LAST_NAME_TEXTBOX);
        return getElementAttribute(driver, CustomerPageUI.LAST_NAME_TEXTBOX, "value");
    }

    public String getFirstNameTextBoxAttributeValue() {
        waitForElementVisible(driver, CustomerPageUI.FIRS_TNAME_TEXTBOX);
        return getElementAttribute(driver, CustomerPageUI.FIRS_TNAME_TEXTBOX, "value");
    }
}
