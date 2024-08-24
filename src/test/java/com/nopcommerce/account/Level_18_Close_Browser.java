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

public class Level_18_Close_Browser extends BaseTest {
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
        Assert.assertTrue(false);
    }

    @Test
    public void TC_01_Register_Empty_Data(){
    }



    @AfterClass(alwaysRun = true)
    public void afterClass(){
        closeBrowser();
    }



}
