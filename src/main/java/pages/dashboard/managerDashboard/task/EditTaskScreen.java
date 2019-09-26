package pages.dashboard.managerDashboard.task;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EditTaskScreen {
    private WebDriver driver;

    //web elements
    @FindBy(xpath = "//*[@id=\"TaskListModalLabel\"]")
    private WebElement editTaskScreenHeader;

    @FindBy(xpath = "//*[@id=\"aeTaskName\"]")
    private WebElement taskNameField;

    @FindBy(xpath = "//*[@id=\"aeTaskDescription\"]")
    private WebElement taskDescriptionField;

    @FindBy(xpath = "/html/body/div[1]/div[2]/div/div[3]/div/div[2]/app-root/ng-component/ng-component/form/div[1]/div[2]/pages.dashboard.managerDashboard.task-list/div[1]/div/form/div/div[3]/button[2]")
    private WebElement updateTaskButton;

    //initialize driver and web elements
    public EditTaskScreen(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //verify screen launch
    public boolean screenLaunched(){
        return editTaskScreenHeader.getText().contains("Edit Task");
    }

    //edit pages.dashboard.managerDashboard.task name
    public void setTaskName(String taskName){
        taskNameField.clear();
        taskNameField.sendKeys(taskName);
    }

    //edit pages.dashboard.managerDashboard.task description
    public void setTaskDescription(String taskDescription){
        taskDescriptionField.clear();
        taskDescriptionField.sendKeys(taskDescription);
    }

    //update pages.dashboard.managerDashboard.task
    public void clickOnUpdateTaskButton(){
        updateTaskButton.click();
    }

}
