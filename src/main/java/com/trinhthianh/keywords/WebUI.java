package com.trinhthianh.keywords;

import com.trinhthianh.drivers.DriverManager;
import com.trinhthianh.helpers.PropertiesHelper;
import com.trinhthianh.reports.AllureManager;
import com.trinhthianh.reports.ExtentTestManager;
import com.trinhthianh.utils.LogUtils;
import com.aventstack.extentreports.Status;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.List;

public class WebUI {
    private static Actions action = new Actions(DriverManager.getDriver());

    private final static int TIMEOUT = Integer.parseInt(PropertiesHelper.getValue("TIMEOUT"));
    private final static int STEP_TIME = Integer.parseInt(PropertiesHelper.getValue("STEP_TIME"));
    private final static int PAGE_LOAD_TIMEOUT = Integer.parseInt(PropertiesHelper.getValue("PAGE_LOAD_TIMEOUT"));

    public static void sleep(double second) {
        try {
            Thread.sleep((long) (second * 1000));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Step("Click element: {0}")
    public static void clickElement(By by) {
        waitForPageLoaded();
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        sleep(STEP_TIME);
        highLightElement(by);
        DriverManager.getDriver().findElement(by).click();
        ExtentTestManager.logMessage(Status.PASS, "Click element: " + by);
        LogUtils.info("Click element: " + by.toString());
        //AllureReportManager.saveTextLog("Click element: " + by.toString());
    }

    @Step("Click element: {0}")
    public static void clickElement(By by, long timeout) {
        waitForPageLoaded();
        waitForElementVisible(by);
        sleep(STEP_TIME);
        highLightElement(by);
        getWebElement(by).click();
        logConsole("Click element: " + by.toString());
        //AllureReportManager.saveTextLog("Click element: " + by.toString());
        ExtentTestManager.logMessage(Status.PASS, "Click element: " + by);
    }

    public static WebElement getWebElement(By by) {
        return DriverManager.getDriver().findElement(by);
    }

    @Step("Scroll to element {0}")
    public static void scrollToElementToTop(By by) {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("arguments[0].scrollIntoView(true);", getWebElement(by));
        LogUtils.info("Scroll to element " + by);
    }

    @Step("Scroll to element {0}")
    public static void scrollToElementToBottom(By by) {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("arguments[0].scrollIntoView(false);", getWebElement(by));
        LogUtils.info("Scroll to element " + by);
    }

    @Step("Scroll to element {0}")
    public static void scrollToElementToTop(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        LogUtils.info("Scroll to element " + element);
    }

    @Step("Scroll to element {0}")
    public static void scrollToElementToBottom(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("arguments[0].scrollIntoView(false);", element);
        LogUtils.info("Scroll to element " + element);
    }

    @Step("Verify element visible {0}")
    public static boolean verifyElementVisible(By by, String message) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT), Duration.ofMillis(500));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            LogUtils.info("Verify element visible " + by);
            return true;
        } catch (Exception e) {
            if (message.isEmpty() || message == null) {
                LogUtils.error("The element is not visible. " + by);
                Assert.fail("The element is NOT visible. " + by);
            } else {
                LogUtils.error(message + by);
                Assert.fail(message + by);
            }
            return false;
        }
    }

    @Step("Verify {0} is disable")
    public static void verifyElementDisable(By by, String message) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));

        WebElement element = DriverManager.getDriver().findElement(by);
        String ariaDisabled = element.getAttribute("aria-disabled");
        Assert.assertEquals(ariaDisabled, "true", "Button is not disabled");
        LogUtils.info("Verify " +by +" is disable");
        ExtentTestManager.logMessage(Status.PASS, "Verify " +by +" is disable");
    }

    @Step("Verify {1} is display correct on {0}")
    public static void verifyAssertTrueEqual(By by, String verifyText, String message) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        Assert.assertTrue(DriverManager.getDriver().findElement(by).getText().trim().equals(verifyText), message);
        LogUtils.info("Verify " + verifyText + " is display correct on " + by.toString());
        //AllureReportManager.saveTextLog("Verify " + verifyText + " is display correct on " + by.toString());
        ExtentTestManager.logMessage(Status.PASS, "Verify " + verifyText + " is display correct on " + by.toString());
    }

    @Step("Verify attribute {1} is contains {2} on {0}")
    public static void verifyAssertTrueContain(By by, String attribute, String verifyText, String message) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        LogUtils.info("Verify contain: " + verifyText);
        Assert.assertTrue(DriverManager.getDriver().findElement(by).getAttribute(attribute).contains(verifyText), message);
        //AllureReportManager.saveTextLog("Verify " + attribute + " is contains " + verifyText + " on " + by.toString());
        ExtentTestManager.logMessage(Status.PASS, "Verify " + attribute + " is contains " + verifyText + " on " + by.toString());
    }

    @Step("Verify {0} is displayed")
    public static void verifyAssertTrueIsDisplayed(By by, String message) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        LogUtils.info("Verify " + by + " is displayed");
        Assert.assertTrue(DriverManager.getDriver().findElement(by).isDisplayed(), message);
        //AllureReportManager.saveTextLog("Verify " + by + " is displayed");
        ExtentTestManager.logMessage("Verify " + by + " is displayed");
    }

    @Step("Verify {0} is displayed")
    public static void verifyAssertElementNotDisplayed(By by, String message) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
        LogUtils.info("Verify " + by + " is not displayed");
        Assert.assertFalse(DriverManager.getDriver().findElements(by).size() > 0, message);
        //AllureReportManager.saveTextLog("Verify " + by + " is displayed");
        ExtentTestManager.logMessage("Verify " + by + " is not displayed");
    }

    @Step("Verify attribute {1} is contains {2} on {0}")
    public static void verifyAssertTrueAttribute(By by, String attribute, String expectedValue, String message) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        LogUtils.info("Verify attribute " + attribute + " is contains " + expectedValue + " on " + by.toString());
        Assert.assertTrue(DriverManager.getDriver().findElement(by).getAttribute(attribute).trim().equals(expectedValue), message);
        //AllureReportManager.saveTextLog("Verify attribute " + attribute + " is contains " + expectedValue + " on " + by.toString());
        ExtentTestManager.logMessage("Verify attribute " + attribute + " is contains " + expectedValue + " on " + by.toString());
    }

    public static void refreshPage(){
        DriverManager.getDriver().navigate().refresh();
    }

    public static void verifyCurrentURLContains(String identifier,String message){
        Assert.assertFalse(DriverManager.getDriver().getCurrentUrl().contains(identifier),message);
    }



    public static void setValue(By by, String value) {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("\"" + by + ".setAttribute('value'," + value + ")");
    }

    public static List<WebElement> getWebElements(By by) {
        return DriverManager.getDriver().findElements(by);
    }

    @Step("Clear text on: {0}")
    public static void clearText(By by) {
        waitForPageLoaded();
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        sleep(STEP_TIME);
        WebUI.clickElement(by);
        DriverManager.getDriver().findElement(by).sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
//        action.keyDown(Keys.COMMAND).sendKeys("a").keyUp(Keys.COMMAND).sendKeys(Keys.DELETE).build().perform();
        //AllureReportManager.saveTextLog("Clear text on: " + by.toString());
        LogUtils.info("Clear text on: " + by.toString());
        ExtentTestManager.logMessage("Clear text on: " + by.toString());
    }

    @Step("Key down Enter")
    public static void keydownEnter() {
        action.keyDown(Keys.ENTER).keyUp(Keys.ENTER).build().perform();
        //AllureReportManager.saveTextLog("Key down Enter");
        LogUtils.info("Key down Enter");
        ExtentTestManager.logMessage("Key down Enter");
    }

    @Step("Set text {1} on {0}")
    public static void setTextAndClear(By by, String value) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        sleep(STEP_TIME);
        clearText(by);
        highLightElement(by);
        getWebElement(by).sendKeys(value);
        ExtentTestManager.logMessage(Status.PASS, "Set text: " + value + " on element " + by);
        LogUtils.info("Set text: " + value + " on " + by);
    }

    @Step("Set text {1} on {0}")
    public static void setText(By by, String value) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        sleep(STEP_TIME);
        highLightElement(by);
        getWebElement(by).sendKeys(value);
        ExtentTestManager.logMessage(Status.PASS, "Set text: " + value + " on element " + by);
        LogUtils.info("Set text: " + value + " on " + by);
    }

    @Step("Set text on textbox and press key")
    public static void setTextAndClear(By by, String value, Keys keys) {
        waitForElementVisible(by).sendKeys(value, keys);
        LogUtils.info("Set text " + value + " on " + by + " and press key " + keys.name());

        if (ExtentTestManager.getTest() != null) {
            ExtentTestManager.logMessage("Set text " + value + " on " + by + " and press key " + keys.name());
        }
        AllureManager.saveTextLog("Set text " + value + " on " + by + " and press key " + keys.name());
    }

    @Step("Set text {1} on {0} and key down enter")
    public static void setTextEnter(By by, String value) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        sleep(STEP_TIME);
        highLightElement(by);
        getWebElement(by).sendKeys(value, Keys.ENTER);
        ExtentTestManager.logMessage(Status.PASS, "Set text: " + value + " on element " + by);
        //AllureReportManager.saveTextLog("Set text " + value + " on " + by.toString() + " and key down enter");
        LogUtils.info("Set text " + value + " on " + by.toString() + " and key down enter");
    }

    public static int countProject(By by) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        List<WebElement> projects= DriverManager.getDriver().findElements(by);
        int numberOfProject = projects.size();
        return numberOfProject;
    }

    public static void waitForElementClick(By by) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT));
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    public static void waitForElementinVisible(By by) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(by));

    }

    @Step("Click element {0} by JavascriptExecutor")
    public static void clickElementByJavascriptExecutor(By by) {
        WebElement button = DriverManager.getDriver().findElement(by);
        JavascriptExecutor executor = (JavascriptExecutor) DriverManager.getDriver();
        executor.executeScript("arguments[0].click();", button);
        LogUtils.info("Click element " + by + " by JavascriptExecutor");
    }

    @Step("Wait until the element {0} is visible")
    public static WebElement waitForElementVisible(By by) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        ExtentTestManager.logMessage(Status.PASS, "Wait until the element " + by + " is visible");
        //AllureReportManager.saveTextLog("Wait until the element " + by + " is visible");
        LogUtils.info("Wait until the element " + by + " is visible");
        return getWebElement(by);
    }

    @Step("Wait until the element {0} is invisible")
    public static void waitForElementInvisible(By by) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
        ExtentTestManager.logMessage(Status.PASS, "Wait until the element " + by + " is invisible");
        //AllureReportManager.saveTextLog("Wait until the element " + by + " is invisible");
        LogUtils.info("Wait until the element " + by + " is invisible");
    }

    @Step("Open URL: {0}")
    public static void openURL(String URL) {
        DriverManager.getDriver().get(URL);
        logConsole("Open URL: " + URL);
        ExtentTestManager.logMessage(Status.PASS, "Open URL: " + URL);
        waitForPageLoaded();
        //AllureReportManager.saveTextLog("Open URL: " + URL);
    }

    public static String getElementText(By by) {
        waitForElementVisible(by);
        sleep(STEP_TIME);
        return getWebElement(by).getText(); // trả về 1 giá trị  String
    }

    public static void logConsole(Object message) {
        System.out.println(message);
    }

    public static Boolean checkElementExist(By by) {
        waitForPageLoaded();
        sleep(3);
        List<WebElement> listElement = getWebElements(by);
        if (listElement.size() > 0) {
            System.out.println("checkElementExist: " + true + " ---" + by);
            return true;
        } else {
            System.out.println("checkElementExist: " + false + " ---" + by);
            return false;
        }
    }


    public static void waitForElementPresent(By by) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT), Duration.ofMillis(500));
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
        } catch (Throwable error) {
            Assert.fail("Element not exist. " + by.toString());
            logConsole("Element not exist. " + by.toString());
        }
    }

    public static void waitForElementPresent(By by, int timeOut) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeOut), Duration.ofMillis(500));
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
        } catch (Throwable error) {
            Assert.fail("Element not exist. " + by.toString());
            logConsole("Element not exist. " + by.toString());
        }
    }

    public static void waitForElementClickable(By by) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT), Duration.ofMillis(500));
            wait.until(ExpectedConditions.elementToBeClickable(getWebElement(by)));
        } catch (Throwable error) {
            Assert.fail("Timeout waiting for the element ready to click. " + by.toString());
            logConsole("Timeout waiting for the element ready to click. " + by.toString());
        }
    }

    public static void waitForElementClickable(By by, int timeOut) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeOut), Duration.ofMillis(500));
            wait.until(ExpectedConditions.elementToBeClickable(getWebElement(by)));
        } catch (Throwable error) {
            Assert.fail("Timeout waiting for the element ready to click. " + by.toString());
            logConsole("Timeout waiting for the element ready to click. " + by.toString());
        }
    }

    @Step("Verify result {1} is correct")
    public static void verifyEquals(Object actual, Object expected) {
        waitForPageLoaded();
        sleep(STEP_TIME);
        Assert.assertEquals(actual, expected, "Fail, NOT match: " + actual.toString() + " != " + expected.toString());
        ExtentTestManager.logMessage(Status.PASS, "Verify result: " + expected + " is correct");
        AllureManager.saveTextLog("Verify result: " + expected + " is correct");
        LogUtils.info("Verify result: " + expected + " is correct");
    }

    @Step("Verify result {1} is correct")
    public static void verifyEquals(Object actual, Object expected, String message) {
        waitForPageLoaded();
        sleep(STEP_TIME);
        Assert.assertEquals(actual, expected, message);
        ExtentTestManager.logMessage(Status.PASS, "Verify result: " + expected + " is correct");
        AllureManager.saveTextLog("Verify result: " + expected + " is correct");
        LogUtils.info("Verify result: " + expected + " is correct");
    }

    @Step("Verify result {0}{1} is match")
    public static void verifyNotEquals(Object actual, Object expected, String message) {
        waitForPageLoaded();
        sleep(STEP_TIME);
        Assert.assertNotEquals(actual, expected, message);
        ExtentTestManager.logMessage(Status.FAIL, "Verify result: "+actual+" " + expected + " can not match");
        AllureManager.saveTextLog("Verify result: "+actual+" " + expected + " can not match");
        LogUtils.info("Verify result: "+actual+" " + expected + " can not match");
    }

    /**
     * Chờ đợi trang tải xong (Javascript) với thời gian thiết lập sẵn
     */
    public static void waitForPageLoaded() {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(PAGE_LOAD_TIMEOUT), Duration.ofMillis(500));
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();

        // wait for Javascript to loaded
        ExpectedCondition<Boolean> jsLoad = driver -> ((JavascriptExecutor) DriverManager.getDriver()).executeScript("return document.readyState")
                .toString().equals("complete");

        //Get JS is Ready
        boolean jsReady = js.executeScript("return document.readyState").toString().equals("complete");

        //Wait Javascript until it is Ready!
        if (!jsReady) {
            logConsole("Javascript in NOT Ready!");
            //Wait for Javascript to load
            try {
                wait.until(jsLoad);
            } catch (Throwable error) {
                error.printStackTrace();
                Assert.fail("Timeout waiting for page load (Javascript). (" + PAGE_LOAD_TIMEOUT + "s)");
            }
        }
    }

    /**
     * Chờ đợi JQuery tải xong với thời gian thiết lập sẵn
     */
    public static void waitForJQueryLoad() {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(PAGE_LOAD_TIMEOUT), Duration.ofMillis(500));
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();

        //Wait for jQuery to load
        ExpectedCondition<Boolean> jQueryLoad = driver -> {
            assert driver != null;
            return ((Long) ((JavascriptExecutor) DriverManager.getDriver())
                    .executeScript("return jQuery.active") == 0);
        };

        //Get JQuery is Ready
        boolean jqueryReady = (Boolean) js.executeScript("return jQuery.active==0");

        //Wait JQuery until it is Ready!
        if (!jqueryReady) {
            //logConsole("JQuery is NOT Ready!");
            try {
                //Wait for jQuery to load
                wait.until(jQueryLoad);
            } catch (Throwable error) {
                Assert.fail("Timeout waiting for JQuery load. (" + PAGE_LOAD_TIMEOUT + "s)");
            }
        }
    }

    //Wait for Angular Load

    /**
     * Chờ đợi Angular tải xong với thời gian thiết lập sẵn
     */
    public static void waitForAngularLoad() {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(PAGE_LOAD_TIMEOUT), Duration.ofMillis(500));
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        final String angularReadyScript = "return angular.element(document).injector().get('$http').pendingRequests.length === 0";

        //Wait for ANGULAR to load
        ExpectedCondition<Boolean> angularLoad = driver -> {
            assert driver != null;
            return Boolean.valueOf(((JavascriptExecutor) DriverManager.getDriver())
                    .executeScript(angularReadyScript).toString());
        };

        //Get Angular is Ready
        boolean angularReady = Boolean.parseBoolean(js.executeScript(angularReadyScript).toString());

        //Wait ANGULAR until it is Ready!
        if (!angularReady) {
            logConsole("Angular is NOT Ready!");
            //Wait for Angular to load
            try {
                //Wait for jQuery to load
                wait.until(angularLoad);
            } catch (Throwable error) {
                Assert.fail("Timeout waiting for Angular load. (" + PAGE_LOAD_TIMEOUT + "s)");
            }
        }

    }

    public static void scrollToElement(By element) {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("arguments[0].scrollIntoView(false);", getWebElement(element));
    }

    public static void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public static void scrollToPosition(int X, int Y) {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("window.scrollTo(" + X + "," + Y + ");");
    }

    public static boolean moveToElement(By toElement) {
        try {
            Actions action = new Actions(DriverManager.getDriver());
            action.moveToElement(getWebElement(toElement)).release(getWebElement(toElement)).build().perform();
            return true;
        } catch (Exception e) {
            logConsole(e.getMessage());
            return false;
        }
    }

    public static boolean moveToOffset(int X, int Y) {
        try {
            Actions action = new Actions(DriverManager.getDriver());
            action.moveByOffset(X, Y).build().perform();
            return true;
        } catch (Exception e) {
            logConsole(e.getMessage());
            return false;
        }
    }

    public static boolean hoverElement(By by) {
        try {
            Actions action = new Actions(DriverManager.getDriver());
            action.moveToElement(getWebElement(by)).perform();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean hoverElementWithHightLight(By by) {
        try {
            Actions action = new Actions(DriverManager.getDriver());
            action.moveToElement(getWebElement(by)).perform();
            highLightElement(by);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean dragAndDrop(By fromElement, By toElement) {
        try {
            Actions action = new Actions(DriverManager.getDriver());
            action.dragAndDrop(getWebElement(fromElement), getWebElement(toElement)).perform();
            //action.clickAndHold(getWebElement(fromElement)).moveToElement(getWebElement(toElement)).release(getWebElement(toElement)).build().perform();
            return true;
        } catch (Exception e) {
            logConsole(e.getMessage());
            return false;
        }
    }

    public static boolean dragAndDropElement(By fromElement, By toElement) {
        try {
            Actions action = new Actions(DriverManager.getDriver());
            action.clickAndHold(getWebElement(fromElement)).moveToElement(getWebElement(toElement)).release(getWebElement(toElement)).build().perform();
            return true;
        } catch (Exception e) {
            logConsole(e.getMessage());
            return false;
        }
    }

    public static boolean dragAndDropOffset(By fromElement, int X, int Y) {
        try {
            Actions action = new Actions(DriverManager.getDriver());
            //Tính từ vị trí click chuột đầu tiên (clickAndHold)
            action.clickAndHold(getWebElement(fromElement)).pause(1).moveByOffset(X, Y).release().build().perform();
            return true;
        } catch (Exception e) {
            logConsole(e.getMessage());
            return false;
        }
    }

    public static boolean pressENTER() {
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean pressESC() {
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ESCAPE);
            robot.keyRelease(KeyEvent.VK_ESCAPE);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean pressF11() {
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_F11);
            robot.keyRelease(KeyEvent.VK_F11);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * @param by truyền vào đối tượng element dạng By
     * @return Tô màu viền đỏ cho Element trên website
     */
    public static WebElement highLightElement(By by) {
        // Tô màu border ngoài chính element chỉ định - màu đỏ
        if (DriverManager.getDriver() instanceof JavascriptExecutor) {
            ((JavascriptExecutor) DriverManager.getDriver()).executeScript("arguments[0].style.border='3px solid red'", getWebElement(by));
            sleep(0.5);
        }
        return getWebElement(by);
    }
}
