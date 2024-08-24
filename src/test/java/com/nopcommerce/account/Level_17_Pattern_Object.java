package com.nopcommerce.account;

import commons.BaseTest;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.user.CustomerPageObject;
import pageObjects.user.HomePageObject;
import pageObjects.user.RegisterPageObject;
import pageObjects.user.UserLoginPageObject;

public class Level_17_Pattern_Object extends BaseTest {
    private WebDriver driver;
    private HomePageObject homePageObject;
    private RegisterPageObject registerPageObject;
    private UserLoginPageObject userLoginPageObject;
    private CustomerPageObject customerPageObject;
    private String email = getRandomEmail();

    @Parameters("browser")
    @BeforeClass
    public void beforeClass(String browserName){
        driver = getBrowserDriver(browserName, null);
        homePageObject = PageGeneratorManager.getHomePage(driver);
    }

    @Test
    public void TC_01_Register_Empty_Data(){
        homePageObject.clickToHeaderLinkByName("Register");
        registerPageObject = PageGeneratorManager.getRegisterPage(driver);

        registerPageObject.clickToButtonByText("Register");

        Assert.assertEquals(registerPageObject.getTextboxErrorMessageByID("FirstName"), "First name is required.");
        Assert.assertEquals(registerPageObject.getTextboxErrorMessageByID("LastName"), "Last name is required.");
        Assert.assertEquals(registerPageObject.getTextboxErrorMessageByID("Email"), "Email is required.");
        Assert.assertEquals(registerPageObject.getTextboxErrorMessageByID("ConfirmPassword"), "Password is required.");
    }

    @Test
    public void TC_02_Register_Invalid_Email(){
        homePageObject = registerPageObject.clickToNopCommerceLogo();

        homePageObject.clickToHeaderLinkByName("Register");
        registerPageObject = PageGeneratorManager.getRegisterPage(driver);

        registerPageObject.enterToTextboxById("FirstName","Harry");
        registerPageObject.enterToTextboxById("LastName","Potter");
        registerPageObject.enterToTextboxById("Email","automation");
        registerPageObject.enterToTextboxById("Password","123456789");
        registerPageObject.enterToTextboxById("ConfirmPassword","123456789");

        registerPageObject.clickToButtonByText("Register");

        Assert.assertEquals(registerPageObject.getTextboxErrorMessageByID("Email"), "Please enter a valid email address.");
    }

    @Test
    public void TC_03_Register_Invalid_Pass(){
        homePageObject = registerPageObject.clickToNopCommerceLogo();

        homePageObject.clickToHeaderLinkByName("Register");
        registerPageObject = PageGeneratorManager.getRegisterPage(driver);

        registerPageObject.enterToTextboxById("FirstName","Harry");
        registerPageObject.enterToTextboxById("LastName","Potter");
        registerPageObject.enterToTextboxById("Email","automation@gmail.com");
        registerPageObject.enterToTextboxById("Password","123");
        registerPageObject.enterToTextboxById("ConfirmPassword","123");

        registerPageObject.clickToButtonByText("Register");

        Assert.assertEquals(registerPageObject.getTextboxErrorMessageByID("Password"), "<p>Password must meet the following rules: </p><ul><li>must have at least 6 characters and not greater than 64 characters</li></ul>");

    }

    @Test
    public void TC_04_Register_Incorrect_Confirm_Pass(){
        homePageObject = registerPageObject.clickToNopCommerceLogo();

        homePageObject.clickToHeaderLinkByName("Register");
        registerPageObject = PageGeneratorManager.getRegisterPage(driver);

        registerPageObject.enterToTextboxById("FirstName","Harry");
        registerPageObject.enterToTextboxById("LastName","Potter");
        registerPageObject.enterToTextboxById("Email","automation@gmail.com");
        registerPageObject.enterToTextboxById("Password","123456789");
        registerPageObject.enterToTextboxById("ConfirmPassword","123");

        registerPageObject.clickToButtonByText("Register");

        Assert.assertEquals(registerPageObject.getTextboxErrorMessageByID("ConfirmPassword"), "The password and confirmation password do not match.");

    }

    @Test
    public void TC_05_Register_Sucess(){

        homePageObject = registerPageObject.clickToNopCommerceLogo();

        homePageObject.clickToHeaderLinkByName("Register");
        registerPageObject = PageGeneratorManager.getRegisterPage(driver);

        registerPageObject.enterToTextboxById("FirstName","Harry");
        registerPageObject.enterToTextboxById("LastName","Potter");
        registerPageObject.enterToTextboxById("Email",email);
        registerPageObject.enterToTextboxById("Password","123456789");
        registerPageObject.enterToTextboxById("ConfirmPassword","123456789");

        registerPageObject.clickToButtonByText("Register");

        Assert.assertEquals(registerPageObject.getRegisterSuccessMessageText(), "Your registration completed");

    }

    @Test
    public void TC_06_Login_Sucess(){
        homePageObject = registerPageObject.clickToNopCommerceLogo();

        homePageObject.clickToHeaderLinkByName("Log out");
        homePageObject.clickToHeaderLinkByName("Log in");
        userLoginPageObject = PageGeneratorManager.getUserLoginPage(driver);

        registerPageObject.enterToTextboxById("Email",email);
        registerPageObject.enterToTextboxById("Password","123456789");

        userLoginPageObject.clickToButtonByText("Log in");
        homePageObject = PageGeneratorManager.getHomePage(driver);

        homePageObject.clickToHeaderLinkByName("My account");
        customerPageObject = PageGeneratorManager.getCustomerPage(driver);

        Assert.assertEquals(customerPageObject.getTextBoxAttributeById("FirstName"), "Harry");
        Assert.assertEquals(customerPageObject.getTextBoxAttributeById("LastName"), "Potter");
        Assert.assertEquals(customerPageObject.getTextBoxAttributeById("Email"), email);
    }

    @AfterClass
    public void afterClass(){
        closeBrowser();
    }



}
