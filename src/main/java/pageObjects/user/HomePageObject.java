package pageObjects.user;

import commons.BaseElement;
import commons.BasePage;
import commons.PageGeneratorManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUIs.user.HomePageUI;

public class HomePageObject extends BaseElement {
    WebDriver driver;

    public HomePageObject(WebDriver driver){
        super(driver);
        this.driver = driver;
    }
    @Step("Click to My Account Link")
    public CustomerPageObject clickToMyAccountLink() {
        waitForElementClickable(driver, HomePageUI.MY_ACCOUNT_LINK);
        clickToElement(driver, HomePageUI.MY_ACCOUNT_LINK);
        return PageGeneratorManager.getCustomerPage(driver);
    }

    public CustomerPageObject openMyAccountLink() {
        String link = getElementAttribute(driver, HomePageUI.MY_ACCOUNT_LINK, "href");
        openPageUrl(driver, link);
        return PageGeneratorManager.getCustomerPage(driver);
    }

    @Step("Click to login link")
    public UserLoginPageObject clickToLoginLink() {
        waitForElementClickable(driver, HomePageUI.LOGIN_LINK);
        clickToElement(driver, HomePageUI.LOGIN_LINK);
        return PageGeneratorManager.getUserLoginPage(driver);
    }
    @Step("Click to register link")
    public RegisterPageObject clickToRegisterLink() {
        waitForElementClickable(driver, HomePageUI.REGISTER_LINK);
        clickToElement(driver, HomePageUI.REGISTER_LINK);
        return PageGeneratorManager.getRegisterPage(driver);
    }
    @Step("Click to log out link")
    public void clickToLogoutLink() {
        waitForElementClickable(driver, HomePageUI.LOGOUT_LINK);
        clickToElement(driver, HomePageUI.LOGOUT_LINK);
    }

    public boolean isRegisterLinkDisplay() {
        waitForElementVisible(driver, HomePageUI.REGISTER_LINK);
        return isElementDisplayed(driver, HomePageUI.REGISTER_LINK);
    }


}
