package pageUIs.orangeHRM;

public class CommonElementUI {
    public static final String HEADER_TITLE = "css=div.oxd-topbar-header-title span h6";
    public static final String ACTIVE_SIDEBAR_MENU_ITEM_TEXT = "css=li a.oxd-main-menu-item.active span";
    public static final String SIDEBAR_MENU_ITEM_LINK = "xpath=//li//a[@class='oxd-main-menu-item'][./span[text()='%s']]";
    public static final String TOPBAR_ITEM_LINK = "xpath=//a[@class='oxd-topbar-body-nav-tab-item'][text()='%s']";
    public static final String ACTIVE_TOPBAR_ITEM_LINK_TEXT = "css=li.oxd-topbar-body-nav-tab.--visited a";
    public static final String LOADING_SPINNER = "css=div.oxd-loading-spinner";
    public static final String EMPLOYEE_SIDEBAR_MENU_ITEM_LINK = "xpath=//a[contains(@class,'orangehrm-tabs-item')][text()='%s']";
}
