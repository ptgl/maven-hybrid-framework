package pageObjects.orangeHRM;

import org.openqa.selenium.WebDriver;
import pageUIs.orangeHRM.AddEmployeePageUI;
import pageUIs.orangeHRM.ContactDetailsPageUI;

public class ContactDetailsPageObject extends CommonElement{
    private WebDriver driver;
    public ContactDetailsPageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void enterStreet1(String streetName) {
        waitForElementVisible(driver, ContactDetailsPageUI.CONTACT_DETAILS_TEXTBOX, "Street 1");
        sendKeyToElement(driver, ContactDetailsPageUI.CONTACT_DETAILS_TEXTBOX, streetName, "Street 1");
    }

    public void enterStreet2(String streetName) {
        waitForElementVisible(driver, ContactDetailsPageUI.CONTACT_DETAILS_TEXTBOX, "Street 2");
        sendKeyToElement(driver, ContactDetailsPageUI.CONTACT_DETAILS_TEXTBOX, streetName, "Street 2");
    }

    public void enterCity(String city) {
        waitForElementVisible(driver, ContactDetailsPageUI.CONTACT_DETAILS_TEXTBOX, "City");
        sendKeyToElement(driver, ContactDetailsPageUI.CONTACT_DETAILS_TEXTBOX, city,"City");
    }

    public void enterWorkEmail(String email) {
        waitForElementVisible(driver, ContactDetailsPageUI.CONTACT_DETAILS_TEXTBOX,  "Work Email");
        sendKeyToElement(driver, ContactDetailsPageUI.CONTACT_DETAILS_TEXTBOX, email,"Work Email");
    }

    public void enterOtherEmail(String email) {
        waitForElementVisible(driver, ContactDetailsPageUI.CONTACT_DETAILS_TEXTBOX,  "Other Email");
        sendKeyToElement(driver, ContactDetailsPageUI.CONTACT_DETAILS_TEXTBOX, email,"Other Email");
    }

    public void enterState(String state) {
        waitForElementVisible(driver, ContactDetailsPageUI.CONTACT_DETAILS_TEXTBOX, "State/Province");
        sendKeyToElement(driver, ContactDetailsPageUI.CONTACT_DETAILS_TEXTBOX, state,"State/Province");
    }

    public void selectCountry(String country) {
        waitForElementVisible(driver, ContactDetailsPageUI.COUNTRY_DROPDOWN);
        selectCustomDropdownByXpath(driver, ContactDetailsPageUI.COUNTRY_DROPDOWN, ContactDetailsPageUI.COUNTRY_OPTION_ITEM, country);
    }



    public void enterZip(String zip) {
        waitForElementVisible(driver, ContactDetailsPageUI.CONTACT_DETAILS_TEXTBOX, "Zip/Postal Code");
        sendKeyToElement(driver, ContactDetailsPageUI.CONTACT_DETAILS_TEXTBOX, zip,"Zip/Postal Code");
    }

    public void enterHomePhone(String phone) {
        waitForElementVisible(driver, ContactDetailsPageUI.CONTACT_DETAILS_TEXTBOX, "Home");
        sendKeyToElement(driver, ContactDetailsPageUI.CONTACT_DETAILS_TEXTBOX, phone, "Home");
    }

    public void enterMobile(String phone) {
        waitForElementVisible(driver, ContactDetailsPageUI.CONTACT_DETAILS_TEXTBOX, "Mobile");
        sendKeyToElement(driver, ContactDetailsPageUI.CONTACT_DETAILS_TEXTBOX, phone, "Mobile");
    }

    public void enterWorkPhone(String phone) {
        waitForElementVisible(driver, ContactDetailsPageUI.CONTACT_DETAILS_TEXTBOX, "Work");
        sendKeyToElement(driver, ContactDetailsPageUI.CONTACT_DETAILS_TEXTBOX, phone,"Work");
    }

}
