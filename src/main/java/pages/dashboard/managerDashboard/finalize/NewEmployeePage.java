package pages.dashboard.managerDashboard.finalize;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NewEmployeePage {

    private WebDriver driver;

    //web elements
    @FindBy(xpath = "/html/body/form/div[4]/div[2]/div/div[1]/ul[1]/li[2]/a")
    private WebElement newEmployeePagePointer;

    //region Web Roles
    @FindBy(xpath = "//*[@id=\"phMain_rptWebRoles_chkRole_4\"]")
    private WebElement employeeSelfServiceUserCheckBox;

    @FindBy(xpath = "//*[@id=\"phMain_rptWebRoles_chkRole_9\"]")
    private WebElement expenseUserCheckBox;

    @FindBy(xpath = "//*[@id=\"phMain_rptWebRoles_chkRole_19\"]")
    private WebElement leaveUserCheckBox;

    @FindBy(xpath = "//*[@id=\"phMain_rptWebRoles_chkRole_35\"]")
    private WebElement synchronizeUserCheckbox;
    //endregion

    @FindBy(xpath = "//*[@id=\"phMain_txtEmail\"]")
    private WebElement emailField;

    @FindBy(xpath = "//*[@id=\"ctl00_phMain_TxtHourlyRate\"]")
    private WebElement hourlyRateField;

    @FindBy(xpath = "//*[@id=\"ctl00_phMain_TxtstdHours\"]")
    private WebElement standardHoursField;

    @FindBy(xpath = "//*[@id=\"ctl00_phMain_txtCostCtrDefaultHr1\"]")
    private WebElement costCentre1DefaultHoursField;

    @FindBy(xpath = "//*[@id=\"phMain_Btnsave\"]")
    private WebElement saveEmployeeButton;

    public NewEmployeePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean pageOpened(){
        return newEmployeePagePointer.getText().contains("Employees");
    }

    public void selectAllBasicWebRoles(){
        employeeSelfServiceUserCheckBox.click();
        expenseUserCheckBox.click();
        leaveUserCheckBox.click();
        synchronizeUserCheckbox.click();
    }

    public void setHourlyRate(String hourlyRate){
        hourlyRateField.clear();
        hourlyRateField.sendKeys(hourlyRate);
    }

    public void setStandardHours(String standardHours){
        standardHoursField.clear();
        standardHoursField.sendKeys(standardHours);
    }

    public void setCostCentre1DefaultHours(String costCentre1DefaultHours){
        costCentre1DefaultHoursField.clear();
        costCentre1DefaultHoursField.sendKeys(costCentre1DefaultHours);
    }

    public void clickSaveEmployeeButton(){
        saveEmployeeButton.click();
    }

    public String getEmailValue(){
        return emailField.getText();
    }
}
