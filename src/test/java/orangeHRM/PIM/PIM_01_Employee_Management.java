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
import testData.OrangeHRMModel.EmployeeInfo;
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
    private ContactDetailsPageObject contactDetailsPageObject;
    //private String employeeId;
    private EmployeeInfo employeeInfo;
    //String firstName,lastName,middleName,username,password;
    private FakerConfig faker;

    @Parameters({"browser","url"})
    @BeforeClass
    public void beforeClass(String browserName, String url){
        driver = getBrowserDriver(browserName, url);
        loginPage = PageGeneratorManager.getLoginPage(driver);

        faker = FakerConfig.getFaker();
        employeeInfo = EmployeeInfo.getEmployeeInfo();
        employeeInfo.setFirstName(faker.getFirstName());
        employeeInfo.setLastName(faker.getLastName());
        employeeInfo.setMiddleName(faker.getMidleName());
        employeeInfo.setPassword(faker.getPassword());
        employeeInfo.setUsername(faker.getEmail());
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
        employeeInfo.setEmployeeId(addEmployeePage.getEmployeeId());

        addEmployeePage.enterEmployeeFullName(employeeInfo.getFirstName(), employeeInfo.getMiddleName(), employeeInfo.getLastName());
        addEmployeePage.turnOnCreateLoginDetails();
        addEmployeePage.enterUsername(employeeInfo.getUsername());
        addEmployeePage.enterPassword(employeeInfo.getPassword());
        addEmployeePage.enterConfirmPassword(employeeInfo.getPassword());

        personalDetailsPage = addEmployeePage.clickSaveButton();
        personalDetailsPage.isPageLoadedSuccess();
        Assert.assertEquals(personalDetailsPage.getActiveTopbarItem(), "Employee List");

        Assert.assertEquals(personalDetailsPage.getEmployeeNameHeader(), employeeInfo.getFirstName()+" "+employeeInfo.getLastName());
        Assert.assertEquals(personalDetailsPage.getFirstNameTextboxValue(), employeeInfo.getFirstName());
        Assert.assertEquals(personalDetailsPage.getLastNameTextboxValue(), employeeInfo.getLastName());
        Assert.assertEquals(personalDetailsPage.getMiddleNameTextboxValue(), employeeInfo.getMiddleName());
        Assert.assertEquals(personalDetailsPage.getEmployeeIdTextboxValue(), employeeInfo.getEmployeeId());

    }



    @Test
    public void Employee_02_Search_employee(){
        personalDetailsPage.clickTopbarItem("Employee List");
        employeeListPage = PageGeneratorManager.getEmployeeListPage(driver);
        employeeListPage.isPageLoadedSuccess();
        employeeListPage.inputEmployeeId(employeeInfo.getEmployeeId());
        employeeListPage.clickSearchButton();

        Assert.assertTrue(employeeListPage.isRecordFound(1));
        Assert.assertTrue(employeeListPage.isUserRecordFoundById(employeeInfo.getEmployeeId()));

    }

    @Test
    public void Employee_03_Update_personal_details(){
        personalDetailsPage = employeeListPage.clickEmployeeId(employeeInfo.getEmployeeId());

    }

    @Test
    public void Employee_04_Update_contact_details(){
        personalDetailsPage.clickSidebarMenuItemLink(PIMSidebar.CONTACT_DETAILS);
        contactDetailsPageObject = PageGeneratorManager.getContactDetailsPage(driver);
        contactDetailsPageObject.isPageLoadedSuccess();

        String otherEmail = faker.getEmail();
        String workEmail = faker.getEmail();

        String street1 = faker.getStreet();
        String street2 = faker.getStreet();
        String city = faker.getCity();
        String state = faker.getState();
        String zip = faker.getZip();

        String homephone = faker.getPhone("089");
        String mobile = faker.getPhone("099");
        String workphone = faker.getPhone("028");

        contactDetailsPageObject.enterStreet1(street1);
        contactDetailsPageObject.enterStreet2(street2);
        contactDetailsPageObject.enterCity(city);
        contactDetailsPageObject.enterState(state);
        contactDetailsPageObject.enterZip(zip);

        contactDetailsPageObject.enterHomePhone(homephone);
        contactDetailsPageObject.enterMobile(mobile);
        contactDetailsPageObject.enterWorkPhone(workphone);

        contactDetailsPageObject.enterWorkEmail(workEmail);
        contactDetailsPageObject.enterOtherEmail(otherEmail);

        contactDetailsPageObject.selectCountry("Viet Nam");

        Assert.assertEquals(street1, contactDetailsPageObject.getStreet1TextboxValue()) ;
        Assert.assertEquals(street2, contactDetailsPageObject.getStreet2TextboxValue()) ;
        Assert.assertEquals(city, contactDetailsPageObject.getCityTextboxValue()) ;


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
