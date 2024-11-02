package pageObjects.orangeHRM;

import commons.BasePage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageUIs.orangeHRM.CommonElementUI;

import java.time.Duration;
import java.util.Date;

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

    public boolean isPageLoadedSuccess(){
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        return explicitWait.until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                return isElementUnDisplayed(driver, CommonElementUI.LOADING_SPINNER);
            }
        });
    }

}
