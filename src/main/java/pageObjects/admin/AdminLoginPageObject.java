package pageObjects.admin;

import commons.BasePage;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import pageUIs.admin.AdminLoginPageUI;
import pageUIs.user.HomePageUI;
import pageUIs.user.LoginPageUI;

public class AdminLoginPageObject extends BasePage {
    WebDriver driver;
    public AdminLoginPageObject(WebDriver driver){
        this.driver = driver;
    }

    public void enterToEmailTextbox(String email) {
        waitForElementVisible(driver, AdminLoginPageUI.EMAIL_TEXTBOX);
        sendKeyToElement(driver, AdminLoginPageUI.EMAIL_TEXTBOX, email);
    }

    public void enterToPasswordTextbox(String pass) {
        waitForElementVisible(driver, AdminLoginPageUI.PASSWORD_TEXTBOX);
        sendKeyToElement(driver, AdminLoginPageUI.PASSWORD_TEXTBOX, pass);
    }

    public AdminDashboardPageObject clickToLoginButton() {
        waitForElementClickable(driver, AdminLoginPageUI.LOGIN_BUTTON);
        clickToElement(driver, AdminLoginPageUI.LOGIN_BUTTON);
        return PageGeneratorManager.getAdminDashboardPage(driver);
    }

    public AdminDashboardPageObject loginToAdmin(String email, String password){
        enterToEmailTextbox(email);
        enterToPasswordTextbox(password);
        clickToLoginButton();
        return PageGeneratorManager.getAdminDashboardPage(driver);
    }
}
