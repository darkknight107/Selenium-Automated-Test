package pages.dashboard.managerDashboard.finalize;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EmplyeeDefaultPage {

    private WebDriver driver;

    @FindBy(xpath = "/html/body/form/div[4]/div[2]/div/div[3]/div/div[2]/div/div[3]/div[1]/h4")
    private WebElement employeeSearchPagePointer;

    @FindBy(xpath = "//*[@id=\"phMain_employeeSearch_txtEmail\"]")
    private WebElement searchByEmailField;

    @FindBy(xpath = "//*[@id=\"phMain_employeeSearch_butsearch\"]")
    private WebElement searchButton;

    @FindBy(xpath = "/html/body/form/div[4]/div[2]/div/div[3]/div/div[2]/div/table/tbody/tr[2]")
    private WebElement searchResultRow;

    public EmplyeeDefaultPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean pageOpened(){
        WebDriverWait wait = new WebDriverWait(driver, 50);
        wait.until(ExpectedConditions.elementToBeClickable(searchByEmailField));
        return employeeSearchPagePointer.getText().contains("Search Employees");
    }

    public void setEmailSearchValue(String email){
        searchByEmailField.clear();
        searchByEmailField.sendKeys(email);
    }

    public void clickOnSearch(){
        searchButton.click();
    }

    public boolean searchedEmployeeExists(){
        return searchResultRow.isDisplayed();
    }


}
