package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    private WebDriver driver;
    private static String PAGE_URL = "https://saasuat.aussiepay.com.au";

    //web elements
    @FindBy(xpath = "//*[@id=\"UserName\"]")
    private WebElement usernameField;

    @FindBy(xpath = "//*[@id=\"Password\"]")
    private WebElement passwordField;

    @FindBy(xpath = "//*[@id=\"Login\"]/div[3]/button")
    private WebElement loginButton;

    @FindBy(xpath = "/html/body/div[2]/div[2]/div/a")
    private WebElement forgotPasswordLink;

    //initialize driver and web elements
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        driver.get(PAGE_URL);
        PageFactory.initElements(driver, this);
    }

    //username
    public void setUsername(String username){
        usernameField.clear();
        usernameField.sendKeys(username);
    }

    //password
    public void setPassword(String password){
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    //login
    public void clickOnLogin(){
        loginButton.click();
    }

    //forgot password
    public void clickOnForgotPassword(){
        forgotPasswordLink.click();
    }
}
