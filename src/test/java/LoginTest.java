import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.dashboard.managerDashboard.homePage.HomePage;
import pages.LoginPage;
import java.util.concurrent.TimeUnit;


public class LoginTest{
    private WebDriver driver;

    @Before
    public void setUp(){
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @After
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void login(){
        //login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.setUsername("s.maharjan@aussiepay.com.au");
        loginPage.setPassword("password01");
        loginPage.clickOnLogin();

        //verify
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.pageOpened());
    }


}
