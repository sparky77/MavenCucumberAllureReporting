package HelperClasses.WebDriver;

import java.net.MalformedURLException;

public  class WebDriverLauncher extends WebDriverTypeConfig {
    public WebDriverLauncher() throws MalformedURLException {
        lauchDriverType(driverType);
    }

}
