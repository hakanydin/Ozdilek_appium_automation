package com.testinium.base;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static com.testinium.base.BaseTest.appiumDriver;

public interface BaseFunctions {
    static TouchAction action = new TouchAction(appiumDriver);
    int  x = appiumDriver.manage().window().getSize().width / 2;
    int y_bottom = (int) (appiumDriver.manage().window().getSize().height * 0.8);
    int y_top = (int) (appiumDriver.manage().window().getSize().height * 0.2);
    WebDriverWait wait = new WebDriverWait(appiumDriver,30);
    Logger logger = Logger.getLogger(BaseFunctions.class);

    static void checkOpenApp() throws InterruptedException {
        String context = appiumDriver.getContext();
        if (context.equals("NATIVE_APP")) {
            logger.info("Uygulama Basarili bir sekilde acildi.");
        } else {
            logger.error("Uygulama acilamadi !");
        }
    }

    static void scrollAction(){
        action.press(PointOption.point(x,y_bottom)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
                .moveTo(PointOption.point(x, y_top))
                .release().perform();

    }
    static void scrollActionTop(){
        action.press(PointOption.point(x,y_top)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
                .moveTo(PointOption.point(x, y_bottom))
                .release().perform();
    }

    static void scrollToBottom(){
            scrollAction();
            logger.info("Asagi kaydirma islemi gerçeklesti.");
    }
    static void clickByID(String elmntID){
        wait.until(ExpectedConditions.presenceOfElementLocated(new By.ById(elmntID))).click();
        logger.info("ID ile "+elmntID + " elemanina tiklandi");
    }
    static void clickByXpath(String xpath){
        wait.until(ExpectedConditions.presenceOfElementLocated(new By.ByXPath(xpath))).click();
        logger.info("Xpath  ile "+ xpath + " elemanina tiklandi");
    }
    static void sendKeysID(String elemntID, String text){
        WebElement element = appiumDriver.findElementById(elemntID);
        wait.until(ExpectedConditions.elementToBeClickable(new By.ById(elemntID)));
        element.sendKeys(text);
    }
    static void sendKeysXpath(String elemntXpath, String text){
        WebElement element = appiumDriver.findElementByXPath(elemntXpath);
        wait.until(ExpectedConditions.elementToBeClickable(new By.ByXPath(elemntXpath)));
        element.sendKeys(text);
    }

    static void isSelectedCheckByXpath(String xpath) {
        By element = new By.ByXPath(xpath);
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        Assert.assertTrue(Boolean.parseBoolean(appiumDriver.findElement(element).getAttribute("selected")));
        if (Boolean.parseBoolean(appiumDriver.findElement(element).getAttribute("selected"))) {
            logger.info("Elementin selected ozelligi true'dur.");
        } else {
            logger.error("Elementin selected ozelligi false'tur.");
        }
    }
    static void isSelectedCheckById(String id) {
        By element = new By.ById(id);
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        Assert.assertTrue(Boolean.parseBoolean(appiumDriver.findElement(element).getAttribute("selected")));
        if (Boolean.parseBoolean(appiumDriver.findElement(element).getAttribute("selected"))) {
            logger.info("Elementin selected ozelligi true'dur.");
        } else {
            logger.error("Elementin selected ozelligi false'tur.");
        }
    }



}
