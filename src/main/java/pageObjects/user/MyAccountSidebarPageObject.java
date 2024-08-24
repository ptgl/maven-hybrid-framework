package pageObjects.user;

import commons.BaseElement;
import commons.BasePage;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import pageUIs.user.MyAccountSidebarPageUI;

public class MyAccountSidebarPageObject extends BaseElement {
    WebDriver driver;
    public MyAccountSidebarPageObject(WebDriver driver){
        super(driver);
        this.driver = driver;
    }

    public AddressPageObject openAddressPage(){
        waitForElementClickable(driver, MyAccountSidebarPageUI.ADDRESS_LINK_TEXT);
        clickToElement(driver, MyAccountSidebarPageUI.ADDRESS_LINK_TEXT);
        return PageGeneratorManager.getAddressPage(driver);
    }

    public OrderPageObject openOrderPage(){
        waitForElementClickable(driver, MyAccountSidebarPageUI.ORDER_LINK_TEXT);
        clickToElement(driver, MyAccountSidebarPageUI.ORDER_LINK_TEXT);
        return PageGeneratorManager.getOrderPage(driver);
    }
    public RewardPointPageObject openRewardPointPage(){
        waitForElementClickable(driver, MyAccountSidebarPageUI.REWARD_POINT_LINK_TEXT);
        clickToElement(driver, MyAccountSidebarPageUI.REWARD_POINT_LINK_TEXT);
        return PageGeneratorManager.getRewardPointPage(driver);
    }
    public CustomerPageObject openCustomerPage(){
        waitForElementClickable(driver, MyAccountSidebarPageUI.CUSTOMER_LINK_TEXT);
        clickToElement(driver, MyAccountSidebarPageUI.CUSTOMER_LINK_TEXT);
        return PageGeneratorManager.getCustomerPage(driver);
    }

    public MyAccountSidebarPageObject openDynamicSideBarPage(String pageName){
        waitForElementClickable(driver, MyAccountSidebarPageUI.DYNAMIC_SIDEBAR_LINK_TEXT, pageName);
        clickToElement(driver, MyAccountSidebarPageUI.DYNAMIC_SIDEBAR_LINK_TEXT, pageName);

        switch (pageName){
            case "Addresses":
                return PageGeneratorManager.getAddressPage(driver);
            case "Customer info":
                return PageGeneratorManager.getCustomerPage(driver);
            case "Orders":
                return PageGeneratorManager.getOrderPage(driver);
            case "Reward points":
                return PageGeneratorManager.getRewardPointPage(driver);
            default:
                new RuntimeException("Page name is invalid");
                return  null;

        }
    }

    public void openDynamicSideBarPageByName(String pageName) {
        waitForElementClickable(driver, MyAccountSidebarPageUI.DYNAMIC_SIDEBAR_LINK_TEXT, pageName);
        clickToElement(driver, MyAccountSidebarPageUI.DYNAMIC_SIDEBAR_LINK_TEXT, pageName);
    }
}
