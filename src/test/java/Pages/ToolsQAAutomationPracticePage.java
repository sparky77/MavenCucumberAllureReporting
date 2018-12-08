package Pages;

import HelperClasses.BaseClass;
import HelperClasses.ENUM.SeleniumActionTypes;
import org.openqa.selenium.By;


public class ToolsQAAutomationPracticePage {

    public static void enterFirstName(String FirstName) throws InterruptedException {
        By firstNameInputBox = By.name("firstname");
        BaseClass.find(firstNameInputBox).sendKeys(FirstName);
        Thread.sleep(3000);
        System.out.println("\nValue in firstname input box is : " + BaseClass.find(firstNameInputBox).getAttribute("value"));
    }

    public static void enterLastName(String LastName)throws InterruptedException {
        By secondNameInputField = By.name("lastname");
        BaseClass.find(secondNameInputField).sendKeys(LastName);
        Thread.sleep(3000);
        System.out.println("\nValue in surname input box is : " + BaseClass.find(secondNameInputField).getAttribute("value"));
    }

    public static void selectContinent(String continent) throws Exception {
        By ByDropDownName = By.id("continents");
        BaseClass.webDriverPerform(SeleniumActionTypes.SelectFromDropDown,ByDropDownName,"Europe");
        Thread.sleep(3000);
        System.out.println("\nValue selected from Continents is : " + BaseClass.find(ByDropDownName).getAttribute("value"));
    }

}
