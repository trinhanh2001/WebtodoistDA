package com.trinhthianh.testcases;

import com.trinhthianh.common.BaseTest;
import com.trinhthianh.helpers.ExcelHelper;
import org.testng.annotations.Test;

public class SignupTest extends BaseTest {


    @Test(priority = 1)
    public void testSignupFailWithEmailNull() {
        signUpPage().signupFailWithEmailNull();
    }


    @Test(priority = 2)
    public void testSignupFailWithEmailIncorrectFormat() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Signup.xlsx", "Signup");
        signUpPage().signupFailWithEmailIncorrectFormat(excel.getCellData("email", 1), excel.getCellData("password", 1));

    }


    @Test(priority = 3)
    public void testSignupFailWithNullPassword() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Signup.xlsx", "Signup");
        signUpPage().signupFailWithNullPassword(excel.getCellData("email", 2));
    }


    @Test(priority = 4)
    public void testSignupFailWithPasswordOnlyContainsNumber() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Signup.xlsx", "Signup");
        signUpPage().signupFailWithPasswordOnlyContainsNumber(excel.getCellData("email", 3), excel.getCellData("password", 3));
    }

    @Test(priority = 4)
    public void testSignupFailWithPasswordOnlyContainsCharacter() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Signup.xlsx", "Signup");
        signUpPage().signupFailWithPasswordOnlyContainsCharacter(excel.getCellData("email", 4), excel.getCellData("password", 4));
    }

    @Test(priority = 5)
    public void testSignupSuccessWithValidEmailPassword() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Signup.xlsx", "Signup");
        signUpPage().signupSuccessWithValidEmailPassword(excel.getCellData("email", 5), excel.getCellData("password", 5));
    }


    @Test(priority = 6)
    public void testSignupFailWithEmailPasswordNull() {
        signUpPage().signupFailWithEmailPasswordNull();
    }


    @Test(priority = 7)
    public void testSignupFailWithShortLengthPassword() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Signup.xlsx", "Signup");
        signUpPage().signupFailWithShortPasswordLength(excel.getCellData("email", 6), excel.getCellData("password", 6));
    }

    @Test(priority = 8)
    public void testSignupFailWithEmailUsed() {
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("DataTest/Signup.xlsx", "Signup");
        signUpPage().signupFailWithEmailUsed(excel.getCellData("email", 7), excel.getCellData("password", 7));
    }

}
