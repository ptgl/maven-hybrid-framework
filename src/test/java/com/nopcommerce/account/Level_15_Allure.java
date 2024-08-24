package com.nopcommerce.account;

import commons.BaseTest;
import commons.PageGeneratorManager;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import jdk.jfr.Description;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.user.*;
import reportConfigs.ExtentTestManager;

import java.lang.reflect.Method;

public class Level_15_Allure extends BaseTest {
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
    @Description("TC 01: Register Sucess")
    @Story("Register")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void TC_01_Register_Sucess(){
        registerPageObject = homePageObject.clickToRegisterLink();

        registerPageObject.enterToFirstNameTextBox("Harry");

        registerPageObject.enterToLastNameTextBox("Potter");

        registerPageObject.enterToEmailTextBox(email);

        registerPageObject.enterToPasswordTextBox("123456789");

        registerPageObject.enterToConfirmPasswordTextBox("123456789");

        registerPageObject.clickToRegisterButton();

        verifyEquals(registerPageObject.getRegisterSuccessMessageText(), "Your registration completed!!!");

    }
    @Description("TC 02: Login Sucess")
    @Story("Login")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void TC_02_Login_Sucess(){
        homePageObject = registerPageObject.clickToNopCommerceLogo();
        homePageObject.clickToLogoutLink();
        userLoginPageObject = homePageObject.clickToLoginLink();

        userLoginPageObject.enterEmailTextBox(email);

        userLoginPageObject.enterPasswordTextBox("123456789");

        homePageObject = userLoginPageObject.clickToLoginButton();

        customerPageObject = homePageObject.clickToMyAccountLink();

        Assert.assertEquals(customerPageObject.getEmailTextBoxAttributeValue(), email);

    }


    @AfterClass
    public void afterClass(){
        closeBrowser();
    }



}
