package Pages;

import HelperClasses.BaseClass;
import org.openqa.selenium.By;


public class ToolsQAAutomationPracticePage {

    public static void enterFirstName(String FirstName) throws InterruptedException {
        By firstNameInputBox = By.name("firstname");
        BaseClass.find(firstNameInputBox).sendKeys(FirstName);
        Thread.sleep(3000);
        System.out.println("\nValue in firstname input box is : " + BaseClass.find(firstNameInputBox).getAttribute("value"));
    }

}
