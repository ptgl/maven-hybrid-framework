package commons;

import org.openqa.selenium.WebDriver;
import pageObjects.user.HomePageObject;
import pageUIs.user.BaseElementUI;

public class BaseElement extends BasePage{
    WebDriver driver;

    public BaseElement(WebDriver driver){
        this.driver = driver;
    }



    public void clickToHeaderLinkByName(String pageName){
        waitForElementClickable(driver, BaseElementUI.DYNAMIC_HEADER_LINK_BY_NAME, pageName);
        clickToElement(driver, BaseElementUI.DYNAMIC_HEADER_LINK_BY_NAME, pageName);
    }
    public HomePageObject clickToNopCommerceLogo() {
        waitForElementClickable(driver, BaseElementUI.NOP_COMMERCE_LOGO);
        clickToElement(driver, BaseElementUI.NOP_COMMERCE_LOGO);
        return PageGeneratorManager.getHomePage(driver);
    }

    public void clickToButtonByText(String buttonText){
        waitForElementClickable(driver, BaseElementUI.DYNAMIC_BUTTON_BY_TEXT, buttonText);
        clickToElement(driver, BaseElementUI.DYNAMIC_BUTTON_BY_TEXT, buttonText);
    }

    public String getTextboxErrorMessageByID(String errorMessageID){
        waitForElementVisible(driver, BaseElementUI.DYNAMIC_TEXTBOX_ERROR_MSG_BY_ID, errorMessageID);
        return getElementText(driver, BaseElementUI.DYNAMIC_TEXTBOX_ERROR_MSG_BY_ID, errorMessageID);
    }

    public void enterToTextboxById(String id, String value){
        waitForElementVisible(driver, BaseElementUI.DYNAMIC_TEXTBOX_BY_ID, id);
        sendKeyToElement(driver, BaseElementUI.DYNAMIC_TEXTBOX_BY_ID, value, id);
    }

    public String getTextBoxAttributeById(String id){
        waitForElementVisible(driver, BaseElementUI.DYNAMIC_TEXTBOX_BY_ID, id);
        return getElementAttribute(driver, BaseElementUI.DYNAMIC_TEXTBOX_BY_ID, "value", id);
    }
}
