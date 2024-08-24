package com.nopcommerce.account;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;

public class Level_01_DRY {
    WebDriver driver;

    @BeforeClass
    public void beforeClass(){
        driver = new ChromeDriver();
    }

    @Test
    public void Register_01_Empty_Data(){
        driver.get("https://demo.nopcommerce.com/");
        driver.findElement(By.cssSelector("a.ico-register")).click();
        driver.findElement(By.id("register-button")).click();

        Assert.assertEquals(driver.findElement(By.id("ConfirmPassword-error")).getText(), "Password is required.");
        Assert.assertEquals(driver.findElement(By.id("Email-error")).getText(), "Email is required.");
        Assert.assertEquals(driver.findElement(By.id("LastName-error")).getText(), "Last name is required.");
        Assert.assertEquals(driver.findElement(By.id("FirstName-error")).getText(), "First name is required.");

    }

    @Test
    public void Register_02_Invalid_Email(){
        driver.get("https://demo.nopcommerce.com/");
        driver.findElement(By.cssSelector("a.ico-register")).click();
        driver.findElement(By.id("FirstName")).sendKeys("Harry");
        driver.findElement(By.id("LastName")).sendKeys("Potter");
        driver.findElement(By.id("FirstName")).sendKeys("Harry");
        driver.findElement(By.id("Email")).sendKeys("automation");
        driver.findElement(By.id("Password")).sendKeys("123456789");
        driver.findElement(By.id("ConfirmPassword")).sendKeys("123456789");
        driver.findElement(By.id("register-button")).click();

        Assert.assertEquals(driver.findElement(By.id("Email-error")).getText(), "Please enter a valid email address.");

    }

    @Test
    public void Register_03_Invalid_Pass(){
        driver.get("https://demo.nopcommerce.com/");
        driver.findElement(By.cssSelector("a.ico-register")).click();
        driver.findElement(By.id("FirstName")).sendKeys("Harry");
        driver.findElement(By.id("LastName")).sendKeys("Potter");
        driver.findElement(By.id("FirstName")).sendKeys("Harry");
        driver.findElement(By.id("Email")).sendKeys("automation@gmail.com");
        driver.findElement(By.id("Password")).sendKeys("123");
        driver.findElement(By.id("ConfirmPassword")).sendKeys("123");
        driver.findElement(By.id("register-button")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("span[data-valmsg-for='Password']")).getText(), "<p>Password must meet the following rules: </p><ul><li>must have at least 6 characters and not greater than 64 characters</li></ul>");

    }

    @Test
    public void Register_04_Incorrect_Confirm_Pass(){
        driver.get("https://demo.nopcommerce.com/");
        driver.findElement(By.cssSelector("a.ico-register")).click();
        driver.findElement(By.id("FirstName")).sendKeys("Harry");
        driver.findElement(By.id("LastName")).sendKeys("Potter");
        driver.findElement(By.id("FirstName")).sendKeys("Harry");
        driver.findElement(By.id("Email")).sendKeys("automation@mail.com");
        driver.findElement(By.id("Password")).sendKeys("123456789");
        driver.findElement(By.id("ConfirmPassword")).sendKeys("123");
        driver.findElement(By.id("register-button")).click();

        Assert.assertEquals(driver.findElement(By.id("ConfirmPassword-error")).getText(), "The password and confirmation password do not match.");


    }

    @Test
    public void Register_05_Sucess(){
        driver.get("https://demo.nopcommerce.com/");
        driver.findElement(By.cssSelector("a.ico-register")).click();
        driver.findElement(By.id("FirstName")).sendKeys("Harry");
        driver.findElement(By.id("LastName")).sendKeys("Potter");
        driver.findElement(By.id("FirstName")).sendKeys("Harry");
        driver.findElement(By.id("Email")).sendKeys(getRandomEmail());
        driver.findElement(By.id("Password")).sendKeys("123456789");
        driver.findElement(By.id("ConfirmPassword")).sendKeys("123456789");
        driver.findElement(By.id("register-button")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("div.result")).getText(),"Your registration completed");
    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }

    private String getRandomEmail(){
        return "automation" + new Random().nextInt(99999) + "@gmail.com";
    }

}
