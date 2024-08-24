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

public class Level_09_Rest_Params extends BaseTest {
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

        registerPageObject = homePageObject.clickToRegisterLink();

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

    @Test
    public void TC_03_Switch_Page(){

        addressPageObject = (AddressPageObject) customerPageObject.openDynamicSideBarPage("Addresses");

        orderPageObject = (OrderPageObject) addressPageObject.openDynamicSideBarPage("Orders");

        rewardPointPageObject = (RewardPointPageObject) orderPageObject.openDynamicSideBarPage("Reward points");

        customerPageObject = (CustomerPageObject) rewardPointPageObject.openDynamicSideBarPage("Customer info");

    }

    @Test
    public void TC_04_Switch_Page(){

        customerPageObject.openDynamicSideBarPageByName("Addresses");
        addressPageObject = PageGeneratorManager.getAddressPage(driver);

        addressPageObject.openDynamicSideBarPageByName("Orders");
        orderPageObject = PageGeneratorManager.getOrderPage(driver);

        orderPageObject.openDynamicSideBarPageByName("Reward points");
        rewardPointPageObject = PageGeneratorManager.getRewardPointPage(driver);

        rewardPointPageObject.openDynamicSideBarPageByName("Customer info");
        customerPageObject = PageGeneratorManager.getCustomerPage(driver);



    }

    @AfterClass
    public void afterClass(){
        closeBrowser();
    }



}
