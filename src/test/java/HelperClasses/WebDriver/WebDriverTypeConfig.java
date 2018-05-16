package HelperClasses.WebDriver;

import HelperClasses.ReadFrom;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;

public abstract class WebDriverTypeConfig {
    public final String resourceDriverLocation = "/src/test/resources/drivers/";
    public final String driverType = ReadFrom.propertiesFile("defaultSetupProperties","driverType");
    public static WebDriver driver; // static driver so can be shared with other Pages and does not need instantiation

    protected WebDriver lauchDriverType(String driverType){
        File classPathRoot = new File(System.getProperty("user.dir"));
        String fullPathToDrivers = classPathRoot + resourceDriverLocation;
        checkWebDriverUniqueInstance();
        switch(driverType.toLowerCase()) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", fullPathToDrivers + "chromedriver.exe");
                driver = new ChromeDriver();
                break;
            case "phantomjs":
                DesiredCapabilities caps = new DesiredCapabilities();
                caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, fullPathToDrivers + "phantomjs.exe");
                driver = new PhantomJSDriver(caps);
                break;
            case "firefox":
                System.setProperty("webdriver.firefox.driver", fullPathToDrivers + "geckodriver.exe");
                driver = new FirefoxDriver();
        }
        return driver;
    }

    private void checkWebDriverUniqueInstance(){
        if (driver == null){ // singleton type patten - only want one instance of webDriver
            System.out.println("Original Driver instance ... launching : " + driverType);
            System.out.println(".........................");
        } else {
            System.out.println("Cannot launch another driver instance - already assigned to : " + driverType);
        }
    }

    public void CloseWebDriver(){
        driver.quit();
        driver = null;
    }

}


