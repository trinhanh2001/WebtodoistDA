package com.trinhthianh.pages;

import com.trinhthianh.helpers.PropertiesHelper;
import com.trinhthianh.keywords.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class LoginPage extends CommonPage {
    private By inputEmail = By.xpath("//input[@type='email']");
    private By inputPassword = By.xpath("//input[@type='password']");
    private By buttonSubmitLogin = By.xpath("//button[@type='submit']");
    private By titleLoginPage = By.xpath("//h1[normalize-space()='Log in']");
    private By messageRequiredEmail = By.xpath("//div[normalize-space() ='Please enter a valid email address.']");
    private By messageAccDoesNotExist = By.xpath("//div[normalize-space() ='Wrong email or password.']");
    private By messageRequiredPassword = By.xpath("//p[contains(text(),'Passwords must be at least 8 characters long.')]");
    private static By titleTodayPage = By.xpath("//span[normalize-space()='Today']");
    private By errormessage = By.xpath("//p[@id='element-5']");



    public void openLoginPage() {
        WebUI.openURL(PropertiesHelper.getValue("url"));
        WebUI.waitForPageLoaded();
        WebUI.verifyAssertTrueIsDisplayed(titleLoginPage, "Login page is NOT displayed");
    }


    public void loginFailWithEmailNull() {
        openLoginPage();
        WebUI.sleep(1);
        WebUI.clickElement(inputPassword);
        WebUI.setText(inputPassword, "abc219203");
        WebUI.sleep(1);
        WebUI.clickElement(buttonSubmitLogin);
        WebUI.verifyAssertTrueIsDisplayed(messageRequiredEmail, "Error Message không xuất hiện.");
        WebUI.verifyEquals(WebUI.getElementText(messageRequiredEmail), "Please enter a valid email address.", "Nội dung của message không đúng.");
        WebUI.sleep(2);
    }
    public void loginFailWithEmailPasswordNull() {
        openLoginPage();
        WebUI.sleep(2);
        WebUI.clickElement(inputEmail);
        WebUI.clickElement(inputPassword);
        WebUI.clickElement(buttonSubmitLogin);
        WebUI.verifyAssertTrueIsDisplayed(errormessage, "Error Message không xuất hiện.");
        WebUI.verifyEquals(WebUI.getElementText(errormessage), "Email and password cannot be empty. Please fill in both fields to proceed.", "Nội dung của message không đúng.");
        WebUI.sleep(2);
    }

    public void loginFailWithAccDoesNotExist(String email, String password) {
        openLoginPage();
        WebUI.clickElement(inputEmail);
        WebUI.setTextAndClear(inputEmail, email);
        WebUI.clickElement(inputPassword);
        WebUI.setTextAndClear(inputPassword, password);
        WebUI.clickElement(buttonSubmitLogin);
        WebUI.verifyAssertTrueIsDisplayed(messageAccDoesNotExist, "Error Message không xuất hiện.");
        Assert.assertEquals(WebUI.getElementText(messageAccDoesNotExist), "Wrong email or password.", "Nội dung của message không đúng.");
    }

    public void loginFailWithEmailIncorrectFormat(String email, String password) {
        openLoginPage();
        WebUI.clickElement(inputEmail);
        WebUI.setTextAndClear(inputEmail, email);
        WebUI.clickElement(inputPassword);
        WebUI.setTextAndClear(inputPassword, password);
        WebUI.clickElement(buttonSubmitLogin);
        WebUI.verifyAssertTrueIsDisplayed(messageAccDoesNotExist, "Error Message không xuất hiện.");
        Assert.assertEquals(WebUI.getElementText(messageAccDoesNotExist), "Wrong email or password.", "Nội dung của message không đúng.");
    }

    public void loginFailWithNullPassword(String email) {
        openLoginPage();
        WebUI.clickElement(inputEmail);
        WebUI.setTextAndClear(inputEmail, email);
        WebUI.clickElement(buttonSubmitLogin);
        WebUI.verifyAssertTrueIsDisplayed(messageRequiredPassword, "Password is NULL but Error Message is NOT displayed.");
        Assert.assertEquals(WebUI.getElementText(messageRequiredPassword), "Passwords must be at least 8 characters long.", "Nội dung của message không đúng.");
    }

    public void loginFailWithShortPasswordLength(String email, String password) {
        openLoginPage();
        WebUI.clickElement(inputEmail);
        WebUI.setTextAndClear(inputEmail, email);
        WebUI.clickElement(inputPassword);
        WebUI.setTextAndClear(inputPassword, password);
        Assert.assertEquals(WebUI.getWebElement(inputPassword).getAttribute("validationMessage"), "Please lengthen this text to 8 characters or more (you are currently using 3 characters).", "Nội dung của message không đúng.");
    }

    public void loginFailWithIncorrectPassword(String email, String password) {
        openLoginPage();
        WebUI.clickElement(inputEmail);
        WebUI.setTextAndClear(inputEmail, email);
        WebUI.clickElement(inputPassword);
        WebUI.setTextAndClear(inputPassword, password);
        WebUI.clickElement(buttonSubmitLogin);
        WebUI.verifyAssertTrueIsDisplayed(messageAccDoesNotExist, "Password is incorrect but Error Message is NOT displayed.");
        Assert.assertEquals(WebUI.getElementText(messageAccDoesNotExist), "Wrong email or password.", "Nội dung của message không đúng.");
    }

    public void loginSuccessWithCustomerAccount(String email, String password) {
        openLoginPage();
        WebUI.clickElement(inputEmail);
        WebUI.setTextAndClear(inputEmail, email);
        WebUI.clickElement(inputPassword);
        WebUI.setTextAndClear(inputPassword, password);
        WebUI.clickElement(buttonSubmitLogin);
        WebUI.waitForElementVisible(titleTodayPage);
        WebUI.verifyAssertTrueIsDisplayed(titleTodayPage, "Today page is NOT displayed.");
        WebUI.verifyEquals(WebUI.getElementText(titleTodayPage), "Today");
    }
}


