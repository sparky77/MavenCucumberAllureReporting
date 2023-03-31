package Pages;

import HelperClasses.BaseClass;
import HelperClasses.ENUM.SeleniumActionTypes;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.By;

@Getter
public class ToolsQAFormPage extends BaseClass {
    public By firstNameInputBox = By.name("firstname");
    public By secondNameInputField = By.name("lastname");
    public By ByDropDownName = By.id("continents");


}
