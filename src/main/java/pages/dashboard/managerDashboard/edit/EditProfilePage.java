package pages.dashboard.managerDashboard.edit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EditProfilePage {

    private WebDriver driver;

    //web elements
    @FindBy(xpath = "//*[@id=\"phMain_ctl00_txtHomePhone\"]")
    private WebElement homePhoneField;

    @FindBy(xpath = "//*[@id=\"phMain_ctl00_txtStreetAddress1\"]")
    private WebElement address1Field;

    @FindBy(xpath = "//*[@id=\"phMain_ctl00_txtSuburb\"]")
    private WebElement suburbField;

    @FindBy(xpath = "//*[@id=\"phMain_btnsave\"]")
    private WebElement saveChangesButton;

    @FindBy(xpath = "//*[@id=\"breadcrumbs\"]/li[3]/a")
    private WebElement editProfilePointer;

    //initialize driver and web elements
    public EditProfilePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //verify page opened
    public boolean pageOpened(){
        return editProfilePointer.getText().toString().contains("Personal Details");
    }

    //edit home phone
    public void setHomePhone(String homePhone){
        homePhoneField.clear();
        homePhoneField.sendKeys(homePhone);
    }

    //edit address1
    public void setAddress1(String address1){
        address1Field.clear();
        address1Field.sendKeys(address1);
    }

    //edit suburb
    public void setSuburb(String suburb){
        suburbField.clear();
        suburbField.sendKeys(suburb);
    }

    //save changes made
    public void clickOnSaveChanges(){
        saveChangesButton.click();
    }
}
