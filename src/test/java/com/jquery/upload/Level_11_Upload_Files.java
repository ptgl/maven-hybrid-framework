package com.jquery.upload;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.jquery.HomePageObject;
import pageObjects.jquery.PageGeneratorManager;
import pageObjects.jquery.UploadPageObject;

public class Level_11_Upload_Files extends BaseTest {
    private WebDriver driver;
    UploadPageObject uploadPage;
    String mountain = "mountain.jpg";
    String pink = "pink_red.jpg";
    String sakura = "sakura.jpg";
    String temple = "temple.jpg";
    String[] files = {mountain, pink, sakura, temple};


    @Parameters({"browser", "url"})
    @BeforeClass
    public void beforeClass(String browserName, String url){
        driver = getBrowserDriver(browserName, "https://blueimp.github.io/jQuery-File-Upload/");
        uploadPage = PageGeneratorManager.getUploadPage(driver);
    }

    @Test
    public void TC_01_Upload_Single_File(){
        uploadPage.uploadMutipleFiles(driver, sakura);
        uploadPage.uploadMutipleFiles(driver, mountain);
        uploadPage.uploadMutipleFiles(driver, pink);
        uploadPage.uploadMutipleFiles(driver, temple);

        Assert.assertTrue(uploadPage.isFileLoadedSuccess(sakura));
        Assert.assertTrue(uploadPage.isFileLoadedSuccess(mountain));
        Assert.assertTrue(uploadPage.isFileLoadedSuccess(pink));
        Assert.assertTrue(uploadPage.isFileLoadedSuccess(temple));

        uploadPage.clickStartButtonOnEachFile();

        Assert.assertTrue(uploadPage.isFileUploadedSuccess(sakura));
        Assert.assertTrue(uploadPage.isFileUploadedSuccess(mountain));
        Assert.assertTrue(uploadPage.isFileUploadedSuccess(pink));
        Assert.assertTrue(uploadPage.isFileUploadedSuccess(temple));
    }

    @Test
    public void TC_02_Upload_Multiple_File(){
        uploadPage.refresh(driver);
        uploadPage.uploadMutipleFiles(driver, files);

        Assert.assertTrue(uploadPage.isFileLoadedSuccess(sakura));
        Assert.assertTrue(uploadPage.isFileLoadedSuccess(mountain));
        Assert.assertTrue(uploadPage.isFileLoadedSuccess(pink));
        Assert.assertTrue(uploadPage.isFileLoadedSuccess(temple));

        uploadPage.clickStartButtonOnEachFile();

        Assert.assertTrue(uploadPage.isFileUploadedSuccess(sakura));
        Assert.assertTrue(uploadPage.isFileUploadedSuccess(mountain));
        Assert.assertTrue(uploadPage.isFileUploadedSuccess(pink));
        Assert.assertTrue(uploadPage.isFileUploadedSuccess(temple));

    }

    @AfterClass
    public void afterClass(){
        closeBrowser();
    }



}
