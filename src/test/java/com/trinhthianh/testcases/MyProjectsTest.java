package com.trinhthianh.testcases;

import com.trinhthianh.common.BaseTest;
import com.trinhthianh.helpers.ExcelHelper;
import com.trinhthianh.pages.LoginPage;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;

public class MyProjectsTest extends BaseTest {

    @Test(priority = 1)
    public void testAddNewProject() {
        String PROJECT_NAME ="ACBVM_1";
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Login.xlsx", "Login");
        LoginPage loginPage = new LoginPage();
        loginPage.loginSuccessWithCustomerAccount(excel.getCellData("email", 4), excel.getCellData("password", 4));
        getMyProjectsPage().addNewProject(PROJECT_NAME);
    }


    @Test(priority = 2)
    public void testAddNewProjectWithExitName() {
        String PROJECT_NAME_1 ="ACBVM_999";
        String PROJECT_NAME_2 ="ACBVM_999";
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Login.xlsx", "Login");
        LoginPage loginPage = new LoginPage();
        loginPage.loginSuccessWithCustomerAccount(excel.getCellData("email", 4), excel.getCellData("password", 4));
        getMyProjectsPage().addNewProjectWithExitName(PROJECT_NAME_1,PROJECT_NAME_2);
    }

    @Test(priority = 3)
    public void testEditProject() {
        String PROJECT_NAME ="ACBVM_2";
        String UPDATE_PROJECT_NAME ="SMART_HOME_2";
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Login.xlsx", "Login");
        LoginPage loginPage = new LoginPage();
        loginPage.loginSuccessWithCustomerAccount(excel.getCellData("email", 4), excel.getCellData("password", 4));
        getMyProjectsPage().addNewProject(PROJECT_NAME);
        getMyProjectsPage().searchProject(PROJECT_NAME);
        getMyProjectsPage().editProject(PROJECT_NAME,UPDATE_PROJECT_NAME);
    }

    @Test(priority = 4)
    public void testDeleteProject() {
        String PROJECT_NAME ="ACBVM_3";
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Login.xlsx", "Login");
        LoginPage loginPage = new LoginPage();
        loginPage.loginSuccessWithCustomerAccount(excel.getCellData("email", 4), excel.getCellData("password", 4));
        getMyProjectsPage().addNewProject(PROJECT_NAME);
        getMyProjectsPage().searchProject(PROJECT_NAME);
        getMyProjectsPage().deleteProject();
    }

    @Test(priority = 5)
    public void testAddMoreThanFiveProject() {
        String PROJECT_NAME ="ACBVM_4" ;
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Login.xlsx", "Login");
        LoginPage loginPage = new LoginPage();
        loginPage.loginSuccessWithCustomerAccount(excel.getCellData("email", 4), excel.getCellData("password", 4));
        getMyProjectsPage().addMoreThanFiveProject(PROJECT_NAME);
    }


}
