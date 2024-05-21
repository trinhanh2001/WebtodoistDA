package com.trinhthianh.pages;

import com.trinhthianh.helpers.PropertiesHelper;
import com.trinhthianh.keywords.WebUI;
import org.openqa.selenium.By;
import org.testng.Assert;

public class SignUpPage extends CommonPage {
    private By inputEmail = By.xpath("//input[@type='email']");
    private By inputPassword = By.xpath("//input[@type='password']");
    private By buttonSubmitSignUp = By.xpath("//button[@type='submit']");
    private By titleSignUpPage = By.xpath("//h1[normalize-space()='Sign up']");
    private By messageRequiredEmail = By.xpath("//div[normalize-space() ='Please enter a valid email address.']");
    private By messageEmailUsed= By.xpath("//div[normalize-space() ='Oh no, this email address is unavailable! Please try a different address.']");
    private By messageRequiredPassword = By.xpath("//p[contains(text(),'Passwords must be at least 8 characters long.')]");
    private By titleCreateProfilePage = By.xpath("//h1[normalize-space()='Create your profile']");
    private By signUpLink = By.xpath("a[href='/auth/signup']");
    private By errormessage = By.xpath("//div[normalize-space() ='Please enter a valid email address.']");



    public void openSignUpPage() {
        WebUI.openURL(PropertiesHelper.getValue("url_signup"));
        WebUI.waitForPageLoaded();
        WebUI.verifyAssertTrueIsDisplayed(titleSignUpPage, "Login page is NOT displayed");
    }


    public void signupFailWithEmailNull() {
        openSignUpPage();
        WebUI.sleep(1);
        WebUI.clickElement(inputPassword);
        WebUI.setText(inputPassword, "abc123456");
        WebUI.sleep(1);
        WebUI.clickElement(buttonSubmitSignUp);
        WebUI.verifyAssertTrueIsDisplayed(messageRequiredEmail, "Error Message không xuất hiện.");
        WebUI.verifyEquals(WebUI.getElementText(messageRequiredEmail), "Please enter a valid email address.", "Nội dung của message không đúng.");
        WebUI.sleep(2);
    }
    public void signupFailWithEmailPasswordNull() {
        openSignUpPage();
        WebUI.sleep(2);
        WebUI.clickElement(inputEmail);
        WebUI.clickElement(inputPassword);
        WebUI.clickElement(buttonSubmitSignUp);
        WebUI.verifyAssertTrueIsDisplayed(errormessage, "Error Message không xuất hiện.");
        WebUI.verifyEquals(WebUI.getElementText(errormessage), "Email and password cannot be empty. Please fill in both fields to proceed.", "Nội dung của message không đúng.");
        WebUI.sleep(2);
    }

    public void signupFailWithEmailIncorrectFormat(String email, String password) {
        openSignUpPage();
        WebUI.clickElement(inputEmail);
        WebUI.setTextAndClear(inputEmail, email);
        WebUI.clickElement(inputPassword);
        WebUI.setTextAndClear(inputPassword, password);
        WebUI.clickElement(buttonSubmitSignUp);
        WebUI.verifyAssertTrueIsDisplayed(messageRequiredEmail, "Error Message không xuất hiện.");
        Assert.assertEquals(WebUI.getElementText(messageRequiredEmail), "Please enter a valid email address.", "Nội dung của message không đúng.");
    }

    public void signupFailWithEmailUsed(String email, String password) {
        openSignUpPage();
        WebUI.clickElement(inputEmail);
        WebUI.setTextAndClear(inputEmail, email);
        WebUI.clickElement(inputPassword);
        WebUI.setTextAndClear(inputPassword, password);
        WebUI.clickElement(buttonSubmitSignUp);
        WebUI.verifyAssertTrueIsDisplayed(messageEmailUsed, "Error Message không xuất hiện.");
        Assert.assertEquals(WebUI.getElementText(messageEmailUsed), "Oh no, this email address is unavailable! Please try a different address.", "Nội dung của message không đúng.");
    }
    public void signupFailWithNullPassword(String email) {
        openSignUpPage();
        WebUI.clickElement(inputEmail);
        WebUI.setTextAndClear(inputEmail, email);
        WebUI.clickElement(buttonSubmitSignUp);
        WebUI.verifyAssertTrueIsDisplayed(messageRequiredPassword, "Password is NULL but Error Message is NOT displayed.");
        Assert.assertEquals(WebUI.getElementText(messageRequiredPassword), "Passwords must be at least 8 characters long.", "Nội dung của message không đúng.");
    }

    public void signupFailWithShortPasswordLength(String email, String password) {
        openSignUpPage();
        WebUI.clickElement(inputEmail);
        WebUI.setTextAndClear(inputEmail, email);
        WebUI.clickElement(inputPassword);
        WebUI.setTextAndClear(inputPassword, password);
        Assert.assertEquals(WebUI.getWebElement(inputPassword).getAttribute("validationMessage"), "Please lengthen this text to 8 characters or more (you are currently using 3 characters).", "Nội dung của message không đúng.");
    }

    public void signupFailWithPasswordOnlyContainsNumber(String email, String password) {
        openSignUpPage();
        WebUI.clickElement(inputEmail);
        WebUI.setTextAndClear(inputEmail, email);
        WebUI.clickElement(inputPassword);
        WebUI.setTextAndClear(inputPassword, password);
        WebUI.clickElement(buttonSubmitSignUp);
        WebUI.waitForPageLoaded();
        WebUI.verifyCurrentURLContains("signup","Fail. Không được tạo tài khoản thành công với password chỉ toàn số. Cần ở lại trang sign up không được chuyển qua trang create-profile.");
        WebUI.sleep(2);

    }
    public void signupFailWithPasswordOnlyContainsCharacter(String email, String password) {
        openSignUpPage();
        WebUI.clickElement(inputEmail);
        WebUI.setTextAndClear(inputEmail, email);
        WebUI.clickElement(inputPassword);
        WebUI.setTextAndClear(inputPassword, password);
        WebUI.clickElement(buttonSubmitSignUp);
        WebUI.waitForPageLoaded();
        WebUI.verifyCurrentURLContains("signup","Fail. Không được tạo tài khoản thành công với password chỉ toàn chữ. Cần ở lại trang sign up không được chuyển qua trang create-profile.");
        WebUI.sleep(2);
    }
    public void signupSuccessWithValidEmailPassword(String email, String password) {
        openSignUpPage();
        WebUI.clickElement(inputEmail);
        WebUI.setTextAndClear(inputEmail, email);
        WebUI.clickElement(inputPassword);
        WebUI.setTextAndClear(inputPassword, password);
        WebUI.clickElement(buttonSubmitSignUp);
        WebUI.waitForPageLoaded();
        WebUI.waitForElementVisible(titleCreateProfilePage);
        WebUI.verifyAssertTrueIsDisplayed(titleCreateProfilePage, "Create profile page is NOT displayed.");
        WebUI.verifyEquals(WebUI.getElementText(titleCreateProfilePage), "Create your profile");
    }
}


