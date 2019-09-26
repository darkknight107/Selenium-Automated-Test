package pages.dashboard.managerDashboard.edit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyAccountPage {

    private WebDriver driver;

    //web elements
    @FindBy(xpath = "//*[@id=\"breadcrumbs\"]/li[3]/a")
    private WebElement myAccountPointer;

    @FindBy(xpath = "//*[@id=\"phMain_empView_ctl00_butEdit\"]")
    private WebElement editPersonalDetailsButton;

    @FindBy(xpath = "//*[@id=\"phMain_paymentMethodView_ctl00_butPaymentMethod\"]")
    private WebElement editPaymentMethodsButton;

    //initialize driver and web elements
    public MyAccountPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //verify page opened
    public boolean pageOpened(){
        return myAccountPointer.getText().toString().contains("My Account Home");
    }

    //launch personal details page
    public void clickOnEdit(){
        editPersonalDetailsButton.click();
    }

    //launch payment methods page
    public void clickOnEditPaymentMethods(){
        editPaymentMethodsButton.click();
    }

}
