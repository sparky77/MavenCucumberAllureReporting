package HelperClasses.WebDriver;

import HelperClasses.ReadFrom;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
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
    DesiredCapabilities caps;
    public static WebDriver driver; // static driver so can be shared with other Pages and does not need instantiation

    protected WebDriver lauchDriverType(String driverType) throws MalformedURLException {
        classPathRoot = new File(System.getProperty("user.dir"));
        osType = getOSType();
        //checkWebDriverUniqueInstance();

        switch(driverType.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                //chromeDriverOSPathConstructor();
                break;
            case "remote":
                // TRYING to fire remote driver for BS
                DesiredCapabilities caps = new DesiredCapabilities();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--ignore-ssl-errors=yes");
                options.addArguments("--ignore-certificate-errors");
                options.addArguments("--disable-web-security");
                options.addArguments("--test-type");
                options.addArguments("allow-running-insecure-content");
                caps.setCapability(ChromeOptions.CAPABILITY, options);

                caps.setCapability("browser", "chrome");
                caps.setCapability("browser_version", "100");
                caps.setCapability("os", "windows");
                caps.setCapability("os_version", "11");
                caps.setCapability("seleniumVersion", "4.2.2");
                caps.setCapability("project", "BAT");
                driver = new RemoteWebDriver(new URL("https://batbsauto_IG7R8rDiiDf:vNhrigLoLtzizHEKhTti@hub-cloud.browserstack.com/wd/hub"), caps);
                driver.manage().window().maximize();
                break;
            case "phantomjs":
                phantomJSDriverOSPathConstructor();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "gridchrome":
                System.out.println("Grid setup to go here");
                caps = DesiredCapabilities.chrome();
                caps.setCapability("version","");
                caps.setCapability("platform","LINUX");
                driver = new RemoteWebDriver(new URL("http://localhost:4446/wd/hub"),caps);
                break;
            case "gridfirefox":
                System.out.println("Grid setup to go here");
                caps = DesiredCapabilities.firefox();
                caps.setCapability("version","");
                caps.setCapability("platform","LINUX");
                driver = new RemoteWebDriver(new URL("http://localhost:4446/wd/hub"),caps);
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
            System.out.println("\n ************************************************** \n");
            System.out.println("\nOriginal Driver instance ... launching : " + driverType);
            System.out.println("\n ************************************************** \n");
        } else {
            System.out.println("\n ************************************************** \n");
            System.out.println("\nCannot launch another driver instance - already assigned to : " + driverType);
            System.out.println("\n ************************************************** \n");
        }
    }

    public void CloseWebDriver(){

        //driver = null;
    }

}


