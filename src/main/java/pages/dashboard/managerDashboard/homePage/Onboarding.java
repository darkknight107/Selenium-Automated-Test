package pages.dashboard.managerDashboard.homePage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Onboarding extends HomePage {
    private WebDriver driver;
    //web elements in Onboarding section of the home page
    @FindBy(xpath = "//*[@id=\"nav\"]/li[3]/a/p")
    private WebElement onboardingButton;

    @FindBy(xpath = "//*[@id=\"nav\"]/li[3]/ul/li[1]/a")
    private WebElement dashboardButton;

    @FindBy(xpath = "//*[@id=\"nav\"]/li[3]/ul/li[2]/a")
    private WebElement setupButton;

    @FindBy(xpath = "//*[@id=\"nav\"]/li[3]/ul/li[2]/ul/li[1]/a")
    private WebElement onboardingWizardButton;

    @FindBy(xpath = "//*[@id=\"nav\"]/li[3]/ul/li[2]/ul/li[2]/a")
    private WebElement onboardingEmployeesButton;

    public Onboarding(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickOnOnboardingButton(){
        onboardingButton.click();
    }

    public void clickOnDashboardButton(){
       dashboardButton.click();
    }

    public void clickOnSetupButton(){
        setupButton.click();
    }

    public void clickOnOnboardingWizard(){
        onboardingWizardButton.click();
    }

    public void clickOnOnboardingEmployees(){
        onboardingEmployeesButton.click();
    }
}
