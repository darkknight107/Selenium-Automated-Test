package pages.dashboard.managerDashboard.homePage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    private WebDriver driver;
    private String PAGE_TITLE = "Aussiepay - ePayroll";

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean pageOpened(){
        return driver.getTitle().equals(PAGE_TITLE);
    }
}
