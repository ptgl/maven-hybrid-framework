package com.nopcommerce.account;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.user.CustomerPageObject;
import pageObjects.user.HomePageObject;
import pageObjects.user.UserLoginPageObject;
import pageObjects.user.RegisterPageObject;

public class Level_04_Multiple_Browsers extends BaseTest {
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
    }

    @Test
    public void TC_01_Register_Empty_Data(){
        homePageObject = new HomePageObject(driver);

        homePageObject.clickToRegisterLink();

        registerPageObject = new RegisterPageObject(driver);

        registerPageObject.clickToRegisterButton();

        Assert.assertEquals(registerPageObject.getFirstNameErrorMessageText(), "First name is required.");
        Assert.assertEquals(registerPageObject.getLastNameErrorMessageText(), "Last name is required.");
        Assert.assertEquals(registerPageObject.getEmailErrorMessageText(), "Email is required.");
        Assert.assertEquals(registerPageObject.getConfirmPasswordErrorMessageText(), "Password is required.");

    }

    @Test
    public void TC_02_Register_Invalid_Email(){
        registerPageObject.clickToNopCommerceLogo();

        homePageObject = new HomePageObject(driver);

        homePageObject.clickToRegisterLink();

        registerPageObject = new RegisterPageObject(driver);
        registerPageObject.enterToFirstNameTextBox("Harry");
        registerPageObject.enterToLastNameTextBox("Potter");
        registerPageObject.enterToEmailTextBox("automation");
        registerPageObject.enterToPasswordTextBox("123456789");
        registerPageObject.enterToConfirmPasswordTextBox("123456789");

        registerPageObject.clickToRegisterButton();

        Assert.assertEquals(registerPageObject.getEmailErrorMessageText(), "Please enter a valid email address.");

    }

    @Test
    public void TC_03_Register_Invalid_Pass(){
        registerPageObject.clickToNopCommerceLogo();

        homePageObject = new HomePageObject(driver);

        homePageObject.clickToRegisterLink();

        registerPageObject = new RegisterPageObject(driver);
        registerPageObject.enterToFirstNameTextBox("Harry");
        registerPageObject.enterToLastNameTextBox("Potter");
        registerPageObject.enterToEmailTextBox("automation@gmail.com");
        registerPageObject.enterToPasswordTextBox("123");
        registerPageObject.enterToConfirmPasswordTextBox("123");

        registerPageObject.clickToRegisterButton();

        Assert.assertEquals(registerPageObject.getPasswordErrorMessageText(),"<p>Password must meet the following rules: </p><ul><li>must have at least 6 characters and not greater than 64 characters</li></ul>");

    }

    @Test
    public void TC_04_Register_Incorrect_Confirm_Pass(){
        registerPageObject.clickToNopCommerceLogo();

        homePageObject = new HomePageObject(driver);

        homePageObject.clickToRegisterLink();

        registerPageObject = new RegisterPageObject(driver);
        registerPageObject.enterToFirstNameTextBox("Harry");
        registerPageObject.enterToLastNameTextBox("Potter");
        registerPageObject.enterToEmailTextBox("automation@gmail.com");
        registerPageObject.enterToPasswordTextBox("123456789");
        registerPageObject.enterToConfirmPasswordTextBox("123");

        registerPageObject.clickToRegisterButton();

        Assert.assertEquals(registerPageObject.getConfirmPasswordErrorMessageText(), "The password and confirmation password do not match.");

    }

    @Test
    public void TC_05_Register_Sucess(){

        registerPageObject.clickToNopCommerceLogo();

        homePageObject = new HomePageObject(driver);

        homePageObject.clickToRegisterLink();

        registerPageObject = new RegisterPageObject(driver);
        registerPageObject.enterToFirstNameTextBox("Harry");
        registerPageObject.enterToLastNameTextBox("Potter");
        registerPageObject.enterToEmailTextBox(email);
        registerPageObject.enterToPasswordTextBox("123456789");
        registerPageObject.enterToConfirmPasswordTextBox("123456789");

        registerPageObject.clickToRegisterButton();

        Assert.assertEquals(registerPageObject.getRegisterSuccessMessageText(), "Your registration completed");

    }

    @Test
    public void TC_06_Login_Sucess(){
        registerPageObject.clickToNopCommerceLogo();

        homePageObject = new HomePageObject(driver);

        homePageObject.clickToLogoutLink();
        homePageObject.clickToLoginLink();

        userLoginPageObject = new UserLoginPageObject(driver);

        userLoginPageObject.enterEmailTextBox(email);
        userLoginPageObject.enterPasswordTextBox("123456789");
        userLoginPageObject.clickToLoginButton();

        homePageObject = new HomePageObject(driver);

        homePageObject.clickToMyAccountLink();

        customerPageObject = new CustomerPageObject(driver);

        Assert.assertEquals(customerPageObject.getFirstNameTextBoxAttributeValue(),"Harry");
        Assert.assertEquals(customerPageObject.getLastNameTextBoxAttributeValue(), "Potter");
        Assert.assertEquals(customerPageObject.getEmailTextBoxAttributeValue(), email);

    }

    @AfterClass
    public void afterClass(){
        closeBrowser();
    }



}