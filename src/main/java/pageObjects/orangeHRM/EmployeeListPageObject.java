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

    public String getEmployeeNameHeader() {
        return getElementText(driver, EmployeeListPageUI.NAME_HEADER );
    }
}
