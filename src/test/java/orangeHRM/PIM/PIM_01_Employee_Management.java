package orangeHRM.PIM;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.orangeHRM.LoginPageObject;
import pageObjects.orangeHRM.PageGeneratorManager;

public class PIM_01_Employee_Management extends BaseTest {

    private WebDriver driver;
    private LoginPageObject loginPage;

    @Parameters({"browser","url"})
    @BeforeClass
    public void beforeClass(String browserName, String url){
        driver = getBrowserDriver(browserName, url);
        loginPage = PageGeneratorManager.getLoginPage(driver);
    }

    @Test
    public void Employee_00_Login_Success(){
        loginPage.enterToUserNameTextbox("Admin");
        loginPage.enterToPasswordTextbox("admin123");
        loginPage.clickLoginButton();

    }
    @Test
    public void Employee_01_Add_new_employee(){

    }

    @Test
    public void Employee_02_Search_employee(){

    }

    @Test
    public void Employee_03_Upload_avatar(){

    }

    @Test
    public void Employee_05_Add_contact_details(){

    }

    @Test
    public void Employee_06_Add_emergency_details(){

    }

    @Test
    public void Employee_07_Add_dependents(){

    }

    @Test
    public void Employee_08_Add_job(){

    }
    @Test
    public void Employee_09_Add_salary(){

    } @Test
    public void Employee_10_Add_qualifications(){

    }

    @AfterClass
    public void afterClass(){
        closeBrowser();
    }

}