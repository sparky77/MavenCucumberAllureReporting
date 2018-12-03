package HelperClasses;

import HelperClasses.WebDriver.WebDriverLaucher;
import HelperClasses.WebDriver.WebDriverTypeConfig;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
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
 * Writting clever custome methods to interact with page, without the need for extras, such as waits - work in progress
 */
public class BaseClass {

    public String baseUrl = setBaseUrl();
    public WebDriverLaucher driverLauncher;
    Integer defaultWaitTimeOut = 10;

    public static String setBaseUrl(){
        return ReadFrom.propertiesFile("defaultSetupProperties","url");
    }

    public void startDriver() throws MalformedURLException {
       driverLauncher = new WebDriverLaucher();
    }

    public void closeDownWebDriver(){
        driverLauncher.CloseWebDriver();
    }

    public void visit(String url){
        WebDriverTypeConfig.driver.get(url);
    }

    public WebElement find(By locator){
        return waitForExpectedElement(locator);
        //return WebDriverTypeConfig.driver.findElement(locator);
    }

    // Below method should wait for for the expected element to be present - then return the element so it can be interacted with
    // this will be master wait element
    public WebElement waitForExpectedElement(By by){
        WebDriverWait wait = new WebDriverWait(WebDriverTypeConfig.driver,defaultWaitTimeOut);
        System.out.println("\n WAIT SUCCESS :: Element : " + by + " Found successfully");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    // Below is experimental - but working with intergrated fluent wait
    public void click(By locator)throws Exception{
        waitForExpectedElement(locator).click();
        // Below is working - but above is better
        //WebDriverWait wait = new WebDriverWait(WebDriverTypeConfig.driver,10);
        //wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    // Master interaction switch statement
    // will wait for presence of element
    // then try to perform the requested action
    // click / enter text / select dropdown selection
    public void webDriverPerform(String action, By by, String ...requiredText) throws Exception{
        switch (action){
            case "click":
                click(by);
                        break;
            case "enterText":
                find(by).sendKeys(requiredText);
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
