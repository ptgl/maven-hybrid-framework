package com.nopcommerce.account;

import com.aventstack.extentreports.Status;
import commons.BaseTest;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.user.*;
import reportConfigs.ExtentTestManager;

import java.lang.reflect.Method;

public class Level_14_Extent_Report_5 extends BaseTest {
    private WebDriver driver;
    private HomePageObject homePageObject;
    private RegisterPageObject registerPageObject;
    private UserLoginPageObject userLoginPageObject;
    private CustomerPageObject customerPageObject;
    private AddressPageObject addressPageObject;
    private OrderPageObject orderPageObject;
    private RewardPointPageObject rewardPointPageObject;

    private String email = getRandomEmail();

    @Parameters("browser")
    @BeforeClass
    public void beforeClass(String browserName){
        driver = getBrowserDriver(browserName, null);
        homePageObject = PageGeneratorManager.getHomePage(driver);
    }

    @Test
    public void TC_01_Register_Sucess(Method method){
        ExtentTestManager.startTest(method.getName(), "Register with valid email and password");
        ExtentTestManager.getTest().log(Status.INFO, "Register - Step 1: Navigate to Register page");
        registerPageObject = homePageObject.clickToRegisterLink();

        ExtentTestManager.getTest().log(Status.INFO, "Register - Step 2: Enter first name");
        registerPageObject.enterToFirstNameTextBox("Harry");

        ExtentTestManager.getTest().log(Status.INFO, "Register - Step 3: Enter last name");
        registerPageObject.enterToLastNameTextBox("Potter");

        ExtentTestManager.getTest().log(Status.INFO, "Register - Step 4: Enter email");
        registerPageObject.enterToEmailTextBox(email);

        ExtentTestManager.getTest().log(Status.INFO, "Register - Step 5: Enter password");
        registerPageObject.enterToPasswordTextBox("123456789");

        ExtentTestManager.getTest().log(Status.INFO, "Register - Step 6: Enter  confirm password");
        registerPageObject.enterToConfirmPasswordTextBox("123456789");

        ExtentTestManager.getTest().log(Status.INFO, "Register - Step 7: Click register button");
        registerPageObject.clickToRegisterButton();

        ExtentTestManager.getTest().log(Status.INFO, "Register - Step 8: Verify success message");
        verifyEquals(registerPageObject.getRegisterSuccessMessageText(), "Your registration completed!!!");

    }

    @Test
    public void TC_02_Login_Sucess(Method method){
        ExtentTestManager.startTest(method.getName(), "Login with valid email and password");
        ExtentTestManager.getTest().log(Status.INFO, "Login - Step 1: Navigate to Login page");
        homePageObject = registerPageObject.clickToNopCommerceLogo();
        homePageObject.clickToLogoutLink();
        userLoginPageObject = homePageObject.clickToLoginLink();

        ExtentTestManager.getTest().log(Status.INFO, "Login - Step 2: Enter email");
        userLoginPageObject.enterEmailTextBox(email);

        ExtentTestManager.getTest().log(Status.INFO, "Login - Step 3: Enter password");
        userLoginPageObject.enterPasswordTextBox("123456789");

        ExtentTestManager.getTest().log(Status.INFO, "Login - Step 4: Click Login button");
        homePageObject = userLoginPageObject.clickToLoginButton();

        ExtentTestManager.getTest().log(Status.INFO, "Login - Step 5: Click My Account Link");
        customerPageObject = homePageObject.clickToMyAccountLink();

        ExtentTestManager.getTest().log(Status.INFO, "Login - Step 6: Verify correct login email");
        Assert.assertEquals(customerPageObject.getEmailTextBoxAttributeValue(), email);

    }


    @AfterClass
    public void afterClass(){
        closeBrowser();
    }



}
