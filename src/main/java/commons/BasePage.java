package commons;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageUIs.user.BaseElementUI;

import java.io.File;
import java.time.Duration;
import java.util.List;
import java.util.Set;

public class BasePage {

    /* Used for using BasePage by getting it directly via static function*/
    public static BasePage getBasePage(){
        return new BasePage();
    }

    public void openPageUrl(WebDriver driver, String url){
        driver.get(url);
    }

    public void back(WebDriver driver){
        driver.navigate().back();
    }

    public void forward(WebDriver driver){
        driver.navigate().forward();
    }

    public void refresh(WebDriver driver){
        driver.navigate().refresh();
    }

    public String getPageTitle(WebDriver driver){
        return driver.getTitle();
    }

    public String getCurrentUrl(WebDriver driver){
        return driver.getCurrentUrl();
    }

    public String getPageSource(WebDriver driver){
        return driver.getPageSource();
    }

    public void acceptAlert(WebDriver driver){
        waitAlertPresent(driver).accept();
    }

    public void cancelAlert(WebDriver driver){
        waitAlertPresent(driver).dismiss();
    }

    public String getTextAlert(WebDriver driver){
        return waitAlertPresent(driver).getText();
    }

    public void sendKeyToAlert(WebDriver driver, String keysToSend){
        waitAlertPresent(driver).sendKeys(keysToSend);
    }

    public Alert waitAlertPresent(WebDriver driver){
        return new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.alertIsPresent());
    }

    public String getWindowHandle(WebDriver driver){
        return driver.getWindowHandle();
    }

    public void switchToWindowByTitle(WebDriver driver, String tilte){
        Set<String> windows= driver.getWindowHandles();
        for (String id : windows){
            driver.switchTo().window(id);
            if(driver.getTitle().equals(tilte)){
                driver.switchTo().window(id);
                return;
            }
        }
    }

    public void clossAllWindowsExceptParent(WebDriver driver, String parentId){
        Set<String> windows= driver.getWindowHandles();
        for (String id : windows){
            driver.switchTo().window(id);
            if(!id.equals(parentId)){
                driver.close();
            }
        }
        driver.switchTo().window(parentId);
    }

    public void sleepInSeconds(long seconds){
        try {
            Thread.sleep(seconds*1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public By getByXpath(String locator){
        return By.xpath(locator);
    }

    public String getDynamicLocator(String locator, String... restParams){
        return String.format(locator, (Object[]) restParams);
    }

    public By getByLocator(String locator){
        By by = null;
        if(locator.startsWith("xpath=") || locator.startsWith("XPATH=") || locator.startsWith("XPath=") || locator.startsWith("Xpath=")){
            by = By.xpath(locator.substring(6));
        }else if(locator.startsWith("css=") || locator.startsWith("CSS=") || locator.startsWith("Css=")){
            by = By.cssSelector(locator.substring(4));
        }else if(locator.startsWith("id=") || locator.startsWith("ID=") || locator.startsWith("Id=")){
            by = By.id(locator.substring(3));
        }else if(locator.startsWith("name=") || locator.startsWith("NAME=") || locator.startsWith("Name=")){
            by = By.name(locator.substring(5));
        }else if(locator.startsWith("class=") || locator.startsWith("CLASS=") || locator.startsWith("Class=")){
            by = By.className(locator.substring(6));
        }else if(locator.startsWith("tagname=") || locator.startsWith("TAGNAME=") || locator.startsWith("Tagname=")){
            by = By.tagName(locator.substring(8));
        }
        else{
            throw new RuntimeException("Locator type is not valid.");
        }
        return by;
    }

    public WebElement getWebElement(WebDriver driver, String locator){
        return driver.findElement(getByLocator(locator));
    }

    public List<WebElement> getWebElements(WebDriver driver, String locator){
        return driver.findElements(getByLocator(locator));
    }

    public List<WebElement> getWebElements(WebDriver driver, String locator, String... restParams){
        return driver.findElements(getByLocator(getDynamicLocator(locator, restParams)));
    }

    public void clickToElement(WebDriver driver, String locator){
        getWebElement(driver, locator).click();
    }

    public void clickToElement(WebDriver driver, WebElement element){
        element.click();
    }

    public void clickToElement(WebDriver driver, String locator, String... restParams){
        getWebElement(driver, getDynamicLocator(locator, restParams)).click();
    }

    public void sendKeyToElement(WebDriver driver, String locator, String valueToSend){
        getWebElement(driver, locator).clear();
        getWebElement(driver, locator).sendKeys(valueToSend);
    }
    public void sendKeyToElement(WebDriver driver, String locator, String valueToSend, String... restParams){
        getWebElement(driver, getDynamicLocator(locator, restParams)).clear();
        getWebElement(driver, getDynamicLocator(locator, restParams)).sendKeys(valueToSend);
    }

    public String getElementText(WebDriver driver, String locator){
        return getWebElement(driver, locator).getText();
    }
    public String getElementText(WebDriver driver, String locator, String... restParams){
        return getWebElement(driver, getDynamicLocator(locator, restParams)).getText();
    }

    public void selectItemInDefaultDropdown(WebDriver driver, String locator, String itemToSelect){
        new Select(getWebElement(driver, locator)).selectByVisibleText(itemToSelect);
    }

    public void selectItemInDefaultDropdown(WebDriver driver, String locator, String itemToSelect, String... restParams){
        new Select(getWebElement(driver, getDynamicLocator(locator, restParams))).selectByVisibleText(itemToSelect);
    }

    public List<WebElement> getSelectedItemstInDefaulDropdown(WebDriver driver, String locator){
        return new Select(getWebElement(driver, locator)).getAllSelectedOptions();
    }

    public String getFirstSelectedTextInDefaultDropdown(WebDriver driver, String locator){
        return new Select(getWebElement(driver, locator)).getFirstSelectedOption().getText();
    }

    public boolean isDropdownMultiple(WebDriver driver, String locator){
        return new Select(getWebElement(driver, locator)).isMultiple();
    }

    public void selectCustomDropdownByXpath(WebDriver driver, String parentXpath, String childTextXpath, String itemText){
        getWebElement(driver, parentXpath).click();
        new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childTextXpath)));
        getWebElement(driver, childTextXpath+"[contains(text(),'"+itemText+"')]").click();
    }

    public String getElementAttribute(WebDriver driver, String locator, String attributeName){
        return getWebElement(driver, locator).getAttribute(attributeName);
    }

    public String getElementAttribute(WebDriver driver, String locator, String attributeName, String... restParams){
        return getWebElement(driver, getDynamicLocator(locator, restParams)).getAttribute(attributeName);
    }

    public String getCssValue(WebDriver driver, String locator, String propertyName){
        return getWebElement(driver, locator).getCssValue(propertyName);
    }

    public String getHexaColorFromRGBA(String rgbaValue){
        return Color.fromString(rgbaValue).asHex().toUpperCase();
    }

    public int getListElementSize(WebDriver driver, String locator){
        return getWebElements(driver, locator).size();
    }

    public int getListElementSize(WebDriver driver, String locator, String... restParams){
        return getWebElements(driver, getDynamicLocator(locator, restParams)).size();
    }

    public boolean isElementSelected(WebDriver driver, String locator){
        return getWebElement(driver, locator).isSelected();
    }

    public boolean isElementDisplayed(WebDriver driver, String locator){
        return getWebElement(driver, locator).isDisplayed();
    }

    public boolean isElementUnDisplayed(WebDriver driver, String locator){
         setImplicitWait(driver, GlobalConstants.SHORT_TIMEOUT);
         List<WebElement> foundElementList = getWebElements(driver, locator);
         setImplicitWait(driver, GlobalConstants.LONG_TIMEOUT);

         if(foundElementList.size() == 0){
             return true; // Not displayed + not in DOM
         }else if(foundElementList.size() == 1 && !foundElementList.get(0).isDisplayed()){
             return true; // Not displayed + in DOM
         }else return false; // Displayed
    }
    public void setImplicitWait(WebDriver driver, long timeout){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));
    }
    public boolean isElementDisplayed(WebDriver driver, String locator, String... restParams){
        return getWebElement(driver, getDynamicLocator(locator, restParams)).isDisplayed();
    }

    public boolean isElementEnabled(WebDriver driver, String locator){
        return getWebElement(driver, locator).isEnabled();
    }

    public void checkTheCheckboxOrRadio(WebDriver driver, String locator){
        if(!isElementSelected(driver, locator)){
            getWebElement(driver, locator).click();
        }
    }

    public void checkTheCheckboxOrRadio(WebDriver driver, String locator, String... restParams){
        if(!isElementSelected(driver, getDynamicLocator(locator, restParams))){
            getWebElement(driver, getDynamicLocator(locator, restParams)).click();
        }
    }

    public void uncheckTheCheckbox(WebDriver driver, String locator){
        if(isElementSelected(driver, locator)){
            getWebElement(driver, locator).click();
        }
    }

    public void uncheckTheCheckbox(WebDriver driver, String locator, String... restParams){
        if(isElementSelected(driver, getDynamicLocator(locator, restParams))){
            getWebElement(driver, getDynamicLocator(locator, restParams)).click();
        }
    }

    public void switchToIframe(WebDriver driver, String locator){
        new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(getByLocator(locator)));
    }

    public void switchToDefaultContent(WebDriver driver){
        driver.switchTo().defaultContent();
    }

    public void doubleClickToElement(WebDriver driver, String locator){
        new Actions(driver).doubleClick(getWebElement(driver, locator)).perform();
    }

    public void hoverToElement(WebDriver driver, String locator){
        new Actions(driver).moveToElement(getWebElement(driver, locator)).perform();
    }

    public void rightClickToElement(WebDriver driver, String locator){
        new Actions(driver).contextClick(getWebElement(driver, locator)).perform();
    }

    public void dragAndDropElement(WebDriver driver, String sourceLocator, String targetLocator){
        new Actions(driver).dragAndDrop(getWebElement(driver, sourceLocator), getWebElement(driver, targetLocator)).perform();
    }

    public void sendKeyBoardToElement(WebDriver driver, String locator, Keys keys){
        new Actions(driver).sendKeys(getWebElement(driver, locator), keys).perform();
    }

    public void scrollToElement(WebDriver driver, String locator){
        new Actions(driver).scrollToElement(getWebElement(driver, locator)).perform();
    }

    public Object executeForBrowserByJS(WebDriver driver, String javaScript) {
        return ((JavascriptExecutor) driver).executeScript(javaScript);
    }
    public Object executeOnElementByJS(WebDriver driver, String javaScript, String locator) {
        return ((JavascriptExecutor) driver).executeScript(javaScript, getWebElement(driver, locator));
    }

    public String getInnerTextByJS(WebDriver driver) {
        return (String) ((JavascriptExecutor) driver).executeScript("return document.documentElement.innerText;");
    }

    public String getInnerTextOfElementByJS(WebDriver driver, String locator){
        return (String) ((JavascriptExecutor) driver).executeScript("return  arguments[0].innerText;", getWebElement(driver, locator));
    }

    public boolean isExpectedTextInInnerTextByJS(WebDriver driver, String textExpected) {
        String textActual = (String) ((JavascriptExecutor) driver).executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
        System.out.println("actual Text "+textActual);
        return textActual.equals(textExpected);
    }

    public void scrollToBottomPageByJS(WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void navigateToUrlByJS(WebDriver driver, String url) {
        ((JavascriptExecutor) driver).executeScript("window.location = '" + url + "'");
        sleepInSeconds(3);
    }

    public void hightlightElementByJS(WebDriver driver, String locator) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        WebElement element = getWebElement(driver, locator);
        String originalStyle = element.getAttribute("style");
        jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
        sleepInSeconds(2);
        jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
    }

    public void clickToElementByJS(WebDriver driver, String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", getWebElement(driver, locator));
        sleepInSeconds(3);
    }

    public void scrollToElementOnTopByJS(WebDriver driver, String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, locator));
    }

    public void scrollToElementOnDownByJS(WebDriver driver, String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", getWebElement(driver, locator));
    }

    public void setAttributeInDOMByJS(WebDriver driver, String locator, String attributeName, String attributeValue) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('" + attributeName + "', '" + attributeValue +"');", getWebElement(driver, locator));
    }

    public void removeAttributeInDOMByJS(WebDriver driver, String locator, String attributeRemove) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(driver, locator));
    }

    public void sendkeyToElementByJS(WebDriver driver, String locator, String value) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '" + value + "')", getWebElement(driver, locator));
    }

    public String getAttributeInDOMByJS(WebDriver driver, String locator, String attributeName) {
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].getAttribute('" + attributeName + "');", getWebElement(driver, locator));
    }

    public String getElementValidationMessageByJS(WebDriver driver, String locator) {
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].validationMessage;", getWebElement(driver, locator));
    }

    public boolean isImageLoadedByJS(WebDriver driver, String locator) {
        boolean status = (boolean) ((JavascriptExecutor) driver).executeScript(
                "return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", getWebElement(driver, locator));
        return status;
    }

    public void waitForElementVisible(WebDriver driver, String locator){
        new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locator)));
    }

    public void waitForElementVisible(WebDriver driver, String locator, String... restParams){
        new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.visibilityOfElementLocated(getByLocator(getDynamicLocator(locator, restParams))));
    }

    public void waitForListElementVisible(WebDriver driver, String locator){
        new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(locator)));
    }

    public void waitForElementInvisible(WebDriver driver, String locator){
        new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locator)));
    }
    public void waitForElementInvisible(WebDriver driver, String locator, String... restParams){
        new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(getDynamicLocator(locator, restParams))));
    }

    public void waitForListElementInvisible(WebDriver driver, String locator){
        new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.invisibilityOfAllElements(getWebElements(driver, locator)));
    }
    public void waitForListElementInvisible(WebDriver driver, String locator, String... restParams){
        new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.invisibilityOfAllElements(getWebElements(driver, getDynamicLocator(locator, restParams))));
    }

    public void waitForElementClickable(WebDriver driver, String locator){
        new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.elementToBeClickable(getByLocator(locator)));
    }

    public void waitForElementClickable(WebDriver driver, WebElement element){
        new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.elementToBeClickable(element));
    }
    public void waitForElementClickable(WebDriver driver, String locator, String... restParams){
        new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.elementToBeClickable(getByLocator(getDynamicLocator(locator, restParams))));
    }

    public void waitForElementSelected(WebDriver driver, String locator){
        new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.elementToBeSelected(getByLocator(locator)));
    }

    public void waitForElementPresent(WebDriver driver, String locator){
        new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.presenceOfElementLocated(getByLocator(locator)));
    }

    public void waitForListElementPresent(WebDriver driver, String locator){
        new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(locator)));
    }

    public boolean isPageLoadedSuccess(WebDriver driver){
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        return explicitWait.until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                return (Boolean) jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
            }
        });
    }

    public void uploadMutipleFiles(WebDriver driver, String... fileNames){
        String filePath = System.getProperty("user.dir") + File.separator + "uploadFiles" + File.separator;
        String fullFileName = "";

        for(String file : fileNames){
            fullFileName = fullFileName + filePath + file + "\n";
        }
        fullFileName = fullFileName.trim();
        sendKeyToElement(driver, BaseElementUI.UPLOAD_FILE_TYPE, fullFileName);
    }

    public void setCookies(WebDriver driver, Set<Cookie> cookies){
        for(Cookie c : cookies){
            driver.manage().addCookie(c);
        }
    }

    public void deleteAllCookie(WebDriver driver){
        driver.manage().deleteAllCookies();
    }

    public Set<Cookie> getCookies(WebDriver driver){
        return driver.manage().getCookies();
    }

    private long longTimeout = GlobalConstants.LONG_TIMEOUT;



}
