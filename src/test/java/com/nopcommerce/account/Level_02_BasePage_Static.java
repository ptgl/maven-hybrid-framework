package com.nopcommerce.account;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;

public class Level_02_BasePage_Static {
    private WebDriver driver;
    private BasePage basePage = BasePage.getBasePage();

    @BeforeClass
    public void beforeClass(){
        driver = new ChromeDriver();
    }

    @Test
    public void Register_01_Empty_Data(){
        basePage.openPageUrl(driver, "https://demo.nopcommerce.com/");
        basePage.clickToElement(driver, "//a[@class='ico-register']");
        basePage.clickToElement(driver, "//*[@id='register-button']");

        Assert.assertEquals(basePage.getElementText(driver, "//*[@id='ConfirmPassword-error']"), "Password is required.");
        Assert.assertEquals(basePage.getElementText(driver, "//*[@id='Email-error']"), "Email is required.");
        Assert.assertEquals(basePage.getElementText(driver, "//*[@id='LastName-error']"), "Last name is required.");
        Assert.assertEquals(basePage.getElementText(driver, "//*[@id='FirstName-error']"), "First name is required.");

    }

    @Test
    public void Register_02_Invalid_Email(){
        basePage.openPageUrl(driver, "https://demo.nopcommerce.com/");
        basePage.clickToElement(driver, "//a[@class='ico-register']");

        basePage.sendKeyToElement(driver, "//*[@id='FirstName']", "Harry");
        basePage.sendKeyToElement(driver, "//*[@id='LastName']", "Potter");
        basePage.sendKeyToElement(driver, "//*[@id='Email']", "automation");
        basePage.sendKeyToElement(driver, "//*[@id='Password']", "123456789");
        basePage.sendKeyToElement(driver, "//*[@id='ConfirmPassword']", "123456789");

        basePage.clickToElement(driver, "//*[@id='register-button']");

        Assert.assertEquals(basePage.getElementText(driver,"//*[@id='Email-error']"), "Please enter a valid email address.");

    }

    @Test
    public void Register_03_Invalid_Pass(){
        basePage.openPageUrl(driver, "https://demo.nopcommerce.com/");
        basePage.clickToElement(driver, "//a[@class='ico-register']");

        basePage.sendKeyToElement(driver, "//*[@id='FirstName']", "Harry");
        basePage.sendKeyToElement(driver, "//*[@id='LastName']", "Potter");
        basePage.sendKeyToElement(driver, "//*[@id='Email']", "automation@gmail.com");
        basePage.sendKeyToElement(driver, "//*[@id='Password']", "123");
        basePage.sendKeyToElement(driver, "//*[@id='ConfirmPassword']", "123");

        basePage.clickToElement(driver, "//*[@id='register-button']");

        Assert.assertEquals(basePage.getElementText(driver, "//span[@data-valmsg-for='Password']") , "<p>Password must meet the following rules: </p><ul><li>must have at least 6 characters and not greater than 64 characters</li></ul>");

    }

    @Test
    public void Register_04_Incorrect_Confirm_Pass(){
        basePage.openPageUrl(driver, "https://demo.nopcommerce.com/");
        basePage.clickToElement(driver, "//a[@class='ico-register']");

        basePage.sendKeyToElement(driver, "//*[@id='FirstName']", "Harry");
        basePage.sendKeyToElement(driver, "//*[@id='LastName']", "Potter");
        basePage.sendKeyToElement(driver, "//*[@id='Email']", "automation@gmail.com");
        basePage.sendKeyToElement(driver, "//*[@id='Password']", "123456789");
        basePage.sendKeyToElement(driver, "//*[@id='ConfirmPassword']", "123");

        basePage.clickToElement(driver, "//*[@id='register-button']");

        Assert.assertEquals(basePage.getElementText(driver, "//*[@id='ConfirmPassword-error']") , "The password and confirmation password do not match.");

    }

    @Test
    public void Register_05_Sucess(){
        basePage.openPageUrl(driver, "https://demo.nopcommerce.com/");
        basePage.clickToElement(driver, "//a[@class='ico-register']");

        basePage.sendKeyToElement(driver, "//*[@id='FirstName']", "Harry");
        basePage.sendKeyToElement(driver, "//*[@id='LastName']", "Potter");
        basePage.sendKeyToElement(driver, "//*[@id='Email']", getRandomEmail());
        basePage.sendKeyToElement(driver, "//*[@id='Password']", "123456789");
        basePage.sendKeyToElement(driver, "//*[@id='ConfirmPassword']", "123456789");

        basePage.clickToElement(driver, "//*[@id='register-button']");

        Assert.assertEquals(basePage.getElementText(driver, "//div[@class='result']") , "Your registration completed");
    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }

    private String getRandomEmail(){
        return "automation" + new Random().nextInt(99999) + "@gmail.com";
    }

}
