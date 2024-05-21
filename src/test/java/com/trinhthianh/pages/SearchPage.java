package com.trinhthianh.pages;

import com.trinhthianh.helpers.PropertiesHelper;
import com.trinhthianh.keywords.WebUI;
import org.openqa.selenium.By;
import org.testng.Assert;

public class SearchPage extends CommonPage {
    private By menuButtonSearch = By.xpath("//ul[@id='top-menu']//span[normalize-space()='Search']");
    private By inputSearch = By.xpath("//input[@aria-label='Search or type a command…']");
    private By headerResultFor = By.xpath("//h1[contains(text(),'Results for')]");
    private By headerResultProjectName = By.xpath("//div[@data-testid='large-header']//h1");
    private By searchFirstResultRow = By.xpath("(//li[@class='task_list_item'])[1]");
    private By searchFirstTaskNameResult = By.xpath("(//li[@class='task_list_item']//div[@class='task_content'])[1]");
    private By searchFirstTaskDescriptionResult = By.xpath("(//div[@class='task_list_item__content']//div[@class='task_description'])[1]");
    private By messageSearchNoResult = By.xpath("//div[contains(text(),'No completed tasks matching for')]");
    private By markGoToComplete = By.xpath("//span[contains(.,'Go to Completed')]");
    private By firstResultComplete = By.xpath("(//li[@class='event'])[1]");
    private By completedATask = By.xpath("(//div[contains(text(),'completed a task:')])[1]");



    public void searchProjectName(String PROJECT_NAME) {
        WebUI.clickElement(menuButtonSearch);
        WebUI.sleep(1);
        WebUI.clickElement(inputSearch);
        WebUI.sleep(1);
        WebUI.setTextEnter(inputSearch,PROJECT_NAME);
        WebUI.sleep(3);
        WebUI.verifyAssertTrueEqual(headerResultProjectName,PROJECT_NAME,"Fail. Kết quả tìm được khác với tên Project vừa nhập.Hoặc không tìm thấy tên Project để so sánh với tên vừa nhập");

    }

    public void searchTaskName(String TASK_NAME) {
        WebUI.clickElement(menuButtonSearch);
        WebUI.sleep(1);
        WebUI.clickElement(inputSearch);
        WebUI.sleep(1);
        WebUI.setTextEnter(inputSearch,TASK_NAME);
        WebUI.sleep(3);
        WebUI.verifyElementVisible(headerResultFor,"Fail.Chưa hiển thị kết quả Result for tên Task vừa tìm kiếm.");
        WebUI.verifyAssertTrueIsDisplayed(searchFirstResultRow,"Fail.Không có kết quả");
        WebUI.hoverElement(searchFirstTaskNameResult);
        WebUI.verifyAssertTrueEqual(searchFirstTaskNameResult,TASK_NAME,"Fail. Kết quả tìm được khác với tên Task vừa nhập.");

    }

    public void searchTaskDescription(String TASK_DESCRIPTION) {
        WebUI.clickElement(menuButtonSearch);
        WebUI.sleep(1);
        WebUI.clickElement(inputSearch);
        WebUI.sleep(1);
        WebUI.setTextEnter(inputSearch,TASK_DESCRIPTION);
        WebUI.sleep(3);
        WebUI.verifyElementVisible(headerResultFor,"Fail.Chưa hiển thị kết quả Result for tên Task vừa tìm kiếm.");
        WebUI.verifyAssertTrueIsDisplayed(searchFirstResultRow,"Fail.Không có kết quả");
        WebUI.hoverElement(searchFirstTaskDescriptionResult);
        WebUI.sleep(1);
        WebUI.verifyAssertTrueEqual(searchFirstTaskDescriptionResult,TASK_DESCRIPTION,"Fail. Kết quả tìm được khác với tên Description vừa nhập.");

    }

    public void searchWithNoExitKeyword(String KEYWORD) {
        WebUI.clickElement(menuButtonSearch);
        WebUI.sleep(1);
        WebUI.clickElement(inputSearch);
        WebUI.sleep(1);
        WebUI.setTextEnter(inputSearch,KEYWORD);
        WebUI.sleep(3);
        WebUI.verifyElementVisible(headerResultFor,"Fail.Chưa hiển thị kết quả Result for tên Task vừa tìm kiếm.");
        WebUI.sleep(3);
        WebUI.verifyAssertTrueIsDisplayed(messageSearchNoResult,"Fail.Không hiện thông báo No matching result.");

    }

    public void searchCompleted(String KEYWORD) {
        WebUI.clickElement(menuButtonSearch);
        WebUI.sleep(1);
        WebUI.clickElement(inputSearch);
        WebUI.sleep(1);
        WebUI.setText(inputSearch,KEYWORD); //Keyword: Go to Completed
        WebUI.sleep(1);
        WebUI.clickElement(markGoToComplete);
        WebUI.sleep(3);
        WebUI.waitForPageLoaded();
        WebUI.verifyElementVisible(firstResultComplete,"Fail.Chưa hiển thị kết quả Completed task.");
        WebUI.verifyElementVisible(completedATask,"Fail. Result Chưa hiển thị text You completed a task:");
    }

}


