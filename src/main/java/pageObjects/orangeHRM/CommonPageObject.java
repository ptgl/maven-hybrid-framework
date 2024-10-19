package pageObjects.orangeHRM;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.orangeHRM.CommonPageUI;

public class CommonPageObject extends BasePage {
    private WebDriver driver;

    public CommonPageObject(WebDriver driver){
        this.driver = driver;
    }

    public String getHeaderTitle(){
        waitForElementVisible(driver, CommonPageUI.HEADER_TITLE);
        return getElementText(driver, CommonPageUI.HEADER_TITLE);
    }

    public String getActiveMenuItemText(){
        waitForElementVisible(driver, CommonPageUI.ACTIVE_MENU_ITEM_TEXT);
        return getElementText(driver, CommonPageUI.ACTIVE_MENU_ITEM_TEXT);
    }

    public void clickMenuItemLink(String itemName){
        waitForElementVisible(driver, CommonPageUI.MENU_ITEM_LINK, itemName);
        clickToElement(driver, CommonPageUI.MENU_ITEM_LINK, itemName);
    }

}
