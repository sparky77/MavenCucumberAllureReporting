package HelperClasses;

import HelperClasses.ENUM.SeleniumActionTypes;
import HelperClasses.WebDriver.WebDriverLauncher;
import HelperClasses.WebDriver.WebDriverTypeConfig;
import org.apache.commons.lang.enums.Enum;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLOutput;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;
/**
 * Created by marcus on 29/04/2018.
 * This below class is HelperClasses to be extended by any class using the webDriver.
 * Writing smart custom methods to interact with page, without the need for extras, such as waits - work in progress
 */
public class BaseClass {

    public String baseUrl = setBaseUrl();
    public WebDriverLauncher driverLauncher;
    private static Integer defaultWaitTimeOut = 5; // 5 seconds


    public static String setBaseUrl(){
        return ReadFrom.propertiesFile("defaultSetupProperties","url");
    }

    public void startDriver() throws MalformedURLException {
       driverLauncher = new WebDriverLauncher();
    }

    public void closeDownWebDriver(){
        driverLauncher.CloseWebDriver();
    }

    public void visit(String url) throws MalformedURLException {
        startDriver();
        WebDriverTypeConfig.driver.get(url);
    }

    public static WebElement find(By locator){
        return waitForExpectedElement(locator);
    }

    // Below method should wait for for the expected element to be present - then return the element so it can be interacted with
    // this will be master wait element
    // DOES THIS METHOD NEED TRY CATCH - WHAT IF FAILURE ??
    public static WebElement waitForExpectedElement(By by){
        WebDriverWait wait = new WebDriverWait(WebDriverTypeConfig.driver,defaultWaitTimeOut);
        System.out.println("\n WAIT STARTED :: Element : " + by + " Being searched for");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public void click(By locator)throws Exception{
        waitForExpectedElement(locator).click();
    }

    // Master interaction switch statement
    // will wait for presence of element
    // then try to perform the requested action
    // click / enter text / select dropdown selection
    public static void webDriverPerform(SeleniumActionTypes action, By by, String... requiredText) throws Exception{
        switch (action){
            case click:
                find(by).click();
                break;
            case enterText:
                find(by).sendKeys(requiredText[0]);
                break;
            case SelectFromDropDown:
                Select select = new Select(find(by));
                select.selectByVisibleText(requiredText[0]);
                break;
            case getText:
                find(by).getText();
                break;
            case getValue:
                find(by).getAttribute("value");
                break;
        }
    }

    public void click(WebElement eleName) throws InterruptedException {
        Thread.sleep(5000);
        eleName.click();
    }

    // Untested
    public void type(String inputText,By locator){
        find(locator).sendKeys(inputText);
    }

    // Untested
    public void submit(By locator){
        find(locator).submit();
    }

    public Boolean isDisplayed(By locator){
        try {
            return find(locator).isDisplayed();
        }catch(NoSuchElementException exception){
            return false;
        }
    }

   // Method to get status code - assert 200 :: Experimental but working
    public static int getResponseCode(String urlString) throws MalformedURLException, IOException {
        URL url = new URL(urlString);
        HttpURLConnection huc = (HttpURLConnection)url.openConnection();
        huc.setRequestMethod("GET");
        huc.connect();
        return huc.getResponseCode();
    }
}
