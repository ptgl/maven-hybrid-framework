package com.nopcommerce.account;

import commons.BaseTest;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.admin.AdminDashboardPageObject;
import pageObjects.admin.AdminLoginPageObject;
import pageObjects.user.*;

public class Level_07_Switch_Page_Url extends BaseTest {
    private WebDriver driver;
    private HomePageObject homePageObject;
    private RegisterPageObject registerPageObject;
    private UserLoginPageObject userLoginPageObject;
    private CustomerPageObject customerPageObject;
    private AddressPageObject addressPageObject;
    private OrderPageObject orderPageObject;
    private RewardPointPageObject rewardPointPageObject;
    private AdminLoginPageObject adminLoginPageObject;
    private AdminDashboardPageObject adminDashboardPageObject;

    private String email = getRandomEmail();
    private String adminUrl, userUrl;

    @Parameters({"browser", "adminUrl", "userUrl"})
    @BeforeClass
    public void beforeClass(String browserName, String adminUrl, String userUrl){
        driver = getBrowserDriver(browserName, userUrl);
        this.adminUrl = adminUrl;
        this.userUrl = userUrl;
        homePageObject = PageGeneratorManager.getHomePage(driver);
    }

    @Test
    public void TC_01_User_To_Admin(){

        registerPageObject = homePageObject.clickToRegisterLink();

        registerPageObject = new RegisterPageObject(driver);
        registerPageObject.enterToFirstNameTextBox("Harry");
        registerPageObject.enterToLastNameTextBox("Potter");
        registerPageObject.enterToEmailTextBox(email);
        registerPageObject.enterToPasswordTextBox("123456789");
        registerPageObject.enterToConfirmPasswordTextBox("123456789");

        registerPageObject.clickToRegisterButton();

        homePageObject = registerPageObject.clickToNopCommerceLogo();

        homePageObject.clickToLogoutLink();

        userLoginPageObject = homePageObject.clickToLoginLink();

        userLoginPageObject.enterEmailTextBox(email);
        userLoginPageObject.enterPasswordTextBox("123456789");
        homePageObject = userLoginPageObject.clickToLoginButton();
        homePageObject.clickToLogoutLink();

        //Open Admin Login Page
        homePageObject.openPageUrl(driver, this.adminUrl);

        adminLoginPageObject = PageGeneratorManager.getAdminLoginPage(driver);

        adminDashboardPageObject = adminLoginPageObject.loginToAdmin(" admin@yourstore.com", "admin");

        Assert.assertTrue(adminDashboardPageObject.isPageLoadedSuccess(driver));
    }

    @Test
    public void TC_02_Admin_To_User(){
        adminLoginPageObject = adminDashboardPageObject.clickToLogout();
        adminLoginPageObject.openPageUrl(driver, this.userUrl);

        homePageObject = PageGeneratorManager.getHomePage(driver);

        homePageObject.clickToLoginLink();



    }


    @AfterClass
    public void afterClass(){
        closeBrowser();
    }



}
