package pageObjects.orangeHRM;

import org.openqa.selenium.WebDriver;
import pageUIs.orangeHRM.PersonalDetailsPageUI;

public class PersonalDetailsPageObject extends CommonElement{
    private WebDriver driver;
    public PersonalDetailsPageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public String getEmployeeNameHeader() {
        return getElementText(driver, PersonalDetailsPageUI.NAME_HEADER );
    }

    public String getFirstNameTextboxValue(){
        return getTextboxValue(PersonalDetailsPageUI.FIRSTNAME_TEXTBOX);
    }

    public String getLastNameTextboxValue(){
        return getTextboxValue(PersonalDetailsPageUI.LASTNAME_TEXTBOX);
    }

    public String getMiddleNameTextboxValue(){
        return getTextboxValue(PersonalDetailsPageUI.MIDDLENAME_TEXTBOX);
    }

    public String getEmployeeIdTextboxValue(){
        return getTextboxValue(PersonalDetailsPageUI.EMPLOYEE_ID_TEXTBOX);
    }
}
