package orangeHRM.PIM;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import commons.BaseTest;
import constants.OrangeHRMConstants.PIMSidebar;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.orangeHRM.*;
import utilities.FakerConfig;
import utilities.Utilities;

import static constants.OrangeHRMConstants.HomePageSidebar.PIM;

public class PIM_01_Employee_Management extends BaseTest {

    private WebDriver driver;
    private LoginPageObject loginPage;
    private DashboardPageObject dashboardPage;
    private EmployeeListPageObject employeeListPage;
    private AddEmployeePageObject addEmployeePage;
    private PersonalDetailsPageObject personalDetailsPage;
    String employeeId;
    String firstName,lastName,middleName,username,password;

    @Parameters({"browser","url"})
    @BeforeClass
    public void beforeClass(String browserName, String url){
        driver = getBrowserDriver(browserName, url);
        loginPage = PageGeneratorManager.getLoginPage(driver);

        FakerConfig faker = FakerConfig.getFaker();
        firstName = faker.getFirstName();
        lastName = faker.getLastName();
        middleName = faker.getMidleName();
        password = faker.getPassword();
        username = faker.getEmail();
    }

    @Test
    public void Employee_00_Login_Success(){
        //loginPage.enterToUserNameTextbox("admin");
        //loginPage.enterToPasswordTextbox("T8#kL9@wQ3zX!p");
        loginPage.enterToUserNameTextbox("Admin");
        loginPage.enterToPasswordTextbox("admin123");
        dashboardPage = loginPage.clickLoginButton();

        Assert.assertEquals(dashboardPage.getHeaderTitle(), "Dashboard");
        Assert.assertEquals(dashboardPage.getActiveMenuItemText(), "Dashboard");

    }
    @Test
    public void Employee_01_Add_new_employee(){
        dashboardPage.clickSidebarMenuItemLink(PIM);
        employeeListPage = PageGeneratorManager.getEmployeeListPage(driver);

        Assert.assertEquals(employeeListPage.getHeaderTitle(), "PIM");
        Assert.assertEquals(employeeListPage.getActiveTopbarItem(), "Employee List");

        addEmployeePage = employeeListPage.clickAddButton();
        addEmployeePage.isPageLoadedSuccess();
        Assert.assertEquals(employeeListPage.getActiveTopbarItem(), "Add Employee");
        this.employeeId = addEmployeePage.getEmployeeId();

        addEmployeePage.enterEmployeeFullName(firstName, middleName, lastName);
        addEmployeePage.turnOnCreateLoginDetails();
        addEmployeePage.enterUsername(username);
        addEmployeePage.enterPassword(password);
        addEmployeePage.enterConfirmPassword(password);

        personalDetailsPage = addEmployeePage.clickSaveButton();
        personalDetailsPage.isPageLoadedSuccess();
        Assert.assertEquals(personalDetailsPage.getActiveTopbarItem(), "Employee List");

        Assert.assertEquals(personalDetailsPage.getEmployeeNameHeader(), firstName+" "+lastName);
        Assert.assertEquals(personalDetailsPage.getFirstNameTextboxValue(), firstName);
        Assert.assertEquals(personalDetailsPage.getLastNameTextboxValue(), lastName);
        Assert.assertEquals(personalDetailsPage.getMiddleNameTextboxValue(), middleName);
        Assert.assertEquals(personalDetailsPage.getEmployeeIdTextboxValue(), employeeId);

    }



    @Test
    public void Employee_02_Search_employee(){
        personalDetailsPage.clickTopbarItem("Employee List");
        employeeListPage = PageGeneratorManager.getEmployeeListPage(driver);
        employeeListPage.isPageLoadedSuccess();
        employeeListPage.inputEmployeeId(this.employeeId);
        employeeListPage.clickSearchButton();

        Assert.assertTrue(employeeListPage.isRecordFound(1));
        Assert.assertTrue(employeeListPage.isUserRecordFoundById(employeeId));

    }

    @Test
    public void Employee_03_Update_personal_details(){
        personalDetailsPage = employeeListPage.clickEmployeeId(this.employeeId);
        personalDetailsPage.clickSidebarMenuItemLink(PIMSidebar.CONTACT_DETAILS);

    }

    //@Test
    public void Employee_03_Upload_avatar(){

    }

    //@Test
    public void Employee_05_Add_contact_details(){

    }

    //@Test
    public void Employee_06_Add_emergency_details(){

    }

    //@Test
    public void Employee_07_Add_dependents(){

    }

    //@Test
    public void Employee_08_Add_job(){

    }
    //@Test
    public void Employee_09_Add_salary(){

    }
    //@Test
    public void Employee_10_Add_qualifications(){

    }

    @AfterClass
    public void afterClass(){
        //closeBrowser();
    }

}
