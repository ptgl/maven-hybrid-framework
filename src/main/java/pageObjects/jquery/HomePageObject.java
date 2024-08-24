package pageObjects.jquery;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageUIs.jquery.HomePageUI;

import java.util.ArrayList;
import java.util.List;

public class HomePageObject extends BasePage {
    WebDriver driver;
    public HomePageObject(WebDriver driver){
        this.driver = driver;
    }

    public void inputToColumnTextBoxByName(String columnName, String value){
        waitForElementVisible(driver, HomePageUI.COLUMN_TEXTBOX_BY_NAME, columnName);
        sendKeyToElement(driver, HomePageUI.COLUMN_TEXTBOX_BY_NAME, value, columnName);
    }

    public void clickToPageByNumber(String pageNumber){
        waitForElementClickable(driver, HomePageUI.PAGE_LINK_BY_NUMBER, pageNumber);
        clickToElement(driver, HomePageUI.PAGE_LINK_BY_NUMBER, pageNumber);
    }

    public boolean isPageActiveByNumber(String pageNumber){
        waitForElementVisible(driver, HomePageUI.PAGE_LINK_ACTIVE_BY_NUMBER, pageNumber);
        return isElementDisplayed(driver, HomePageUI.PAGE_LINK_ACTIVE_BY_NUMBER, pageNumber);
    }

    public  boolean isRowValuesDisplayed(String females, String country, String males, String total){
        waitForElementVisible(driver, HomePageUI.DYNAMIC_ROW_VALUES, females, country, males, total);
        return isElementDisplayed(driver, HomePageUI.DYNAMIC_ROW_VALUES, females, country, males, total);
    }

    public void clickToRowActionByCountry(String country, String rowAction){
        waitForElementClickable(driver, HomePageUI.ROW_ACTION_BY_COUNTRY, country, rowAction);
        clickToElement(driver, HomePageUI.ROW_ACTION_BY_COUNTRY, country, rowAction);
    }

    public List<String> getAllPageValuesByColumn(String columnName){
        List<String> allValues = new ArrayList<String>();
        List<WebElement> allPages = getWebElements(driver, HomePageUI.ALL_PAGES_LINK);

        int columnIdx = getListElementSize(driver, HomePageUI.COLUMN_INDEX_COLUMN_NAME, columnName) + 1;

        //loop through all pages
        for (WebElement page : allPages){
            page.click();
            sleepInSeconds(1);

            //get all column value of a page
            List<WebElement> allColumnValue = getWebElements(driver, HomePageUI.ALL_VALUE_BY_COLUMN_INDEX, String.valueOf(columnIdx));

            for(WebElement value : allColumnValue){
                allValues.add(value.getText());
            }
        }

        allValues.forEach(a-> System.out.println(a));
        return allValues;
    }

    public void enterToTextboxByColumnNameAndRowIndex(String columnName, String rowIndex, String value) {
        int columnIdx = getListElementSize(driver, HomePageUI.DYNAMIC_COLUMN_INDEX_COLUMN_NAME, columnName) + 1;
        waitForElementVisible(driver, HomePageUI.DYNAMIC_TEXTBOX_BY_COLUMN_INDEX_AND_ROW_INDEX, String.valueOf(columnIdx), rowIndex);
        sendKeyToElement(driver, HomePageUI.DYNAMIC_TEXTBOX_BY_COLUMN_INDEX_AND_ROW_INDEX, value, String.valueOf(columnIdx), rowIndex);
    }

    public void selectDropdownByRowIndex(String rowIndex, String value) {
        int columnIdx = getListElementSize(driver, HomePageUI.DYNAMIC_COLUMN_INDEX_COLUMN_NAME, "Country") + 1;
        waitForElementVisible(driver, HomePageUI.DYNAMIC_DROPDOWN_BY_COLUMN_INDEX_AND_ROW_INDEX, String.valueOf(columnIdx), rowIndex);
        selectItemInDefaultDropdown(driver, HomePageUI.DYNAMIC_DROPDOWN_BY_COLUMN_INDEX_AND_ROW_INDEX, value, String.valueOf(columnIdx), rowIndex);
    }

    public void clickToCheckboxByRowIndex(String rowIndex) {
        int columnIdx = getListElementSize(driver, HomePageUI.DYNAMIC_COLUMN_INDEX_COLUMN_NAME, "NPO?") + 1;
        waitForElementClickable(driver, HomePageUI.DYNAMIC_CHECKBOX_BY_COLUMN_INDEX_AND_ROW_INDEX, String.valueOf(columnIdx), rowIndex);
        checkTheCheckboxOrRadio(driver, HomePageUI.DYNAMIC_CHECKBOX_BY_COLUMN_INDEX_AND_ROW_INDEX, String.valueOf(columnIdx), rowIndex);
    }
}
