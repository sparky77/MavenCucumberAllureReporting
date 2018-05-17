package HelperClasses.WebDriver;

import HelperClasses.ReadFrom;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public abstract class WebDriverTypeConfig {
    private final String resourceDriverLocation = "/src/test/resources/drivers/";
    private String modifiedOSDriverLocation;
    final String driverType = ReadFrom.propertiesFile("defaultSetupProperties","driverType");
    private String osType;
    private File classPathRoot;
    public static WebDriver driver; // static driver so can be shared with other Pages and does not need instantiation

    protected WebDriver lauchDriverType(String driverType) throws MalformedURLException {
        classPathRoot = new File(System.getProperty("user.dir"));
        osType = getOSType();
        checkWebDriverUniqueInstance();

        switch(driverType.toLowerCase()) {
            case "chrome":
                chromeDriverOSPathConstructor();
                break;
            case "phantomjs":
                phantomJSDriverOSPathConstructor();
                break;
            case "firefox":
                fireFoxDriverOSPathConstructor();
                break;
            case "grid":
                System.out.println("Grid setup to go here");
                DesiredCapabilities caps = new DesiredCapabilities();
                // set desired caps - i.e. Chrome etc - will be DOCKER setup
                // IP below is not valid - just example - DOCKER GRID HUB will advertise correct URL
                driver = new RemoteWebDriver(new URL("http://10.0.0.152:4444/wd/hub"),caps);
                break;

            default:
                System.out.println("ERROR :: WEB DRIVER CHOICE NOT RECOGNISED ");
        }
        return driver;
    }

    private void fireFoxDriverOSPathConstructor() {
        modifiedOSDriverLocation = classPathRoot + resourceDriverLocation + "Win/geckodriver.exe";
        System.setProperty("webdriver.gecko.driver", modifiedOSDriverLocation);
        driver = new FirefoxDriver();
    }

    private void phantomJSDriverOSPathConstructor() {
        if (osType.contains("Mac")) {
            modifiedOSDriverLocation = classPathRoot + resourceDriverLocation + "Mac/phantomjs";
        } else if (osType.contains("Windows")) {
            modifiedOSDriverLocation = classPathRoot + resourceDriverLocation + "Win/phantomjs.exe";
        }
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, modifiedOSDriverLocation);
        driver = new PhantomJSDriver(caps);
    }

    private void chromeDriverOSPathConstructor() {
        if (osType.contains("Mac")) {
            modifiedOSDriverLocation = classPathRoot + resourceDriverLocation + "Mac/chromedriver";
        } else if (osType.contains("Windows")) {
            modifiedOSDriverLocation = classPathRoot + resourceDriverLocation + "/Win/chromedriver.exe";
            System.out.println("Chome location : " +modifiedOSDriverLocation);
        }
        System.setProperty("webdriver.chrome.driver", modifiedOSDriverLocation);
        driver = new ChromeDriver();
    }

    private String getOSType(){
        return System.getProperty("os.name");
    }

    private void checkWebDriverUniqueInstance(){
        if (driver == null){ // singleton type patten - only want one instance of webDriver
            System.out.println("\nOriginal Driver instance ... launching : " + driverType);
        } else {
            System.out.println("Cannot launch another driver instance - already assigned to : " + driverType);
        }
    }

    public void CloseWebDriver(){
        driver.quit();
        driver = null;
    }

}


