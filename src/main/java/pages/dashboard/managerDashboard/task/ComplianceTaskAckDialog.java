package pages.dashboard.managerDashboard.task;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ComplianceTaskAckDialog {
    private WebDriver driver;

    //web elements
    @FindBy(xpath = "/html/body/div[1]/div[2]/div/div[3]/div/div[2]/app-root/ng-component/ng-component/form/div[1]/div[2]/task-list/div[4]/div/form/div/div[1]/h4")
    private WebElement complianceTaskAckHeading;

    @FindBy(xpath = "//*[@id=\"aeFullname\"]")
    private WebElement fullNameField;

    @FindBy(xpath = "/html/body/div[1]/div[2]/div/div[3]/div/div[2]/app-root/ng-component/ng-component/form/div[1]/div[2]/task-list/div[4]/div/form/div/div[3]/button[2]")
    private WebElement acknowledgedButton;

    //initialize driver and web elements
    public ComplianceTaskAckDialog(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //verify dialog box displayed
    public boolean dialogBoxDisplayed(){
        return complianceTaskAckHeading.getText().contains("Compliance Task Requires Acknowledgement");
    }

    //set full name
    public void setFullName(String fullName){
        fullNameField.sendKeys(fullName);
    }

    //acknowledge
    public void clickOnAcknowledgedButton(){
        acknowledgedButton.click();
    }
}
