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
        return getTextboxValue(AddEmployeePageUI.EMPLOYEE_ID_TEXTBOX);
    }

    public void turnOnCreateLoginDetails() {
        if(!isCheckboxSelected()){
            clickToElement(driver, AddEmployeePageUI.CREATE_LOGIN_DETAIL_CHECKBOX);
        }

    }

    public void turnOffCreateLoginDetails() {
        if(isCheckboxSelected()){
            clickToElement(driver, AddEmployeePageUI.CREATE_LOGIN_DETAIL_CHECKBOX);
        }

    }

    public void enterUsername(String username) {
        waitForElementVisible(driver, AddEmployeePageUI.USERNAME_TEXTBOX);
        sendKeyToElement(driver, AddEmployeePageUI.USERNAME_TEXTBOX, username);
    }

    public void enterPassword(String password) {
        waitForElementVisible(driver, AddEmployeePageUI.PASSWORD_TEXTBOX);
        sendKeyToElement(driver, AddEmployeePageUI.PASSWORD_TEXTBOX, password);
    }

    public void enterConfirmPassword(String password) {
        waitForElementVisible(driver, AddEmployeePageUI.CONFIRM_PASSWORD_TEXTBOX);
        sendKeyToElement(driver, AddEmployeePageUI.CONFIRM_PASSWORD_TEXTBOX, password);
    }

    public PersonalDetailsPageObject clickSaveButton() {
        waitForElementClickable(driver, AddEmployeePageUI.SAVE_BUTTON);
        clickToElement(driver, AddEmployeePageUI.SAVE_BUTTON);
        return PageGeneratorManager.getPersonalDetailsPage(driver);
    }

    private boolean isCheckboxSelected(){
     return  (boolean) executeForBrowserByJS(driver, "return !!document.querySelector('input[type=checkbox]:checked')");
    }
}
