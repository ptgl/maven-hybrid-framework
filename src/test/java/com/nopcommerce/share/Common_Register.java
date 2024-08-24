package com.nopcommerce.share;

import commons.BaseTest;
import commons.PageGeneratorManager;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pageObjects.user.CustomerPageObject;
import pageObjects.user.HomePageObject;
import pageObjects.user.RegisterPageObject;
import pageObjects.user.UserLoginPageObject;

import java.util.Set;

public class Common_Register extends BaseTest {
    private WebDriver driver;
    private HomePageObject homePageObject;
    private RegisterPageObject registerPageObject;
    private UserLoginPageObject userLoginPageObject;
    public static String email, password, firstName, lastName;
    public static Set<Cookie> cookies;

    @Parameters("browser")
    @BeforeTest
    public void beforeClass(String browserName){
        driver = getBrowserDriver(browserName, null);
        homePageObject = PageGeneratorManager.getHomePage(driver);

        this.firstName = "Harry";
        this.lastName = "Potter";
        email = getRandomEmail();
        password = "123456789";

        registerPageObject = homePageObject.clickToRegisterLink();

        registerPageObject = new RegisterPageObject(driver);
        registerPageObject.enterToFirstNameTextBox(firstName);
        registerPageObject.enterToLastNameTextBox(lastName);
        registerPageObject.enterToEmailTextBox(email);
        registerPageObject.enterToPasswordTextBox(password);
        registerPageObject.enterToConfirmPasswordTextBox(password);

        registerPageObject.clickToRegisterButton();

        Assert.assertEquals(registerPageObject.getRegisterSuccessMessageText(), "Your registration completed");

        homePageObject = registerPageObject.clickToNopCommerceLogo();
        homePageObject.clickToLogoutLink();
        userLoginPageObject = homePageObject.clickToLoginLink();
        userLoginPageObject.enterEmailTextBox(email);
        userLoginPageObject.enterPasswordTextBox(password);
        homePageObject = userLoginPageObject.clickToLoginButton();
        homePageObject.sleepInSeconds(3);

        cookies = homePageObject.getCookies(driver);

        System.out.println("email at Common "+email);
        System.out.println("pwd at Common "+password);

        closeBrowser();
    }

}
