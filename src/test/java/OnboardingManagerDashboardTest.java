import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.*;
import pages.dashboard.managerDashboard.edit.BankAccountsPage;
import pages.dashboard.managerDashboard.edit.EditProfilePage;
import pages.dashboard.managerDashboard.OnboardingManagerDashboard;
import pages.dashboard.managerDashboard.edit.MyAccountPage;
import pages.dashboard.managerDashboard.finalize.EmplyeeDefaultPage;
import pages.dashboard.managerDashboard.finalize.NewEmployeePage;
import pages.dashboard.managerDashboard.homePage.Onboarding;
import pages.dashboard.managerDashboard.task.AddTaskScreen;
import pages.dashboard.managerDashboard.task.ComplianceTaskAckDialog;
import pages.dashboard.managerDashboard.task.DeleteTaskDialogBox;
import pages.dashboard.managerDashboard.task.EditTaskScreen;

import java.io.IOException;


public class OnboardingManagerDashboardTest {
    private WebDriver driver;
    private OnboardingManagerDashboard onboardingManagerDashboard;
    private  WebDriverWait wait;

    @Before
    public void setUp(){
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\shiri\\Downloads\\Softwares\\geckodriver-v0.25.0-win64\\geckodriver.exe");
        driver = new FirefoxDriver();
        onboardingManagerDashboard= new OnboardingManagerDashboard(driver);
        this.wait = new WebDriverWait(driver, 50);
    }

    @After
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void navigateToDashboard() throws InterruptedException {
        //region xpath (for waits)
        String staffTurnoverXpath = "/html/body/div[1]/div[2]/div/div[3]/div/div[2]/div/app-root/ng-component/" +
                "form/div/div[3]/div[3]/app-staffturnover/div/div[2]/div/div/div/div[2]/canvas[2]";
        String taskTableXpath = "//*[@id=\"TaskTable_wrapper\"]/div[2]/div[2]/div/ul/li[2]/a";
        //endregion

        //login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.setUsername("s.maharjan@aussiepay.com.au");
        loginPage.setPassword("password01");
        loginPage.clickOnLogin();

        //dashboard
        Onboarding onboarding = new Onboarding(driver);
        Assert.assertTrue(onboarding.pageOpened());
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(staffTurnoverXpath)));
        onboarding.clickOnOnboardingButton();
        onboarding.clickOnDashboardButton();

        //verify
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(taskTableXpath)));
        Thread.sleep(2000);
        Assert.assertTrue(onboardingManagerDashboard.pageOpened());
    }

    @Test
    public void testUploadPicture() throws InterruptedException, IOException {
        navigateToDashboard();
        //upload picture
        onboardingManagerDashboard.clickOnChangePicture();
        onboardingManagerDashboard.uploadProfilePicture();
        Thread.sleep(5000);
    }

    @Test
    public void testEditProfile() throws InterruptedException {
        //region xpath(needed during verification)
        String homePhoneXpath = "/html/body/form/div[4]/div[2]/div/div[3]/div/div[1]/div[1]/div/div[2]/div[1]/div[1]/div/div[2]/p";
        String address1Xpath = "/html/body/form/div[4]/div[2]/div/div[3]/div/div[1]/div[1]/div/div[2]/div[1]/div[5]/div/div[2]/p";
        String suburbXpath = "/html/body/form/div[4]/div[2]/div/div[3]/div/div[1]/div[1]/div/div[2]/div[1]/div[6]/div/div[2]/p";
        //endregion

        navigateToDashboard();
        //open edit profile page
        onboardingManagerDashboard.clickOnEditProfile();
        MyAccountPage myAccountPage = new MyAccountPage(driver);
        Assert.assertTrue(myAccountPage.pageOpened());
        myAccountPage.clickOnEdit();
        //verify
        EditProfilePage editProfilePage = new EditProfilePage(driver);
        Assert.assertTrue(editProfilePage.pageOpened());

        //edit profile
        editProfilePage.setHomePhone("042331251");
        editProfilePage.setAddress1("3 Street");
        editProfilePage.setSuburb("Hunters Hill");
        Thread.sleep(5000);
        editProfilePage.clickOnSaveChanges();
        Thread.sleep(5000);

        //verify
        Assert.assertEquals(driver.findElement(By.xpath(homePhoneXpath)).getText(), "042331251");
        Assert.assertEquals(driver.findElement(By.xpath(address1Xpath)).getText(), "3 Street");
        Assert.assertEquals(driver.findElement(By.xpath(suburbXpath)).getText(), "Hunters Hill");
    }

    @Test
    public void testEditPaymentMethods() throws InterruptedException {
        //region xpath (required during verification)
        String accountNameXpath = "/html/body/form/div[4]/div[2]/div/div[3]/div/div[1]/div[2]/div/div[2]/div[2]/table/tbody/tr[2]/td[5]";
        String bsbXpath = "//*[@id=\"phMain_ctl01_dgBankAccounts_lblBankBSB_0\"]";
        String accountNumberXpath = "//*[@id=\"phMain_ctl01_dgBankAccounts_lblAccountNumber_0\"]";
        //endregion

        navigateToDashboard();
        //open edit payment methods
        onboardingManagerDashboard.clickOnEditProfile();
        MyAccountPage myAccountPage = new MyAccountPage(driver);
        Assert.assertTrue(myAccountPage.pageOpened());
        myAccountPage.clickOnEditPaymentMethods();
        //verify
        BankAccountsPage bankAccountsPage = new BankAccountsPage(driver);
        Assert.assertTrue(bankAccountsPage.pageOpened());

        //add primary payment method
        bankAccountsPage.setAccountName("New Account");
        Thread.sleep(5000);
        bankAccountsPage.setBsb("062181");
        Thread.sleep(5000);
        bankAccountsPage.setAccountNumber("101111021");
        Thread.sleep(5000);
        bankAccountsPage.setAsPrimaryAccount();
        bankAccountsPage.clickOnAddPaymentMethodButton();
        Thread.sleep(5000);

        //verify
        Assert.assertEquals(driver.findElement(By.xpath(accountNameXpath)).getText(), "New Account");
        Assert.assertEquals(driver.findElement(By.xpath(bsbXpath)).getText(), "062-181");
        Assert.assertEquals(driver.findElement(By.xpath(accountNumberXpath)).getText(), "101111021");
    }

    @Test
    public void testViewOnboardingStatus() throws InterruptedException {
        String onboardingStatusTableXpath = "/html/body/div/div[2]/div/div[3]/div/div[2]/app-root/ng-component/ng-component/form/div[2]/div[1]/div/div[2]";
        navigateToDashboard();
        Assert.assertTrue(driver.findElement(By.xpath(onboardingStatusTableXpath)).isDisplayed());
    }

    @Test
    public void testViewTasks() throws InterruptedException {
        String taskTableXpath = "//*[@id=\"TaskTable\"]";
        navigateToDashboard();
        Assert.assertTrue(driver.findElement(By.xpath(taskTableXpath)).isDisplayed());
    }

    @Test
    public void testAddTask() throws InterruptedException {
        String newTask = "This is a pages.dashboard.managerDashboard.task created for testing.";
        String taskDescription = "This is a pages.dashboard.managerDashboard.task description for testing.";

        navigateToDashboard();
        //add pages.dashboard.managerDashboard.task and verify
        onboardingManagerDashboard.clickOnAddTask();
        AddTaskScreen addTaskScreen = new AddTaskScreen(driver);
        Assert.assertTrue(addTaskScreen.screenLaunched());

        //set pages.dashboard.managerDashboard.task details
        addTaskScreen.setTaskName(newTask);
        addTaskScreen.selectTaskType();
        addTaskScreen.setTaskDescription(taskDescription);

        //set schedule
        addTaskScreen.clickOnScheduling();
        addTaskScreen.setDueDate("10/10/2019");
        addTaskScreen.selectNotificationTime();
        addTaskScreen.clickOnScheduling();
        addTaskScreen.clickOnCreateTaskButton();

        //verify
        Assert.assertTrue(onboardingManagerDashboard.taskExists(newTask));
    }

    @Test
    public void testEditTask() throws Exception {
        String newTaskName = "Edited Task Name";
        String newTaskDescription = "Edited Task Description";

        //select a pages.dashboard.managerDashboard.task and click on edit
        navigateToDashboard();
        onboardingManagerDashboard.selectATask();
        onboardingManagerDashboard.clickOnEditTask();
        //verify pages.dashboard.managerDashboard.task edit screen launch
        EditTaskScreen editTaskScreen = new EditTaskScreen(driver);
        editTaskScreen.screenLaunched();

        //edit pages.dashboard.managerDashboard.task
        editTaskScreen.setTaskName(newTaskName);
        editTaskScreen.setTaskDescription(newTaskDescription);
        editTaskScreen.clickOnUpdateTaskButton();

        //verify
        Assert.assertTrue(onboardingManagerDashboard.taskExists(newTaskName));
    }

    @Test
    public void testDeleteTask() throws Exception {
        String taskToDelete = null;

        //select a task and delete
        navigateToDashboard();
        taskToDelete = onboardingManagerDashboard.selectATask();
        onboardingManagerDashboard.clickOnDeleteTask();

        //confirm deletion
        DeleteTaskDialogBox deleteTaskDialogBox = new DeleteTaskDialogBox(driver);
        deleteTaskDialogBox.clickOnCancelTaskButton();

        //verify
        Assert.assertFalse(onboardingManagerDashboard.taskExists(taskToDelete));
    }

    @Test
    public void testCompleteTask() throws Exception {
        String MARK_AS_COMPLETE_ICON_XPATH = "/html/body/div/div[2]/div/div[3]/div/div[2]/app-root/ng-component/ng-component/" +
                "form/div[1]/div[2]/task-list/div[2]/form/div[2]/div[2]/div/div/table/tbody/tr/td[6]/div/span[1]/i";
        String taskToMarkComplete = null;

        //select a task to complete and mark as complete
        navigateToDashboard();
        taskToMarkComplete = onboardingManagerDashboard.selectATask();
        Thread.sleep(5000);
        onboardingManagerDashboard.searchTask(taskToMarkComplete);
        Thread.sleep(5000);

        //click on mark as complete icon
        if(onboardingManagerDashboard.isClickable(driver.findElement(By.xpath(MARK_AS_COMPLETE_ICON_XPATH)), driver)){
            driver.findElement(By.xpath(MARK_AS_COMPLETE_ICON_XPATH)).click();
        }
        else{
            testCompleteTask();
        }

        //check if task is compliance task
        ComplianceTaskAckDialog complianceTaskAckDialog = new ComplianceTaskAckDialog(driver);
        if (complianceTaskAckDialog.dialogBoxDisplayed()){
            complianceTaskAckDialog.setFullName("Acknowledge");
            complianceTaskAckDialog.clickOnAcknowledgedButton();
        }

        //refresh page and verify
        driver.navigate().refresh();
        Assert.assertTrue(onboardingManagerDashboard.taskCompleted(taskToMarkComplete));
    }

    @Test
    public void testFinalizeEmp() throws Exception {
        //email for verification
        String email = null;

        //click on finalize
        navigateToDashboard();
        onboardingManagerDashboard.selectAnEmployeeAndClickFinalize();

        //verify finalize page is opened
        NewEmployeePage newEmployeePage = new NewEmployeePage(driver);
        Assert.assertTrue(newEmployeePage.pageOpened());

        //enter required values and finalize
        newEmployeePage.selectAllBasicWebRoles();
        newEmployeePage.setHourlyRate("50");
        newEmployeePage.setStandardHours("38");
        newEmployeePage.setCostCentre1DefaultHours("38");
        email = newEmployeePage.getEmailValue();
        newEmployeePage.clickSaveEmployeeButton();
        Thread.sleep(5000);

        //verify
        EmplyeeDefaultPage emplyeeDefaultPage = new EmplyeeDefaultPage(driver);
        Assert.assertTrue(emplyeeDefaultPage.pageOpened());
        emplyeeDefaultPage.setEmailSearchValue(email);
        emplyeeDefaultPage.clickOnSearch();
        Thread.sleep(5000);
        Assert.assertTrue(emplyeeDefaultPage.searchedEmployeeExists());

    }
}
