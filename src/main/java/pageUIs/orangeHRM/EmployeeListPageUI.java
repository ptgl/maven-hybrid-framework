package pageUIs.orangeHRM;

public class EmployeeListPageUI {
    public static final String ADD_BUTTON = "xpath=//button[contains(string(),'Add')]";
    public static final String EMPLOYEE_ID_TEXTBOX = "xpath=//label[text()='Employee Id']/../following-sibling::div/input";
    public static final String SEARCH_BUTTON = "CSS=button[type=submit]";
    public static final String RECORD_FOUND_TEXT = "XPATH=//span[text()='(%s) Record Found']";
    public static final String EMPLOYEE_ID_CELL = "XPATH=//div[@role='cell']/div[text()='%s']";

}
