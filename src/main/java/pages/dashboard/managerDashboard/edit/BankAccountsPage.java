package pages.dashboard.managerDashboard.edit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BankAccountsPage {
    private WebDriver driver;

    @FindBy(xpath = "//*[@id=\"phMain_ctl00_txtAccountName\"]")
    private WebElement accoutNameField;

    @FindBy(xpath = "//*[@id=\"ctl00_phMain_ctl00_txtBankBSB\"]")
    private WebElement bsbField;

    @FindBy(xpath = "//*[@id=\"ctl00_phMain_ctl00_txtAccountNumber\"]")
    private WebElement accountNumberField;

    @FindBy(xpath = "//*[@id=\"phMain_ctl00_cboxPrymaryAcc\"]")
    private WebElement primaryAccountCheckBox;

    @FindBy(xpath = "//*[@id=\"phMain_ctl00_btnPrimaryPayment\"]")
    private WebElement addPaymentMethodButton;

    @FindBy(xpath = "/html/body/form/div[4]/div[2]/div/div[1]/ul[1]/li[3]/a")
    private WebElement paymentMethodPagePointer;

    //initialize driver and web elements
    public BankAccountsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //verify page open
    public boolean pageOpened(){
        return paymentMethodPagePointer.getText().toString().contains("Payment Methods");
    }

    //account name
    public void setAccountName(String accountName){
        accoutNameField.clear();
        accoutNameField.sendKeys(accountName);
    }

    //bsb
    public void setBsb(String bsb){
        bsbField.clear();
        String [] bsbLetters = bsb.split("");
        for (String i : bsbLetters){
            bsbField.sendKeys(i);
        }
    }

    //checkbox
    public void setAsPrimaryAccount(){
        primaryAccountCheckBox.click();
    }

    //account number
    public void setAccountNumber(String accountNumber){
        accountNumberField.clear();
        String [] accountNumberLetters = accountNumber.split("");
        for (String i : accountNumberLetters){
            accountNumberField.sendKeys(i);
        }
    }

    //add payment method
    public void clickOnAddPaymentMethodButton(){
        addPaymentMethodButton.click();
    }
}
