package pages.dashboard.managerDashboard;

import org.checkerframework.checker.formatter.FormatUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class OnboardingManagerDashboard {
    private WebDriver driver;

    //region Profile Elements
    //web elements in the Onboarding Manager Dashboard page
    @FindBy(xpath = "//*[@id=\"content\"]/div/div[3]/div/div[2]/app-root/ng-component/ng-component/form/div[1]/div[1]/div/div/div[3]/div/button")
    private WebElement editProfileButton;

    @FindBy(xpath = "//*[@id=\"content\"]/div/div[3]/div/div[2]/app-root/ng-component/ng-component/form/div[1]/div[1]/div/div/div[2]/div/div/label")
    private WebElement changePictureButton;

    @FindBy(xpath = "//*[@id=\"content\"]/div/div[3]/div/div[2]/app-root/ng-component/ng-component/form/div[1]/div[1]/div/div/div[1]/div[1]/div/img")
    private WebElement uploadPicture;
    //endregion

    //region My Task List Widget Elements
    @FindBy(xpath = "/html/body/div/div[2]/div/div[3]/div/div[2]/app-root/ng-component/ng-component/form/div[1]/div[2]/task-list/div[2]/form/div[1]/div/div/span[2]/i")
    private WebElement addTaskButton;

    @FindBy(xpath = "//*[@id=\"openEditModal\"]")
    private WebElement editTaskButton;

    @FindBy(xpath = "//*[@id=\"openDeleteConfirm\"]")
    private WebElement deleteTaskButton;

    @FindBy(xpath = "/html/body/div/div[2]/div/div[3]/div/div[2]/app-root/ng-component/ng-component/form/div[1]/div[2]/task-list/div[2]/form/div[2]/div[2]/div/div/div[1]/div[1]/div/label/div/input")
    private WebElement searchTaskField;
    //endregion

    //initialize driver and web elements
    public OnboardingManagerDashboard(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //verify page opened
    public boolean pageOpened(){
        return editProfileButton.getText().toString().contains("Edit Profile");
    }

    //launch my account page
    public void clickOnEditProfile(){
        editProfileButton.click();
    }

    //upload photo button click
    public void clickOnChangePicture(){
        changePictureButton.click();
    }

    //run auto it script in the resources folder to upload a picture
    public void uploadProfilePicture() throws IOException {
        Runtime.getRuntime().exec("C:\\Users\\shirish.maharjan\\IdeaProjects\\onboarding_uat\\src\\main\\resources\\upload_picture.exe");
    }

    //region Methods related to tasks
    //add task
    public void clickOnAddTask(){
        addTaskButton.click();
    }

    //edit task
    public void clickOnEditTask() throws InterruptedException {
        editTaskButton.click();
        Thread.sleep(2000);
    }

    //delete task
    public void clickOnDeleteTask() throws InterruptedException {
        deleteTaskButton.click();
        Thread.sleep(2000);
    }

    //search task
    public void searchTask(String task){
        searchTaskField.sendKeys(task);
    }

    //select a random task from list
    public String selectATask() throws Exception {
        //task name and task assigned to
        int randomTaskTableRow = getRandomTaskTableRow();
        String selectedTaskNameXpath = "/html/body/div/div[2]/div/div[3]/div/div[2]/app-root/ng-component/ng-component/form/div[1]/div[2]/task-list/" +
                "div[2]/form/div[2]/div[2]/div/div/table/tbody/tr[" + randomTaskTableRow + "]/td[2]/label";
        String selectedTaskAssignedToXpath = "/html/body/div/div[2]/div/div[3]/div/div[2]/app-root/ng-component/ng-component/form/div[1]/div[2]/" +
                "task-list/div[2]/form/div[2]/div[2]/div/div/table/tbody/tr[" + randomTaskTableRow + "]/td[4]";
        String specificTaskAssignedTo = driver.findElement(By.xpath(selectedTaskNameXpath)).getText() + " " +
                driver.findElement(By.xpath(selectedTaskAssignedToXpath)).getText();

        //click on the task
        driver.findElement(By.xpath(selectedTaskNameXpath)).click();
        //return task assigned to a specific emp
        return specificTaskAssignedTo;
    }

    public int getRandomTaskTableRow() throws Exception {
        int randomRow = 0;
        int taskListLength = 0;
        String TASK_TABLE_XPATH = "/html/body/div/div[2]/div/div[3]/div/div[2]/app-root/ng-component/ng-component/form/div[1]/div[2]/" +
                "task-list/div[2]/form/div[2]/div[2]/div/div/table/tbody/tr";
        List<WebElement> taskList = driver.findElements(By.xpath(TASK_TABLE_XPATH));

        //select a random row
        taskListLength = taskList.size();
        randomRow = ThreadLocalRandom.current().nextInt(1, taskListLength);

        if (taskListLength == 0){
            throw new Exception("No Task Exist!");
        }
        return randomRow;
    }

    //verify if task exists
    public boolean taskExists(String taskName) throws InterruptedException {
        String searchedTaskNameXpath = "/html/body/div/div[2]/div/div[3]/div/div[2]/app-root/ng-component/ng-component/form/div[1]/div[2]/task-list/div[2]/" +
                "form/div[2]/div[2]/div/div/table/tbody/tr[1]/td[2]/label";

        Thread.sleep(1000);//search for the task
        searchTask(taskName);
        return driver.findElement(By.xpath(searchedTaskNameXpath)).getText().contains(taskName);
    }

    public boolean taskCompleted(String taskName) throws InterruptedException {
        String taskDueStatusXpath = "/html/body/div/div[2]/div/div[3]/div/div[2]/app-root/ng-component/ng-component/form/div[1]/div[2]" +
                "/task-list/div[2]/form/div[2]/div[2]/div/div/table/tbody/tr[1]/td[5]/span";
        WebDriverWait wait = new WebDriverWait(driver, 40);
        wait.until(ExpectedConditions.elementToBeClickable(searchTaskField));
        searchTask(taskName);
        Thread.sleep(5000);
        return driver.findElement(By.xpath(taskDueStatusXpath)).getText().contains("Complete");
    }
    //endregion

    public static boolean isClickable(WebElement element, WebDriver driver){
        try {
            WebDriverWait wait = new WebDriverWait(driver, 5);
            wait.until(ExpectedConditions.elementToBeClickable(element));
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    public void selectAnEmployeeAndClickFinalize() throws Exception {
            String onboardingStatusRowsXpath = "/html/body/div/div[2]/div/div[3]/div/div[2]/app-root/ng-component/ng-component/form/div[2]/div[1]/div/div[2]/div/div/div[4]/div";
            List<WebElement> rows = driver.findElements(By.xpath(onboardingStatusRowsXpath));
            int numberOfRows = rows.size();

            //check which employee can be finalized
            for (int i=1; i <= numberOfRows; i++){
                String buttonXpath =  "/html/body/div/div[2]/div/div[3]/div/div[2]/" +
                        "app-root/ng-component/ng-component/form/div[2]/div[1]/div/div[2]/div" +
                        "/div/div[4]/div[" + i + "]/div[5]/button";
                if(driver.findElement(By.xpath(buttonXpath)).getText().equals("Finalize")){
                    driver.findElement(By.xpath(buttonXpath)).click();
                    Thread.sleep(10000);
                    return;
                }
            }
            throw new Exception("No employee with status 'Processing' exists to finalize.");
    }
}
