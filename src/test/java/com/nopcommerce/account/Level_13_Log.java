package com.nopcommerce.account;

import commons.BaseTest;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.user.*;

public class Level_13_Log extends BaseTest {
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
    public void TC_01_Register_Sucess(){
        //Verify Register link undisplayed => FAILED
        log.info("TC01 - Step 1: Verify register link display");
        verifyFalse(homePageObject.isRegisterLinkDisplay());

        log.info("TC01 - Step 2: Click register link");
        registerPageObject = homePageObject.clickToRegisterLink();

        log.info("TC01 - Step 3: Click register button");
        registerPageObject.clickToRegisterButton();

        //PASS
        log.info("TC01 - Step 4: Verify first name error message text");
        verifyEquals(registerPageObject.getFirstNameErrorMessageText(),"First name is required.");

        //FAILED
        log.info("TC01 - Step 5: Verify last name error message text");
        verifyEquals(registerPageObject.getLastNameErrorMessageText(),"Last name is... ");

        registerPageObject = new RegisterPageObject(driver);

        log.info("TC01 - Step 6: Enter first name");
        registerPageObject.enterToFirstNameTextBox("Harry");

        log.info("TC01 - Step 7: Enter last name");
        registerPageObject.enterToLastNameTextBox("Potter");

        log.info("TC01 - Step 8: Enter email");
        registerPageObject.enterToEmailTextBox(email);

        log.info("TC01 - Step 9: Enter password");
        registerPageObject.enterToPasswordTextBox("123456789");

        log.info("TC01 - Step 10: Enter confirm password");
        registerPageObject.enterToConfirmPasswordTextBox("123456789");

        log.info("TC01 - Step 11: Click register button");
        registerPageObject.clickToRegisterButton();

        //FAILED
        log.info("TC01 - Step 12: Verify register success message text");
        verifyEquals(registerPageObject.getRegisterSuccessMessageText(), "Your registration completed!!!");

    }

    //@Test
    public void TC_02_Login_Sucess(){
        homePageObject = registerPageObject.clickToNopCommerceLogo();

        homePageObject.clickToLogoutLink();

        userLoginPageObject = homePageObject.clickToLoginLink();

        userLoginPageObject.enterEmailTextBox(email);
        userLoginPageObject.enterPasswordTextBox("123456789");
        homePageObject = userLoginPageObject.clickToLoginButton();

        customerPageObject = homePageObject.clickToMyAccountLink();

        Assert.assertEquals(customerPageObject.getFirstNameTextBoxAttributeValue(),"Harry");
        Assert.assertEquals(customerPageObject.getLastNameTextBoxAttributeValue(), "Potter");
        Assert.assertEquals(customerPageObject.getEmailTextBoxAttributeValue(), email);

    }


    @AfterClass
    public void afterClass(){
        closeBrowser();
    }



}
