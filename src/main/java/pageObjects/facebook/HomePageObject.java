package pageObjects.facebook;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.facebook.HomePageUI;

public class HomePageObject extends BasePage {
    WebDriver driver;

    public HomePageObject(WebDriver driver){
        this.driver = driver;
    }

    public boolean isPasswordTextboxDisplayed() {
        waitForElementVisible(driver, HomePageUI.PASSWORD_TEXTBOX);
        return isElementDisplayed(driver, HomePageUI.PASSWORD_TEXTBOX);
    }

    public boolean isPasswordTextboxUnDisplayed() {
        return isElementUnDisplayed(driver, HomePageUI.PASSWORD_TEXTBOX);
    }

    public boolean isEmailTextboxDisplayed() {
        waitForElementVisible(driver, HomePageUI.EMAIL_TEXTBOX);
        return isElementDisplayed(driver, HomePageUI.EMAIL_TEXTBOX);
    }

    public boolean isSureNameTextboxDisplayed() {
        waitForElementVisible(driver, HomePageUI.SURNAME_TEXTBOX);
        return isElementDisplayed(driver, HomePageUI.SURNAME_TEXTBOX);
    }

    public boolean isFirstNameTextboxDisplayed() {
        waitForElementVisible(driver, HomePageUI.FIRSTNAME_TEXTBOX);
        return isElementDisplayed(driver, HomePageUI.FIRSTNAME_TEXTBOX);
    }

    public void clickToCloseSignUpPopup() {
        waitForElementClickable(driver, HomePageUI.CLOSE_POPUP_BUTTON);
        clickToElement(driver, HomePageUI.CLOSE_POPUP_BUTTON);
    }

    public void enterToEmailTextbox(String email) {
        waitForElementVisible(driver, HomePageUI.EMAIL_TEXTBOX);
        sendKeyToElement(driver, HomePageUI.EMAIL_TEXTBOX, email);

    }

    public boolean isReConfirmEmailDisplayed() {
        waitForElementVisible(driver, HomePageUI.CONFIRM_EMAIL_TEXTBOX);
        return isElementDisplayed(driver, HomePageUI.CONFIRM_EMAIL_TEXTBOX);
    }

    public void clickToCreateNewAccountButton() {
        waitForElementClickable(driver, HomePageUI.CREATE_NEW_ACCOUNT_BUTTON);
        clickToElement(driver, HomePageUI.CREATE_NEW_ACCOUNT_BUTTON);
    }

    public boolean isEmailTextboxUnDisplayed() {
        return isElementUnDisplayed(driver, HomePageUI.EMAIL_TEXTBOX);
    }

    public boolean isSureNameTextboxUnDisplayed() {
        return isElementUnDisplayed(driver, HomePageUI.SURNAME_TEXTBOX);
    }

    public boolean isFirstNameTextboxUnDisplayed() {
        return isElementUnDisplayed(driver, HomePageUI.FIRSTNAME_TEXTBOX);
    }

    public boolean isReConfirmEmailUnDisplayed() {
        return isElementUnDisplayed(driver, HomePageUI.CONFIRM_EMAIL_TEXTBOX);
    }
}
