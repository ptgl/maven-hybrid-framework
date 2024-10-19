package pageObjects.orangeHRM;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.orangeHRM.LoginPageUI;

public class LoginPageObject extends BasePage {
    WebDriver driver;
    public LoginPageObject(WebDriver driver){
        this.driver = driver;
    }

    public void enterToUserNameTextbox(String username){
        waitForElementVisible(driver, LoginPageUI.USERNAME_TEXTBOX);
        sendKeyToElement(driver, LoginPageUI.USERNAME_TEXTBOX, username);
    }

    public void enterToPasswordTextbox(String pass){
        waitForElementVisible(driver, LoginPageUI.PASSWORD_TEXTBOX);
        sendKeyToElement(driver, LoginPageUI.PASSWORD_TEXTBOX, pass);
    }

    public void clickLoginButton(){
        waitForElementVisible(driver, LoginPageUI.LOGIN_BUTTON);
        clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
    }
}
