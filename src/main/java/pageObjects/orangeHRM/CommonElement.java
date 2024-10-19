package pageObjects.orangeHRM;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.orangeHRM.CommonElementUI;

public class CommonElement extends BasePage {
    private WebDriver driver;

    public CommonElement(WebDriver driver){
        this.driver = driver;
    }

    public String getHeaderTitle(){
        waitForElementVisible(driver, CommonElementUI.HEADER_TITLE);
        return getElementText(driver, CommonElementUI.HEADER_TITLE);
    }

    public String getActiveMenuItemText(){
        waitForElementVisible(driver, CommonElementUI.ACTIVE_SIDEBAR_MENU_ITEM_TEXT);
        return getElementText(driver, CommonElementUI.ACTIVE_SIDEBAR_MENU_ITEM_TEXT);
    }

    public void clickSidebarMenuItemLink(String itemName){
        waitForElementVisible(driver, CommonElementUI.SIDEBAR_MENU_ITEM_LINK, itemName);
        clickToElement(driver, CommonElementUI.SIDEBAR_MENU_ITEM_LINK, itemName);
    }

    public String getActiveTopbarItem(){
        waitForElementVisible(driver, CommonElementUI.ACTIVE_TOPBAR_ITEM_LINK_TEXT);
        return getElementText(driver, CommonElementUI.ACTIVE_TOPBAR_ITEM_LINK_TEXT);
    }

}
