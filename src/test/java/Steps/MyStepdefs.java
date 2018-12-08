package Steps;

import HelperClasses.BaseClass;
import HelperClasses.ReadFrom;
import HelperClasses.WebDriver.WebDriverTypeConfig;
import Pages.ToolsQAAutomationPracticePage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertTrue;

public class MyStepdefs extends BaseClass{

    String HomePageTitle = ReadFrom.propertiesFile("defaultSetupProperties","TescoHomePageTitle");

   @After
    public void tearDown(){
        System.out.println("Test Completed, quiting WebDrier Instance");
        WebDriverTypeConfig.driver.quit();
    }

    @Given("^User Navigaties to Tesco home page$")
    public void userNavigatiesToHomePage() throws Throwable {
        startDriver();
        visit(baseUrl);
    }

    @Then("^User clicks on close cookie policy button")
    public void userClicksCloseCookiePoliceButton() throws Exception {
        webDriverPerform("click",By.className("announcement-close-icon"));
        //click(By.className("announcement-close-icon"));
    }


    @Then("^Check the homePage title is as expected$")
    public void getAndComparePageTitle(){
        //System.out.println(HomePageTitle);
        // TODO will implement HomePage obj here as POC
        assertTrue("Page title not as defined expected result"+ WebDriverTypeConfig.driver.getTitle(),HomePageTitle.equals(WebDriverTypeConfig.driver.getTitle()));
    }

    @When("^User navigates to (.*)$")
    public void NavToSite(String site) throws MalformedURLException {
       startDriver();
       visit(site);
    }

    @Test @Then("^the title should be \"(.*)\"")
    public void theTitleShouldBe(String text) throws Throwable {
        WebDriverWait wait = new WebDriverWait(WebDriverTypeConfig.driver,10);
        wait.until(ExpectedConditions.titleIs(text));
        Assert.assertEquals(WebDriverTypeConfig.driver.getTitle(), text);
    }

    @And("^close down webDriver Gracefully")
    public void quiteWebDriver(){
        System.out.println("Closing down webDriver");
        System.out.println("...................");
        closeDownWebDriver();
    }

    private void waitForClickableElementAndClick(String elementName) {
        WebElement clickableElement = find(By.className(elementName)); // "announcement-close-icon"
        WebDriverWait wait = new WebDriverWait(WebDriverTypeConfig.driver,10);
        wait.until(ExpectedConditions.elementToBeClickable(clickableElement)).click();
    }

    @Test
    @Given("^Test to return the HTTP responce of the server$")
    public void RequestHTTPResponceCode() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Getting HTTP response code method");
        System.out.println(getResponseCode(baseUrl));
    }

    @Given("^User enters correct password details$")
    public void user_enters_correct_password_details() throws Throwable {
        System.out.println("User enters correct password details method");
        // Write code here that turns the phrase above into concrete actions
    }

    @When("^User clicks the login button$")
    public void user_clicks_the_login_button() throws Throwable {
        System.out.println("User Clicks login button method");
        // Write code here that turns the phrase above into concrete actions
    }

    @Then("^User is succesfully logged in$")
    public void user_is_succesfully_logged_in() throws Throwable {
        System.out.println("User is successfully logged in method.. ");
    }

    @And("Enters text '(.*)' to the firstname field")
    public void enterTextToFieldFirstName(String firstName) throws InterruptedException {
         ToolsQAAutomationPracticePage.enterFirstName(firstName);
    }

    @And("Enters text '(.*)' to the surname field")
    public void enterTextToFieldSurName(String lastName) throws InterruptedException {
          ToolsQAAutomationPracticePage.enterLastName(lastName);
    }

    @When("^Selects '(.*)' from continents drop down$")
    public void selectContinent(String continent) throws Exception {
       ToolsQAAutomationPracticePage.selectContinent(continent);
    }

    @And("^Searches for (.*)$")
    public void searchesForProduct(String product) {
        find(By.cssSelector("input.input-text-box")).sendKeys(product);
        find(By.cssSelector("button.search-icon-button")).click();
    }

    @Then("^the user is landed on the search results page$")
    public void theUserIsLandedOnTheSearchResultsPage() {
       System.out.println("This is not yet done");
    }
}
