package com.trinhthianh.pages;

import com.trinhthianh.drivers.DriverManager;
import com.trinhthianh.helpers.PropertiesHelper;
import com.trinhthianh.keywords.WebUI;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class MyProjectsPage extends CommonPage{
    private String nameProjectVerify;
    private By menuMyProjects = By.xpath("//div[normalize-space()='My Projects']");
    private By titleMyProjectsPage = By.xpath("//h1[normalize-space()='My Projects']");
    private By buttonPlusAddProject = By.xpath("//main[@id='content']//button[@aria-label ='My projects menu']");
    private By addNewProject = By.xpath("//div[normalize-space()='Add project']");
    private By inputNameProject = By.xpath("//input[@id='edit_project_modal_field_name']");
    private By dropdownColor = By.xpath("//span[@class='color_dropdown_select__name']");
    private By optionBlueColor = By.xpath("//span[normalize-space()='Blue']");
    private By optionRedColor = By.xpath("//span[normalize-space()='Red']");
    private By toggleAddFavorites = By.xpath("//label[normalize-space()='Add to favorites']");
    private By buttonSubmitAddProject = By.xpath("//button[@type='submit']");
    private By inputSearchProjects = By.xpath("//input[@name='project_search']");
    private By searchFirstResult = By.xpath("(//li[@data-testid='project-row'])[1]");
    private By firstProjectButtonMoreAction = By.xpath("((//li[@data-testid='project-row']) //button[@aria-label='More project actions'])[1]");
    private By buttonEditProject = By.xpath("//div[normalize-space()='Edit']");
    private By ProjectName = By.xpath("//div[@data-testid='large-header']//h1");
    private By buttonSubmitEditProject = By.xpath("//button[@type='submit']");
    private By buttonDeleteProject = By.xpath("//div[normalize-space()='Delete']");
    private By buttonConfirmDeleteProject = By.xpath("//button[@type='submit']");
    private By usedLimitProject = By.xpath("//span[normalize-space()='Used: 5/5']");
    private By projectItem = By.xpath("//ul[@id='projects_list']//li");
    private By dialogNeedMoreProject = By.xpath("//div[@role='dialog']");
    private By buttonCloseDialog = By.xpath("(//button[@aria-label='Close modal'])[1]");
    private By noMatchResult = By.xpath("//div[@class='uWruwq5']");



    public void openMyProjectsPage() {
        WebUI.openURL(PropertiesHelper.getValue("url_home"));
        WebUI.waitForPageLoaded();
        WebUI.clickElement(menuMyProjects);
        WebUI.verifyAssertTrueIsDisplayed(titleMyProjectsPage, "My Projects page is NOT displayed");
    }


    public void addNewProject(String PROJECT_NAME) {
        WebUI.clickElement(menuMyProjects);
        WebUI.sleep(1);
        WebUI.clickElement(buttonPlusAddProject);
        WebUI.clickElement(addNewProject);
        WebUI.verifyElementDisable(buttonSubmitAddProject,"Fail.Button Add Project is enable");
        WebUI.setText(inputNameProject, PROJECT_NAME);
        WebUI.sleep(1);
        WebUI.clickElement(dropdownColor);
        WebUI.sleep(1);
        WebUI.clickElement(optionBlueColor);
        WebUI.sleep(1);
        WebUI.clickElement(toggleAddFavorites);
        WebUI.sleep(1);
        WebUI.clickElement(buttonSubmitAddProject);
        WebUI.verifyCurrentURLContains("active","Fail. Vẫn chưa chuyển đến trang Project vừa tạo");
        WebUI.sleep(2);
        WebUI.hoverElementWithHightLight(ProjectName);
        WebUI.sleep(2);
        nameProjectVerify = WebUI.getElementText(ProjectName);
        WebUI.verifyEquals(nameProjectVerify,PROJECT_NAME,"Fail.Tên Project name khác với tên Project vừa tạo");

    }

    public void addNewProjectWithExitName(String PROJECT_NAME_1,String PROJECT_NAME_2) {
        addNewProject(PROJECT_NAME_1);
        WebUI.clickElement(menuMyProjects);
        WebUI.sleep(1);
        WebUI.clickElement(buttonPlusAddProject);
        WebUI.clickElement(addNewProject);
        WebUI.verifyElementDisable(buttonSubmitAddProject,"Fail.Button Add Project is enable");
        WebUI.setText(inputNameProject, PROJECT_NAME_2);
        WebUI.verifyNotEquals(PROJECT_NAME_1,PROJECT_NAME_2,"Fail. Tên project bị trùng nhau.");
        WebUI.sleep(1);
        WebUI.clickElement(dropdownColor);
        WebUI.sleep(1);
        WebUI.clickElement(optionBlueColor);
        WebUI.sleep(1);
        WebUI.clickElement(toggleAddFavorites);
        WebUI.sleep(1);
        WebUI.clickElement(buttonSubmitAddProject);
        WebUI.verifyCurrentURLContains("active","Fail. Vẫn chưa chuyển đến trang Project vừa tạo");
        WebUI.sleep(2);
        WebUI.hoverElementWithHightLight(ProjectName);
        WebUI.sleep(2);
        nameProjectVerify = WebUI.getElementText(ProjectName);
        WebUI.verifyEquals(nameProjectVerify,PROJECT_NAME_2,"Fail.Tên Project name khác với tên Project vừa tạo");

    }

    public void addMoreThanFiveProject(String PROJECT_NAME) {
        int count = WebUI.countProject(projectItem);
        for (; count< 5 ; count++) {
                WebUI.clickElement(menuMyProjects);
                WebUI.sleep(1);
                WebUI.clickElement(buttonPlusAddProject);
                WebUI.clickElement(addNewProject);
                WebUI.verifyElementDisable(buttonSubmitAddProject,"Fail.Button Add Project is enable");
                WebUI.setText(inputNameProject, PROJECT_NAME);
                WebUI.sleep(1);
                WebUI.clickElement(dropdownColor);
                WebUI.sleep(1);
                WebUI.clickElement(optionBlueColor);
                WebUI.sleep(1);
                WebUI.clickElement(toggleAddFavorites);
                WebUI.sleep(1);
                WebUI.clickElement(buttonSubmitAddProject);
                WebUI.verifyCurrentURLContains("active", "Fail. Vẫn chưa chuyển đến trang Project vừa tạo");
                WebUI.sleep(2);
                WebUI.hoverElementWithHightLight(ProjectName);
                WebUI.sleep(2);
                nameProjectVerify = WebUI.getElementText(ProjectName);
                WebUI.verifyEquals(nameProjectVerify, PROJECT_NAME, "Fail.Tên Project name khác với tên Project vừa tạo");
            }
                WebUI.clickElement(menuMyProjects);
                WebUI.sleep(1);
                WebUI.clickElement(buttonPlusAddProject);
                WebUI.clickElement(addNewProject);
                WebUI.verifyElementVisible(dialogNeedMoreProject, "Fail. Vượt quá limit 5 projects nhưng không hiện dialog.");
                WebUI.clickElement(buttonCloseDialog);
                WebUI.sleep(3);
                WebUI.verifyElementVisible(usedLimitProject, "Fail.Used project is more than Used: 5/5 ");
                WebUI.sleep(3);


    }

    public void searchProject(String PROJECT_NAME){
        WebUI.clickElement(menuMyProjects);
        WebUI.waitForPageLoaded();
        WebUI.setTextAndClear(inputSearchProjects, PROJECT_NAME, Keys.ENTER);
        WebUI.sleep(3);
        WebUI.waitForElementVisible(searchFirstResult);
        WebUI.sleep(2);
        WebUI.hoverElement(searchFirstResult);
        WebUI.sleep(2);
    }

    public void verifyProjectDetail(String PROJECT_NAME){
        WebUI.clickElement(firstProjectButtonMoreAction);
        WebUI.sleep(1);
        WebUI.clickElement(buttonEditProject);
        WebUI.verifyAssertTrueAttribute(inputNameProject,"value",PROJECT_NAME,"Tên Project không đúng");
        WebUI.sleep(1);
    }

    public void editProject(String PROJECT_NAME,String UPDATE_PROJECT_NAME) {
        WebUI.clickElement(firstProjectButtonMoreAction);
        WebUI.sleep(1);
        WebUI.clickElement(buttonEditProject);
        WebUI.setTextAndClear(inputNameProject, UPDATE_PROJECT_NAME);
        WebUI.sleep(1);
        WebUI.clickElement(dropdownColor);
        WebUI.sleep(1);
        WebUI.clickElement(optionRedColor);
        WebUI.sleep(1);
        WebUI.clickElement(toggleAddFavorites);
        WebUI.sleep(1);
        WebUI.clickElement(buttonSubmitEditProject);
        WebUI.sleep(3);
        WebUI.clickElement(menuMyProjects);
        WebUI.waitForPageLoaded();
        WebUI.setTextAndClear(inputSearchProjects, PROJECT_NAME, Keys.ENTER);
        WebUI.sleep(3);
        WebUI.verifyAssertTrueIsDisplayed(noMatchResult,"Fail. Project chưa edit thành công");
        searchProject(UPDATE_PROJECT_NAME);
        WebUI.clickElement(searchFirstResult);
        WebUI.sleep(2);
        WebUI.hoverElementWithHightLight(ProjectName);
        WebUI.sleep(2);
        nameProjectVerify = WebUI.getElementText(ProjectName);
        WebUI.verifyEquals(nameProjectVerify,UPDATE_PROJECT_NAME,"Fail.Tên Project name khác với tên Project vừa tạo");

    }
    public void deleteProject() {
        WebUI.clickElement(firstProjectButtonMoreAction);
        WebUI.sleep(1);
        WebUI.scrollToElement(buttonDeleteProject);
        WebUI.sleep(1);
        WebUI.clickElement(buttonDeleteProject);
        WebUI.sleep(1);
        WebUI.clickElement(buttonConfirmDeleteProject);
        WebUI.sleep(3);
        WebUI.verifyAssertElementNotDisplayed(searchFirstResult,"Fail. Project chưa edit thành công");

    }

}
