package pageObjects.admin;

import commons.BasePage;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import pageUIs.admin.AdminDashBoardUI;

public class AdminDashboardPageObject extends BasePage {
    WebDriver driver;
    public AdminDashboardPageObject(WebDriver driver){
        this.driver = driver;
    }

    public AdminLoginPageObject clickToLogout(){
        waitForElementClickable(driver, AdminDashBoardUI.LOGOUT_LINK);
        clickToElement(driver, AdminDashBoardUI.LOGOUT_LINK);
        return PageGeneratorManager.getAdminLoginPage(driver);
    }
}
