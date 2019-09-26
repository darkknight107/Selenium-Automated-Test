package pages.dashboard.managerDashboard.task;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class AddTaskScreen {
    private WebDriver driver;

    //region Web Elements
    @FindBy(xpath = "//*[@id=\"TaskListModalLabel\"]")
    private WebElement createNewTaskHeader;

    @FindBy(xpath = "//*[@id=\"aeTaskName\"]")
    private WebElement taskNameField;

    @FindBy(xpath = "//*[@id=\"aeTaskType\"]")
    private WebElement taskTypeDropDown;

    @FindBy(xpath = "//*[@id=\"aeTaskDescription\"]")
    private WebElement descriptionField;

    @FindBy(xpath = "/html/body/div[1]/div[2]/div/div[3]/div/div[2]/app-root/ng-component/ng-component/form/div[1]/div[2]/pages.dashboard.managerDashboard.task-list/div[1]/div/form/div/div[2]/div/ul/li[2]/a")
    private WebElement schedulingButton;

    @FindBy(xpath = "/html/body/div[1]/div[2]/div/div[3]/div/div[2]/app-root/ng-component/ng-component/form/div[1]/div[2]/pages.dashboard.managerDashboard.task-list/div[1]/div/form/div/div[2]/div/div/div[2]/div/div[2]/div/input")
    private WebElement dueDateField;

    @FindBy(xpath = "//*[@id=\"aeTaskRemindTime\"]")
    private WebElement notificationTimeDropDown;

    @FindBy(xpath = "/html/body/div[1]/div[2]/div/div[3]/div/div[2]/app-root/ng-component/ng-component/form/div[1]/div[2]/pages.dashboard.managerDashboard.task-list/div[1]/div/form/div/div[3]/button[2]")
    private WebElement createTaskButton;
    //endregion

    //initialize driver and web elements
    public AddTaskScreen(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //verify screen launched
    public boolean screenLaunched(){
        return createNewTaskHeader.getText().toString().contains("Create a New Task");
    }

    //pages.dashboard.managerDashboard.task name
    public void setTaskName(String taskName){
        taskNameField.clear();
        taskNameField.sendKeys(taskName);
    }

    //pages.dashboard.managerDashboard.task type
    public void selectTaskType(){
        Select tasktypes = new Select(taskTypeDropDown);
        List<WebElement> taskTypeList = tasktypes.getOptions();
        int listLength = taskTypeList.size();
        tasktypes.selectByIndex(ThreadLocalRandom.current().nextInt(1, listLength));
    }

    //pages.dashboard.managerDashboard.task description
    public void setTaskDescription(String description){
        descriptionField.clear();
        descriptionField.sendKeys(description);
    }

    //schedule
    public void clickOnScheduling(){
        schedulingButton.click();
    }

    //due date
    public void setDueDate(String dueDate){
        dueDateField.clear();
        dueDateField.sendKeys(dueDate);
    }

    //notification
    public void selectNotificationTime(){
        Select notificationTimes = new Select(notificationTimeDropDown);
        List<WebElement> notificationTimeList = notificationTimes.getOptions();
        int listLength = notificationTimeList.size();
        notificationTimes.selectByIndex(ThreadLocalRandom.current().nextInt(1, listLength));
    }

    //create pages.dashboard.managerDashboard.task
    public void clickOnCreateTaskButton(){
        createTaskButton.click();
    }


}
