package pageObjects.orangeHRM;

import org.openqa.selenium.WebDriver;
import pageUIs.orangeHRM.EmployeeListPageUI;

public class EmployeeListPageObject extends CommonElement{
    private WebDriver driver;
    public EmployeeListPageObject(WebDriver driver){
        super(driver);
        this.driver = driver;
    }

    public AddEmployeePageObject clickAddButton() {
        waitForElementClickable(driver, EmployeeListPageUI.ADD_BUTTON);
        clickToElement(driver, EmployeeListPageUI.ADD_BUTTON);
        return PageGeneratorManager.getAddEmployeePage(driver);
    }

    public void clickSearchButton(){
        waitForElementClickable(driver, EmployeeListPageUI.SEARCH_BUTTON);
        clickToElement(driver, EmployeeListPageUI.SEARCH_BUTTON);
    }

    public PersonalDetailsPageObject clickEmployeeId(String employeeId){
        waitForElementClickable(driver, EmployeeListPageUI.EMPLOYEE_ID_CELL, employeeId);
        clickToElement(driver, EmployeeListPageUI.EMPLOYEE_ID_CELL, employeeId);
        return PageGeneratorManager.getPersonalDetailsPage(driver);
    }

    public void inputEmployeeId(String employeeId) {
        waitForElementVisible(driver, EmployeeListPageUI.EMPLOYEE_ID_TEXTBOX);
        sendKeyToElement(driver, EmployeeListPageUI.EMPLOYEE_ID_TEXTBOX, employeeId);
    }

    public boolean isRecordFound(int numberFound){
        return isElementDisplayed(driver, EmployeeListPageUI.RECORD_FOUND_TEXT,  String.valueOf(numberFound));
    }

    public boolean isUserRecordFoundById(String employeeId){
        return isElementDisplayed(driver, EmployeeListPageUI.EMPLOYEE_ID_CELL, employeeId);
    }
}
