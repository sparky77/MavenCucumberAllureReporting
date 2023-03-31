package Steps;

import HelperClasses.BaseClass;
import HelperClasses.ENUM.SeleniumActionTypes;
import Pages.ToolsQAFormPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import java.net.MalformedURLException;

public class ToolQAFormPageSteps extends BaseClass{

    ToolsQAFormPage toolsQAFormPage;

    public ToolQAFormPageSteps(ToolsQAFormPage toolsQAFormPage) {
        this.toolsQAFormPage = toolsQAFormPage;
    }

    @When("^User navigates to (.*)$")
    public void NavToSite(String site) throws MalformedURLException {
       visit(site);
    }

    @And("^close down webDriver Gracefully")
    public void quiteWebDriver(){
        closeDownWebDriver();
    }

    @And("Enters text '(.*)' to the firstname field")
    public void enterTextToFieldFirstName(String firstName) throws Exception {
        ToolsQAFormPage.webDriverPerform(SeleniumActionTypes.enterText,toolsQAFormPage.firstNameInputBox,firstName);
    }

    @And("Enters text '(.*)' to the surname field")
    public void enterTextToFieldSurName(String lastName) throws Exception {
        ToolsQAFormPage.webDriverPerform(SeleniumActionTypes.enterText,toolsQAFormPage.secondNameInputField,lastName);
    }

    @When("^Selects '(.*)' from continents drop down$")
    public void selectContinent(String continent) throws Exception {
        ToolsQAFormPage.webDriverPerform(SeleniumActionTypes.SelectFromDropDown,toolsQAFormPage.ByDropDownName,continent);
    }

}
