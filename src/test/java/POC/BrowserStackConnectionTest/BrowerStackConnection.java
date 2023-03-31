package POC.BrowserStackConnectionTest;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.net.URL;

public class BrowerStackConnection {

    // BrowserStack login
    //un - itsupport@salmon.com
    //password - 4g41nD0ntR3s3t

    public static final String USERNAME = "test19095";
    public static final String AUTOMATE_KEY = "AgQ3qpS7ewgpK64s4qzr";
    //public static final String USERNAME = "wtcbatauto";
    //KCEpmQRK4ghu1NLEU5ca
    //public static final String AUTOMATE_KEY = "KCEpmQRK4ghu1NLEU5ca";

    public static final String stringURL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";
    public static final String URLToVisit = "https://www-eu-uat-global-vype.non-prod.marketing.bat.net/uk/";
    WebDriverWait wait;

    @Test
    public void browserStackConnectionTest() throws Exception {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("browser", "Chrome");
        caps.setCapability("browser_version", "81.0");
        caps.setCapability("os", "OS X");
        caps.setCapability("os_version", "El Capitan");
        caps.setCapability("resolution", "1024x768");
        caps.setCapability("name", "Bstack-[Java] Sample Test");
        WebDriver driver = new RemoteWebDriver(new URL(stringURL), caps);
        wait = new WebDriverWait(driver,30);

        driver.get(URLToVisit);
        driver.quit();
/*        Assert.assertEquals("Title not as expected ",driver.getTitle(),"Home Page UK | Vype UK");
        WebElement allow18 = driver.findElement(By.id("btn-entry-age-allow"));
        waitForELement(allow18);

        allow18.click();

        //perform search
        WebElement searchIcon = driver.findElement(By.cssSelector("div.column.vype_search"));
        waitForELement(searchIcon);
        driver.findElement(By.cssSelector("div.column.vype_search")).click();

        WebElement searchInputBar = driver.findElement(By.cssSelector("#search"));
        waitForELement(searchInputBar);
        searchInputBar.sendKeys("vype");
        driver.findElement(By.cssSelector("#search")).submit();

        wait.until(ExpectedConditions.titleIs("Search results for: 'vype' | Vype UK"));
        Assert.assertEquals("Title not as expected", driver.getTitle(),"Search results for: 'vype' | Vype UK");

        driver.quit();*/

    }

    private void waitForELement(WebElement webElement) {
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }
}


