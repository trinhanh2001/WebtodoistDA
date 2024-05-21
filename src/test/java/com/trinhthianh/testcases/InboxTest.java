package com.trinhthianh.testcases;

import com.trinhthianh.common.BaseTest;
import com.trinhthianh.dataproviders.DataProviderAddTask;
import com.trinhthianh.helpers.ExcelHelper;
import com.trinhthianh.pages.LoginPage;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;

import java.util.Hashtable;

public class InboxTest extends BaseTest {

    ExcelHelper excelLogin;
    ExcelHelper excelAddTask;

    @Test(priority = 1)
    public void testAddTask() {
        String TASK_NAME ="Task_Metting";
        String TASK_DESCRIPTION ="Đây là description "+TASK_NAME;
        String PROJECT_NAME ="Inbox";
        String TIME ="Jul 15 3 AM";

        excelLogin = new ExcelHelper();
        excelAddTask = new ExcelHelper();
        excelLogin.setExcelFile("DataTest/Login.xlsx", "Login");
        excelAddTask.setExcelFile("DataTest/AddTask.xlsx", "AddTask");
        LoginPage loginPage = new LoginPage();
        loginPage.loginSuccessWithCustomerAccount(excelLogin.getCellData("email", 4), excelLogin.getCellData("password", 4));
        inboxPage().addNewTask(TASK_NAME,TASK_DESCRIPTION, PROJECT_NAME,TIME);
        inboxPage().searchTask(TASK_NAME);
        inboxPage().verifyTaskDetail(TASK_NAME,TASK_DESCRIPTION, PROJECT_NAME,TIME);
    }


    @Test(priority = 2)
    public void testEditTask() {
        String TASK_NAME ="Task_1";
        String TASK_DESCRIPTION ="Đây là description "+TASK_NAME;
        String PROJECT_NAME ="Inbox";
        String TIME ="Jul 10 10 AM";
        String UPDATE_PROJECT_NAME ="ACBVM_999";
        String UPDATE_TASK_NAME ="TASK_SMART_HOME_UPDATE_NAME";
        String UPDATE_TASK_DESCRIPTION ="TASK_SMART_HOME_DESCRIPTION_UPDATE" ;
        String UPDATE_TIME ="Jul 15 3 AM";
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Login.xlsx", "Login");
        LoginPage loginPage = new LoginPage();
        loginPage.loginSuccessWithCustomerAccount(excel.getCellData("email", 4), excel.getCellData("password", 4));
        inboxPage().addNewTask(TASK_NAME,TASK_DESCRIPTION,PROJECT_NAME,TIME);
        inboxPage().searchTask(TASK_NAME);
        inboxPage().verifyTaskDetail(TASK_NAME,TASK_DESCRIPTION,PROJECT_NAME,TIME);
        inboxPage().editTask(UPDATE_TASK_NAME,UPDATE_TASK_DESCRIPTION,UPDATE_PROJECT_NAME,UPDATE_TIME);
        inboxPage().searchTask(UPDATE_TASK_NAME);
        inboxPage().verifyTaskDetail(UPDATE_TASK_NAME,UPDATE_TASK_DESCRIPTION,UPDATE_PROJECT_NAME,UPDATE_TIME);

    }


    @Test(priority = 3)
    public void testDeleteTask() {
        String TASK_NAME ="Task_2";
        String TASK_DESCRIPTION ="Đây là description "+TASK_NAME;
        String PROJECT_NAME ="Inbox";
        String TIME ="20 Jul 11:00";
        excelLogin = new ExcelHelper();
        excelAddTask = new ExcelHelper();
        excelLogin.setExcelFile("DataTest/Login.xlsx", "Login");
        excelAddTask.setExcelFile("DataTest/AddTask.xlsx", "AddTask");
        LoginPage loginPage = new LoginPage();
        loginPage.loginSuccessWithCustomerAccount(excelLogin.getCellData("email", 4), excelLogin.getCellData("password", 4));
        inboxPage().addNewTask(TASK_NAME,TASK_DESCRIPTION,PROJECT_NAME,TIME);
        inboxPage().searchTask(TASK_NAME);
        inboxPage().deleteTask();

    }
    @Test(priority = 4)
    public void checkCompleteAndUndoTask() {
        String TASK_NAME ="Task_Testcase";
        String TASK_DESCRIPTION ="Đây là description "+TASK_NAME;
        String PROJECT_NAME ="Inbox";
        String TIME ="18 Jul 17:00";
        excelLogin = new ExcelHelper();
        excelAddTask = new ExcelHelper();
        excelLogin.setExcelFile("DataTest/Login.xlsx", "Login");
        excelAddTask.setExcelFile("DataTest/AddTask.xlsx", "AddTask");
        LoginPage loginPage = new LoginPage();
        loginPage.loginSuccessWithCustomerAccount(excelLogin.getCellData("email", 4), excelLogin.getCellData("password", 4));
        inboxPage().addNewTask(TASK_NAME,TASK_DESCRIPTION,PROJECT_NAME,TIME);
        inboxPage().searchTask(TASK_NAME);
        inboxPage().checkCompleteTask(TASK_NAME);
        inboxPage().checkUndoCompleteTask();
    }
}
