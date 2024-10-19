package pageObjects.orangeHRM;

import org.openqa.selenium.WebDriver;
import pageUIs.orangeHRM.AddEmployeePageUI;

public class AddEmployeePageObject extends CommonElement{
    private WebDriver driver;
    public AddEmployeePageObject(WebDriver driver){
        super(driver);
        this.driver = driver;
    }

    public void enterEmployeeFullName(String firstName, String lastName) {
        waitForElementVisible(driver, AddEmployeePageUI.FIRST_NAME_TEXTBOX);
        sendKeyToElement(driver, AddEmployeePageUI.FIRST_NAME_TEXTBOX, firstName);
        sendKeyToElement(driver, AddEmployeePageUI.LAST_NAME_TEXTBOX, lastName);
    }

    public void enterEmployeeFullName(String firstName, String middleName, String lastName) {
        enterEmployeeFullName(firstName, lastName);
        sendKeyToElement(driver, AddEmployeePageUI.MIDDLE_NAME_TEXTBOX, middleName);
    }

    public String getEmployeeId() {
        waitForElementVisible(driver, AddEmployeePageUI.EMPLOYEE_ID_TEXTBOX);
        return executeOnElementByJS(driver,"return arguments[0]._value", AddEmployeePageUI.EMPLOYEE_ID_TEXTBOX).toString();
    }

    public void turnOnCreateLoginDetails() {
    }

    public void enterUsername() {
    }

    public void enterPassword() {
    }

    public void enterConfirmPassword() {
    }

    public void clickSaveButton() {
    }
}
