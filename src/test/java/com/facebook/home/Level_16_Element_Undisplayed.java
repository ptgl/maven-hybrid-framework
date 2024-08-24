package com.facebook.home;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.facebook.HomePageObject;
import pageObjects.facebook.PageGeneratorManager;
import pageObjects.user.*;

public class Level_16_Element_Undisplayed extends BaseTest {
    private WebDriver driver;
    HomePageObject homePage;

    @Parameters({"browser", "url"})
    @BeforeClass
    public void beforeClass(String browserName, String url){
        driver = getBrowserDriver(browserName, url);
        homePage = PageGeneratorManager.getHomePageObject(driver);

    }

    @Test
    public void TC_01_Element_Displayed(){
        homePage.clickToCreateNewAccountButton();
        verifyTrue(homePage.isFirstNameTextboxDisplayed());
        verifyTrue(homePage.isSureNameTextboxDisplayed());
        verifyTrue(homePage.isEmailTextboxDisplayed());
        verifyTrue(homePage.isPasswordTextboxDisplayed());

        homePage.enterToEmailTextbox("mail@mail.com");
        verifyTrue(homePage.isReConfirmEmailDisplayed());
    }

    @Test
    public void TC_02_Element_Undisplayed_In_DOM(){
        homePage.enterToEmailTextbox("");
        homePage.sleepInSeconds(2);

        log.info("Verify confirm email textbox not display but still in Dom");
        verifyTrue(homePage.isReConfirmEmailUnDisplayed());
    }

    @Test
    public void TC_03_Element_Undisplayed_Not_In_DOM(){
        homePage.clickToCloseSignUpPopup();
        homePage.sleepInSeconds(2);

        log.info("Verify FirstName textbox is not displayed");
        verifyTrue(homePage.isFirstNameTextboxUnDisplayed());

        log.info("Verify SureName textbox is not displayed");
        verifyTrue(homePage.isSureNameTextboxUnDisplayed());

        log.info("Verify Email textbox is not displayed");
        verifyTrue(homePage.isEmailTextboxUnDisplayed());

        log.info("Verify Password textbox is not displayed");
        verifyTrue(homePage.isPasswordTextboxUnDisplayed());

    }


    @AfterClass
    public void afterClass(){
        closeBrowser();
    }



}
