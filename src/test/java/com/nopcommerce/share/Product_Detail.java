package com.nopcommerce.share;

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

public class Product_Detail extends BaseTest {
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

        userLoginPageObject = homePageObject.clickToLoginLink();

        //Login with username - password
//        userLoginPageObject.enterEmailTextBox(Common_Register.email);
//        userLoginPageObject.enterPasswordTextBox(Common_Register.password);
//        homePageObject = userLoginPageObject.clickToLoginButton();
//        customerPageObject = homePageObject.clickToMyAccountLink();

        //Login with cookie
        userLoginPageObject.setCookies(driver, Common_Register.cookies);
        userLoginPageObject.sleepInSeconds(2);
        userLoginPageObject.refresh(driver);
        homePageObject.sleepInSeconds(2);
        customerPageObject = homePageObject.openMyAccountLink();

        Assert.assertEquals(customerPageObject.getFirstNameTextBoxAttributeValue(),Common_Register.firstName);
        Assert.assertEquals(customerPageObject.getLastNameTextBoxAttributeValue(), Common_Register.lastName);
        Assert.assertEquals(customerPageObject.getEmailTextBoxAttributeValue(), Common_Register.email);

        System.out.println("email at Product "+ Common_Register.email);
        System.out.println("pwd at Product "+ Common_Register.password);
    }


    @Test
    public void Product_01_Create(){


    }

    @Test
    public void Product_02_View(){


    }

    @Test
    public void Product_03_Edit(){


    }

    @AfterClass
    public void afterClass(){
        closeBrowser();
    }



}
