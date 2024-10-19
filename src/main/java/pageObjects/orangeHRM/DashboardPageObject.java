package pageObjects.orangeHRM;

import org.openqa.selenium.WebDriver;

public class DashboardPageObject extends CommonPageObject{
    private WebDriver driver;
    public DashboardPageObject(WebDriver driver){
        super(driver);
        this.driver = driver;
    }
}
