package com.nopcommerce.account;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;

public class Level_02_BasePage_Inheritance extends BasePage{
    private WebDriver driver;

    @BeforeClass
    public void beforeClass(){
        driver = new ChromeDriver();
    }

    @Test
    public void Register_01_Empty_Data(){
        openPageUrl(driver, "https://demo.nopcommerce.com/");
        clickToElement(driver, "//a[@class='ico-register']");
        clickToElement(driver, "//*[@id='register-button']");

        Assert.assertEquals(getElementText(driver, "//*[@id='ConfirmPassword-error']"), "Password is required.");
        Assert.assertEquals(getElementText(driver, "//*[@id='Email-error']"), "Email is required.");
        Assert.assertEquals(getElementText(driver, "//*[@id='LastName-error']"), "Last name is required.");
        Assert.assertEquals(getElementText(driver, "//*[@id='FirstName-error']"), "First name is required.");

    }

    @Test
    public void Register_02_Invalid_Email(){
        openPageUrl(driver, "https://demo.nopcommerce.com/");
        clickToElement(driver, "//a[@class='ico-register']");

        sendKeyToElement(driver, "//*[@id='FirstName']", "Harry");
        sendKeyToElement(driver, "//*[@id='LastName']", "Potter");
        sendKeyToElement(driver, "//*[@id='Email']", "automation");
        sendKeyToElement(driver, "//*[@id='Password']", "123456789");
        sendKeyToElement(driver, "//*[@id='ConfirmPassword']", "123456789");

        clickToElement(driver, "//*[@id='register-button']");

        Assert.assertEquals(getElementText(driver,"//*[@id='Email-error']"), "Please enter a valid email address.");

    }

    @Test
    public void Register_03_Invalid_Pass(){
        openPageUrl(driver, "https://demo.nopcommerce.com/");
        clickToElement(driver, "//a[@class='ico-register']");

        sendKeyToElement(driver, "//*[@id='FirstName']", "Harry");
        sendKeyToElement(driver, "//*[@id='LastName']", "Potter");
        sendKeyToElement(driver, "//*[@id='Email']", "automation@gmail.com");
        sendKeyToElement(driver, "//*[@id='Password']", "123");
        sendKeyToElement(driver, "//*[@id='ConfirmPassword']", "123");

        clickToElement(driver, "//*[@id='register-button']");

        Assert.assertEquals(getElementText(driver, "//span[@data-valmsg-for='Password']") , "<p>Password must meet the following rules: </p><ul><li>must have at least 6 characters and not greater than 64 characters</li></ul>");

    }

    @Test
    public void Register_04_Incorrect_Confirm_Pass(){
        openPageUrl(driver, "https://demo.nopcommerce.com/");
        clickToElement(driver, "//a[@class='ico-register']");

        sendKeyToElement(driver, "//*[@id='FirstName']", "Harry");
        sendKeyToElement(driver, "//*[@id='LastName']", "Potter");
        sendKeyToElement(driver, "//*[@id='Email']", "automation@gmail.com");
        sendKeyToElement(driver, "//*[@id='Password']", "123456789");
        sendKeyToElement(driver, "//*[@id='ConfirmPassword']", "123");

        clickToElement(driver, "//*[@id='register-button']");

        Assert.assertEquals(getElementText(driver, "//*[@id='ConfirmPassword-error']") , "The password and confirmation password do not match.");

    }

    @Test
    public void Register_05_Sucess(){
        openPageUrl(driver, "https://demo.nopcommerce.com/");
        clickToElement(driver, "//a[@class='ico-register']");

        sendKeyToElement(driver, "//*[@id='FirstName']", "Harry");
        sendKeyToElement(driver, "//*[@id='LastName']", "Potter");
        sendKeyToElement(driver, "//*[@id='Email']", getRandomEmail());
        sendKeyToElement(driver, "//*[@id='Password']", "123456789");
        sendKeyToElement(driver, "//*[@id='ConfirmPassword']", "123456789");

        clickToElement(driver, "//*[@id='register-button']");

        Assert.assertEquals(getElementText(driver, "//div[@class='result']") , "Your registration completed");
    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }

    private String getRandomEmail(){
        return "automation" + new Random().nextInt(99999) + "@gmail.com";
    }

}
