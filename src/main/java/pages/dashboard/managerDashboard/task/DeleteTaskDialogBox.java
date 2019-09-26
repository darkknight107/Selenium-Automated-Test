package pages.dashboard.managerDashboard.task;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DeleteTaskDialogBox {
    private WebDriver driver;

    //Web Elements
    @FindBy(xpath = "/html/body/div[1]/div[2]/div/div[3]/div/div[2]/app-root/ng-component/ng-component/form/div[1]/div[2]/task-list/div[3]/div/div/div[1]/h4")
    private WebElement cancelTaskBoxHeader;

    @FindBy(xpath = "/html/body/div[1]/div[2]/div/div[3]/div/div[2]/app-root/ng-component/ng-component/form/div[1]/div[2]/task-list/div[3]/div/div/div[3]/button[2]")
    private WebElement cancelTaskButton;

    @FindBy(xpath = "//*[@id=\"cancelTaskModal\"]")
    private WebElement closeButton;

    //initialize driver and web elements
    public DeleteTaskDialogBox(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean dailogBoxLaunched(){
        return cancelTaskBoxHeader.getText().contains("Cancel Task");
    }

    //delete task
    public void clickOnCancelTaskButton(){
        cancelTaskButton.click();
    }

    //close dialog box
    public void clickOnCloseButton(){
        closeButton.click();
    }
}
