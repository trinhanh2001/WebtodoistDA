package com.trinhthianh.testcases;

import com.trinhthianh.common.BaseTest;
import com.trinhthianh.helpers.ExcelHelper;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    //đăng nhập với email để trống
    @Test(priority = 1)
    public void testLoginFailWithEmailNull() {
        getLoginPage().loginFailWithEmailNull();
    }

    //đăng nhập với acc chưa đăng ký
    @Test(priority = 2)
    public void testLoginFailWithAccDoesNotExist() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Login.xlsx", "Login");
        getLoginPage().loginFailWithAccDoesNotExist(excel.getCellData("email", 1), excel.getCellData("password", 1));

    }

    //đăng nhập với acc chưa đăng ký
    @Test(priority = 8)
    public void testLoginFailWithEmailIncorrectFormat() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Login.xlsx", "Login");
        getLoginPage().loginFailWithEmailIncorrectFormat(excel.getCellData("email", 6), excel.getCellData("password", 6));

    }

    //đăng nhập với password trống
    @Test(priority = 3)
    public void testLoginFailWithNullPassword() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Login.xlsx", "Login");
        getLoginPage().loginFailWithNullPassword(excel.getCellData("email", 2));
    }

    //đăng nhập với password không đúng
    @Test(priority = 4)
    public void testLoginFailWithIncorrectPassword() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Login.xlsx", "Login");
        getLoginPage().loginFailWithIncorrectPassword(excel.getCellData("email", 3), excel.getCellData("password", 3));
    }

        //đăng nhập thành công với email và pasword đúng
    @Test(priority = 5)
    public void testLoginSuccessWithCustomerAccount() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Login.xlsx", "Login");
        getLoginPage().loginSuccessWithCustomerAccount(excel.getCellData("email", 4), excel.getCellData("password", 4));
    }

    //đăng nhập với email và password để trống
    @Test(priority = 6)
    public void testLoginFailWithEmailPasswordNull() {
        getLoginPage().loginFailWithEmailPasswordNull();
    }

    //đăng nhập thành công với email và pasword đúng
    @Test(priority = 7)
    public void testLoginFailWithShortLengthPassword() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Login.xlsx", "Login");
        getLoginPage().loginFailWithShortPasswordLength(excel.getCellData("email", 5), excel.getCellData("password", 5));
    }

}
