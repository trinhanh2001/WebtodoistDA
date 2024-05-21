package com.trinhthianh.pages;

import com.trinhthianh.helpers.PropertiesHelper;
import com.trinhthianh.keywords.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class InboxPage extends CommonPage{
    private By menuToday = By.xpath("//li[@id='filter_today']");
    private By titleTodayPage = By.xpath("//h1[normalize-space()='Today']");
    private By buttonSearch = By.xpath("//ul[@id='top-menu']//span[normalize-space()='Search']");
    private By inputSearch = By.xpath("//input[@aria-label='Search or type a command…']");
    private By searchFirstResultRow = By.xpath("(//li[@class='task_list_item'])[1]");
    private By searchTaskNameResult = By.xpath("(//li[@class='task_list_item']//div[@class='task_content'])[1]");
    private By buttonAddTask = By.xpath("//button[contains(text(),'Add task')]");
    private By inputTaskName = By.xpath("//div[@aria-label='Task name']");
    private By inputTaskDescription = By.xpath("//div[@aria-label='Description']");
    private By buttonSetDate = By.xpath("//span[@class='date date_today']");
    private By buttonPriority = By.xpath("//div[@aria-label='Set priority']");
    private By dropdownSelectProject = By.xpath("//button[@aria-label='Select a project']");
    private By optionInbox = By.xpath("//div[contains(text(),'Inbox')]");
    private By buttonSubmitAddTask= By.xpath("//button[@data-testid='task-editor-submit-button']");
    private By dateNextWeekend = By.xpath("//div[normalize-space()='Next weekend']");
    private By buttonDateTime = By.xpath("//button[@aria-haspopup='dialog']");
    private By inputTime = By.xpath("//input[@id='start-time-field']");
    private By dropdowFloatingTime = By.xpath("//div[@class='VXQljRJ']");
    private By optionBangKok = By.xpath("//div[@id='user-timezone-label']");
    private By optionFloatingTime = By.xpath("//div[@id='floating-time-label']");
    private By buttonSave = By.xpath("//div[@class='CEoA_IR']//button[@type='submit']");
    private By optionPriority1 = By.xpath("//span[normalize-space()='Priority 1']");
    private By optionPriority2 = By.xpath("//span[normalize-space()='Priority 2']");
    private By optionPriority3 = By.xpath("//span[normalize-space()='Priority 3']");
    private By optionPriority4 = By.xpath("//span[normalize-space()='Priority 4']");
    private By buttonMarkCompleteTask = By.xpath("(//button[@aria-label='Mark task as complete'])[1]");
    private By panelTaskName = By.xpath("//div[@class='task_content task-overview-content-large']");
    private By panelTaskDescription = By.xpath("//div[@aria-label='Task description']//div[@class='task_content']//p");
    //div[@aria-label='Task description']//div[@class='task_content']//p
    private By buttonSaveEditTaskName = By.xpath("//button[@data-testid='task-editor-submit-button']");
    private By panelButtonSelectProject = By.xpath("//button[@aria-label='Select a project']");
    private By panelInputSearchProject = By.xpath("//input[@placeholder='Type a project name']");

    private By panelDateTime = By.xpath("//div[@aria-label='Due date']//div[@class='DHmDvjK']");
    private By panelPickTime = By.xpath("//div[@class='scheduler_popper popper']//button[@aria-haspopup='dialog']");
    private By panelNextWeek = By.xpath("//div[normalize-space()='Next weekend']");
    private By panelPriority = By.xpath("//div[@aria-label='Priority']//span");
    private By buttonAddSubTask = By.xpath("(//div[@data-testid='task-main-content-container']//button)[2]");
    private By inputSubTaskName = By.xpath("(//div[@aria-label='Task name'])[2]");
    private By inputSubTaskDescription= By.xpath("//div[@aria-label='Description']");
    private By buttonSubDueDate = By.xpath("//div[@aria-label='Set due date']");
    private By buttonSubmiAddSubTask = By.xpath("(//button[@data-testid='task-editor-submit-button'])[2]");
    private By buttonComment = By.xpath("//button[@aria-label='Open comment editor']");
    private By inputComment = By.xpath("//div[@aria-label='Comment']");
    private By buttonSaveComment = By.xpath("//button[@data-track='comments|add_comment']");

    private By buttonCloseTask = By.xpath("//button[@aria-label='Close task']");
    private By notiAddSuccessful = By.xpath("//div[@aria-live='polite']");
    private By notiCompleteTask = By.xpath("//div[@aria-live='polite']");
    private By notiVerifyEmail = By.xpath("//div[@role='alert']");
    private By buttonCloseNotiVerifyEmail = By.xpath("//button[@aria-label='Close']");
    private By inputSearchProjectName = By.xpath("//input[@placeholder='Type a project name']");
    private By inputDateTime = By.xpath("//input[@placeholder='Type a due date']");
    private By buttonMoreActionTask = By.xpath("//button[@aria-label='More task actions']");
    private By buttonDeleteTask = By.xpath("//div[contains(text(),'Delete')]");
    private By buttonConfirmDeleteTask = By.xpath("//button[@type='submit']");
    private By messageSearchNoResult = By.xpath("//div[contains(text(),'No completed tasks matching for')]");
    private By buttonCheckCompleteTask = By.xpath("//button[@aria-label='Mark task as incomplete']");
    private By previewSchedule = By.xpath("//div[@class='scheduler-preview-date']");





    public void openMyProjectsPage() {
        WebUI.openURL(PropertiesHelper.getValue("url_home"));
        WebUI.waitForPageLoaded();
        WebUI.clickElement(menuToday);
        WebUI.verifyAssertTrueIsDisplayed(titleTodayPage, "Today page is NOT displayed");
    }

    public void addNewTask(String TASK_NAME, String TASK_DESCRIPTION,String PROJECT_NAME, String TIME) {

        WebUI.clickElement(menuToday);
        WebUI.sleep(1);
        WebUI.clickElement(buttonAddTask);
        WebUI.sleep(1);
        WebUI.verifyElementDisable(buttonSubmitAddTask,"Fail.Button Submit Add Task is enable");
        WebUI.clickElement(inputTaskName);
        WebUI.setText(inputTaskName,TASK_NAME);
        WebUI.sleep(1);
        WebUI.clickElement(inputTaskDescription);
        WebUI.setText(inputTaskDescription,TASK_DESCRIPTION);
        WebUI.sleep(1);
        WebUI.clickElement(buttonSetDate);
        WebUI.sleep(1);
        WebUI.waitForElementClickable(buttonDateTime);
        WebUI.clickElement(buttonDateTime);
        WebUI.sleep(2);
        WebUI.clickElement(dropdowFloatingTime);
        WebUI.sleep(2);
        WebUI.clickElement(optionBangKok);
        WebUI.sleep(2);
        WebUI.hoverElementWithHightLight(buttonSave);
        WebUI.clickElementByJavascriptExecutor(buttonSave);
        WebUI.sleep(1);
        WebUI.clickElement(inputDateTime);
        WebUI.setTextAndClear(inputDateTime,TIME);
        WebUI.sleep(2);
        WebUI.clickElement(previewSchedule);
        WebUI.sleep(2);
        WebUI.clickElement(buttonPriority);
        WebUI.sleep(1);
        WebUI.clickElement(optionPriority2);
        WebUI.sleep(1);
        WebUI.clickElement(dropdownSelectProject);
        WebUI.sleep(1);
        WebUI.clickElement(inputSearchProjectName);
        WebUI.setTextEnter(inputSearchProjectName,PROJECT_NAME);
        WebUI.sleep(1);
        WebUI.clickElement(buttonSubmitAddTask);
        WebUI.sleep(1);
        WebUI.verifyAssertTrueIsDisplayed(notiAddSuccessful,"Fail. Don't display notification Add Task successful");
        WebUI.sleep(2);

    }

    public void searchTask(String TASK_NAME) {
        WebUI.clickElement(buttonSearch);
        WebUI.sleep(1);
        WebUI.clickElement(inputSearch);
        WebUI.setTextEnter(inputSearch,TASK_NAME);
        WebUI.sleep(2);
        WebUI.waitForElementVisible(searchFirstResultRow);
        WebUI.verifyAssertTrueEqual(searchTaskNameResult,TASK_NAME,"Fail. Task name tìm được khác với kết quả");
        WebUI.sleep(1);
    }

    public void verifyTaskDetail(String TASK_NAME,String TASK_DESCRIPTION,String PROJECT_NAME, String TIME){
        WebUI.clickElement(searchTaskNameResult);
        WebUI.sleep(1);
       // WebUI.hoverElementWithHightLight(panelTaskName);
        WebUI.verifyAssertTrueEqual(panelTaskName,TASK_NAME,"Fail. Task name khác với lúc tạo");
        WebUI.verifyAssertTrueEqual(panelTaskDescription,TASK_DESCRIPTION,"Fail. Task description khác với lúc tạo");
        WebUI.verifyAssertTrueEqual(panelButtonSelectProject,PROJECT_NAME,"Fail. Project select khác với lúc tạo");
        WebUI.verifyAssertTrueEqual(panelDateTime,TIME,"Fail. Time khác với lúc tạo");
        WebUI.verifyAssertTrueEqual(panelPriority,"P2","Fail. Priority khác với lúc tạo");
        WebUI.sleep(2);
        WebUI.clickElement(buttonCloseTask);
    }

    public void editTask(String UPDATE_TASK_NAME,String UPDATE_TASK_DESCRIPTION,String PROJECT_NAME,String UPDATE_TIME) {
        WebUI.clickElement(searchTaskNameResult);
        WebUI.sleep(1);
        WebUI.clickElement(panelTaskName);
        WebUI.clickElement(inputTaskName);
        WebUI.setTextAndClear(inputTaskName, UPDATE_TASK_NAME);
        WebUI.sleep(1);
        WebUI.clickElement(inputTaskDescription);
        WebUI.setTextAndClear(inputTaskDescription, UPDATE_TASK_DESCRIPTION);
        WebUI.sleep(1);
        WebUI.clickElement(buttonSaveEditTaskName);
        WebUI.clickElement(panelButtonSelectProject);
        WebUI.clickElement(panelInputSearchProject);
        WebUI.setTextEnter(panelInputSearchProject,PROJECT_NAME);
        WebUI.sleep(1);
        WebUI.clickElement(panelDateTime);
        WebUI.sleep(1);
        WebUI.clickElement(panelPickTime);
        WebUI.sleep(1);
        WebUI.clickElement(dropdowFloatingTime);
        WebUI.sleep(2);
        WebUI.clickElement(optionFloatingTime);
        WebUI.sleep(2);
        WebUI.hoverElementWithHightLight(buttonSave);
        WebUI.clickElementByJavascriptExecutor(buttonSave);
        WebUI.sleep(1);
        WebUI.clickElement(inputDateTime);
        WebUI.setTextAndClear(inputDateTime,UPDATE_TIME);
        WebUI.sleep(2);
        WebUI.clickElement(previewSchedule);
        WebUI.sleep(2);
        WebUI.clickElement(buttonAddSubTask);
        WebUI.clickElement(inputSubTaskName);
        WebUI.setText(inputSubTaskName,"Sub task name");
        WebUI.sleep(1);
        WebUI.clickElement(inputTaskDescription);
        WebUI.setText(inputTaskDescription,"Đây là description của sub task");
        WebUI.sleep(1);
        WebUI.clickElement(buttonSubDueDate);
        WebUI.sleep(1);
        WebUI.clickElement(buttonDateTime);
        WebUI.sleep(1);
        WebUI.clickElement(dropdowFloatingTime);
        WebUI.sleep(1);
        WebUI.clickElement(optionBangKok);
        WebUI.sleep(2);
        WebUI.hoverElementWithHightLight(buttonSave);
        WebUI.clickElementByJavascriptExecutor(buttonSave);
        WebUI.sleep(1);
        WebUI.clickElement(inputDateTime);
        WebUI.setTextAndClear(inputDateTime,UPDATE_TIME);
        WebUI.sleep(2);
        WebUI.keydownEnter();
        WebUI.sleep(1);
        WebUI.clickElement(buttonAddSubTask);
        WebUI.sleep(1);
        WebUI.clickElement(buttonComment);
        WebUI.sleep(1);
        WebUI.clickElement(inputComment);
        WebUI.setTextAndClear(inputComment,"Đây là comment");
        WebUI.clickElement(buttonSaveComment);
        WebUI.sleep(1);
        WebUI.clickElement(buttonCloseTask);


    }
    public void checkCompleteTask(String TASK_NAME) {
        WebUI.clickElement(buttonMarkCompleteTask);
        WebUI.verifyAssertTrueIsDisplayed(notiCompleteTask,"Fail.Task chưa hoàn thành");
        WebUI.sleep(2);
        WebUI.refreshPage();
        WebUI.clickElement(buttonSearch);
        WebUI.sleep(1);

        WebUI.setTextEnter(inputSearch,TASK_NAME);
        WebUI.sleep(5);
        WebUI.hoverElementWithHightLight(buttonCheckCompleteTask);
        WebUI.sleep(1);
        WebUI.verifyAssertTrueAttribute(buttonCheckCompleteTask,"aria-checked","true","Fail. Button false, vẫn chưa check hoàn thành.");
        WebUI.sleep(2);

    }
    public void checkUndoCompleteTask() {
        WebUI.sleep(2);
        WebUI.clickElement(buttonCheckCompleteTask);
        WebUI.sleep(2);
        WebUI.hoverElementWithHightLight(searchTaskNameResult);
        WebUI.verifyAssertTrueAttribute(buttonMarkCompleteTask,"aria-checked","false","Fail. Button false, vẫn chưa check hoàn thành.");
        WebUI.sleep(2);

    }

    public void deleteTask() {
        WebUI.hoverElementWithHightLight(searchTaskNameResult);
        WebUI.clickElement(buttonMoreActionTask);
        WebUI.sleep(1);
        WebUI.scrollToElement(buttonDeleteTask);
        WebUI.sleep(1);
        WebUI.clickElement(buttonDeleteTask);
        WebUI.sleep(1);
        WebUI.clickElement(buttonConfirmDeleteTask);
        WebUI.sleep(3);
        WebUI.verifyAssertElementNotDisplayed(searchTaskNameResult,"Fail. Project chưa edit thành công.");
        WebUI.verifyAssertTrueIsDisplayed(messageSearchNoResult,"Fail. Chưa hiển thị message No search result.");


    }

}
