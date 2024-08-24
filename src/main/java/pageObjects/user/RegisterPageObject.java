package pageObjects.user;

import commons.BaseElement;
import commons.BasePage;
import commons.PageGeneratorManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUIs.user.RegisterPageUI;

public class RegisterPageObject extends BaseElement {
    WebDriver driver;
    public RegisterPageObject(WebDriver driver){
        super(driver);
        this.driver = driver;
    }

    @Step("Get register success message text")
    public String getRegisterSuccessMessageText() {
        waitForElementVisible(driver, RegisterPageUI.REGISTER_SUCCESS_MESSAGE_TEXT);
        return getElementText(driver, RegisterPageUI.REGISTER_SUCCESS_MESSAGE_TEXT);
    }
    @Step("Click to register button")
    public void clickToRegisterButton() {
        waitForElementClickable(driver, RegisterPageUI.REGISTER_BUTTON);
        clickToElement(driver, RegisterPageUI.REGISTER_BUTTON);
    }
    @Step("Enter to first confirm password text box with value {0}")
    public void enterToConfirmPasswordTextBox(String confirmPassword) {
        waitForElementVisible(driver, RegisterPageUI.CONFIRM_PASSWORD_TEXTBOX);
        sendKeyToElement(driver, RegisterPageUI.CONFIRM_PASSWORD_TEXTBOX, confirmPassword);
    }
    @Step("Enter to password text box with value {0}")
    public void enterToPasswordTextBox(String password) {
        waitForElementVisible(driver, RegisterPageUI.PASSWORD_TEXTBOX);
        sendKeyToElement(driver, RegisterPageUI.PASSWORD_TEXTBOX, password);
    }
    @Step("Enter to email text box with value {0}")
    public void enterToEmailTextBox(String email) {
        waitForElementVisible(driver, RegisterPageUI.EMAIL_TEXTBOX);
        sendKeyToElement(driver, RegisterPageUI.EMAIL_TEXTBOX, email);
    }
    @Step("Enter to last name text box with value {0}")
    public void enterToLastNameTextBox(String lastName) {
        waitForElementVisible(driver, RegisterPageUI.LASTNAME_TEXTBOX);
        sendKeyToElement(driver, RegisterPageUI.LASTNAME_TEXTBOX, lastName);
    }
    @Step("Enter to first name text box with value {0}")
    public void enterToFirstNameTextBox(String firstName) {
        waitForElementVisible(driver, RegisterPageUI.FIRSTNAME_TEXTBOX);
        sendKeyToElement(driver, RegisterPageUI.FIRSTNAME_TEXTBOX, firstName);
    }

    public String getConfirmPasswordErrorMessageText() {
        waitForElementVisible(driver, RegisterPageUI.CONFIRM_PASSWORD_ERROR_MSG);
        return getElementText(driver, RegisterPageUI.CONFIRM_PASSWORD_ERROR_MSG);
    }

    public String getPasswordErrorMessageText() {
        waitForElementVisible(driver, RegisterPageUI.PASSWRDO_ERROR_MSG);
        return getElementText(driver, RegisterPageUI.PASSWRDO_ERROR_MSG);
    }

    public String getEmailErrorMessageText() {
        waitForElementVisible(driver, RegisterPageUI.EMAIL_ERROR_MSG);
        return getElementText(driver, RegisterPageUI.EMAIL_ERROR_MSG);
    }

    public String getLastNameErrorMessageText() {
        waitForElementVisible(driver, RegisterPageUI.LASTNAME_ERROR_MSG);
        return getElementText(driver, RegisterPageUI.LASTNAME_ERROR_MSG);
    }

    public String getFirstNameErrorMessageText() {
        waitForElementVisible(driver, RegisterPageUI.FIRSTNAME_ERROR_MSG);
        return getElementText(driver, RegisterPageUI.FIRSTNAME_ERROR_MSG);
    }
}
