package com.trinhthianh.testcases;

import com.trinhthianh.common.BaseTest;
import com.trinhthianh.dataproviders.DataProviderAddTask;
import com.trinhthianh.helpers.ExcelHelper;
import com.trinhthianh.pages.LoginPage;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;

import java.util.Hashtable;

public class SearchTest extends BaseTest {

    ExcelHelper excelLogin;
    ExcelHelper excelAddTask;


    @Test(priority = 1)
    public void testsearchProjectName() {
        String PROJECT_NAME = "ACBVM_999";
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Login.xlsx", "Login");
        LoginPage loginPage = new LoginPage();
        loginPage.loginSuccessWithCustomerAccount(excel.getCellData("email", 4), excel.getCellData("password", 4));
        searchPage().searchProjectName(PROJECT_NAME);

    }

    @Test(priority = 2)
    public void testsearchTaskName() {
        String TASK_NAME = "Task_Metting";
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Login.xlsx", "Login");
        LoginPage loginPage = new LoginPage();
        loginPage.loginSuccessWithCustomerAccount(excel.getCellData("email", 4), excel.getCellData("password", 4));
        searchPage().searchTaskName(TASK_NAME);

    }

    @Test(priority = 3)
    public void testsearchTaskDescription() {
        String TASK_DESCRIPTION = "Đây là description Task_Metting";
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Login.xlsx", "Login");
        LoginPage loginPage = new LoginPage();
        loginPage.loginSuccessWithCustomerAccount(excel.getCellData("email", 4), excel.getCellData("password", 4));
        searchPage().searchTaskDescription(TASK_DESCRIPTION);

    }

    @Test(priority = 4)
    public void testsearchWithNoExitKeyword() {
        String KEYWORD = "@%%%$#*!$123";
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Login.xlsx", "Login");
        LoginPage loginPage = new LoginPage();
        loginPage.loginSuccessWithCustomerAccount(excel.getCellData("email", 4), excel.getCellData("password", 4));
        searchPage().searchWithNoExitKeyword(KEYWORD);

    }

    @Test(priority = 5)
    public void testsearchCompleted() {
        String KEYWORD = "Go to Completed";
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Login.xlsx", "Login");
        LoginPage loginPage = new LoginPage();
        loginPage.loginSuccessWithCustomerAccount(excel.getCellData("email", 4), excel.getCellData("password", 4));
        searchPage().searchCompleted(KEYWORD);

    }

}
