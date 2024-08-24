package com.jquery.table;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.jquery.HomePageObject;
import pageObjects.jquery.PageGeneratorManager;
import pageObjects.user.*;

public class Level_10_Handle_DataTable extends BaseTest {
    private WebDriver driver;
    HomePageObject homePage;

    @Parameters({"browser", "url"})
    @BeforeClass
    public void beforeClass(String browserName, String url){
        driver = getBrowserDriver(browserName, url);
        homePage = PageGeneratorManager.getHomePage(driver);
    }

    @Test
    public void TC_01_Search(){
        homePage.inputToColumnTextBoxByName("Females", "625");

        homePage.inputToColumnTextBoxByName("Country", "Aruba");

        homePage.inputToColumnTextBoxByName("Males", "756");

        homePage.inputToColumnTextBoxByName("Total", "1504");



    }

    @Test
    public void TC_02_Paging(){
        homePage.clickToPageByNumber("10");
        homePage.sleepInSeconds(1);
        Assert.assertTrue(homePage.isPageActiveByNumber("10"));

        homePage.clickToPageByNumber("4");
        homePage.sleepInSeconds(1);
        Assert.assertTrue(homePage.isPageActiveByNumber("4"));

        homePage.clickToPageByNumber("23");
        homePage.sleepInSeconds(1);
        Assert.assertTrue(homePage.isPageActiveByNumber("23"));
    }

    @Test
    public void TC_03_Data_Displayed(){
        homePage.refresh(driver);
        homePage.inputToColumnTextBoxByName("Females", "777");
        homePage.inputToColumnTextBoxByName("Country", "Antigua and Barbuda");
        homePage.inputToColumnTextBoxByName("Males", "803");
        homePage.inputToColumnTextBoxByName("Total", "1580");
        homePage.sleepInSeconds(2);

        Assert.assertTrue(homePage.isRowValuesDisplayed("777", "Antigua and Barbuda", "803", "1580"));
        homePage.refresh(driver);
    }

    @Test
    public void TC_04_Icon_Button(){

        homePage.clickToRowActionByCountry("Afghanistan", "remove");
        homePage.clickToRowActionByCountry("AFRICA", "remove");
        homePage.clickToRowActionByCountry("Albania", "remove");
        homePage.clickToRowActionByCountry("Angola", "remove");

        homePage.refresh(driver);
        homePage.clickToRowActionByCountry("Afghanistan", "edit");

        homePage.refresh(driver);
        homePage.clickToRowActionByCountry("Angola", "edit");

        homePage.refresh(driver);
    }

    @Test
    public void TC_05_Get_All_Column_Values(){
        homePage.getAllPageValuesByColumn("Country");
    }

    @Test
    public void TC_06_Action_By_Index(){
        homePage.openPageUrl(driver,"https://www.jqueryscript.net/demo/jQuery-Dynamic-Data-Grid-Plugin-appendGrid/");

        homePage.enterToTextboxByColumnNameAndRowIndex("Contact Person", "2", "Harry");
        homePage.enterToTextboxByColumnNameAndRowIndex("Company", "3", "Katalon");

        homePage.selectDropdownByRowIndex("2", "Japan");
        homePage.selectDropdownByRowIndex("1", "Taiwan");

        homePage.clickToCheckboxByRowIndex("2");
        homePage.clickToCheckboxByRowIndex("1");
        homePage.clickToCheckboxByRowIndex("3");

        homePage.sleepInSeconds(5);

    }

    @AfterClass
    public void afterClass(){
        closeBrowser();
    }



}
