package Steps;

import HelperClasses.BaseClass;
import HelperClasses.ReadFrom;
import HelperClasses.WebDriver.WebDriverTypeConfig;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.Cookie;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.Map;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class APIStepdefs extends BaseClass{


    @Given("^Test to return the HTTP response of the server at location '(.*)'$")
    public void RequestHTTPResponceCode(String webAddress) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Getting HTTP response code method");
        System.out.println(getResponseCode(webAddress));
        assertEquals("Response code not as expected",getResponseCode(webAddress),200);
    }

    @Then("^Rest Assured - Assert LUKE SKYWALKER the name data returned$")
    public void RestAssuredTest() throws Throwable {
       String baseURL = "http://swapi.co/api/";
       String lukeSW = "people/1/";
       get(baseURL+lukeSW).then().assertThat().body("name",equalTo("Luke Skywalker"));
    }

    @Then("^Rest Assured - return all information$")
    public void RestAssuredReturnAllInformation() {
        Response response = get("https://swapi.dev/api/").andReturn();
        String jsonResponce = response.getBody().asString();
        System.out.println("API response : \n" + jsonResponce);
        assertTrue("Amazing!",true);
    }


    @Then("^Rest Assured - return all information using JasonPath$")
    public void restAssuredReturnAllInformationUsingJasonPath() throws Throwable {
        //String response = RestAssured.get("http://swapi.co/api/people/1/").andReturn().getBody().asString();
        //String json = response.getBody().asString();
        JsonPath jsonPath = new JsonPath(get("http://swapi.co/api/people/1/").andReturn().getBody().asString());
        System.out.println("Getting json path name :" + jsonPath.getString("name"));
        assertEquals("Luke Skywalker",jsonPath.getString("name"));
    }

    @Then("^Rest Assured basic Authentication$")
    public void restAssuredBasicAuthentication() throws Throwable {
        String authAppApi = "http://restapi.demoqa.com/authentication/CheckForAuthentication";
        String username = "ToolsQA";
        String incorrectUserName = "TooksBA";
        String password = "TestPassword";
        //given().auth().preemptive().basic(username, password).when().get(authAppApi).then().statusCode(200).log().all();
        given().auth().preemptive().basic(incorrectUserName, password).when().get(authAppApi).then().statusCode(401).log().all();
    }

    @Then("^Rest Assured basic Authentication preemtiveBasicAuth$")
    public void restAssuredBasicAuthenticationPreemtiveBasicAuth() throws Throwable {
        RestAssured.baseURI = ("http://restapi.demoqa.com/authentication/CheckForAuthentication");
        PreemptiveBasicAuthScheme authScheme = new PreemptiveBasicAuthScheme();
        authScheme.setUserName("ToolsQA");
        authScheme.setPassword("TestPassword");
        RestAssured.authentication = authScheme;
        System.out.println(authScheme.generateAuthToken());
    }

    @Then("^Rest Assured - Cookie extraction$")
    public void restAssuredCookieExtraction() throws Throwable {
        Response response = get("http://www.tesco.co.uk");
        Map<String,String> cookies = response.getCookies();
        for (Map.Entry<String,String> cookie : cookies.entrySet()){
            System.out.println("\nkey : " + cookie.getKey()+ " : value : " + cookie.getValue());
        }
    }

    @Then("^Rest Assured - Single Cookie extraction$")
    public void restAssuredCookieExtractionSingle() throws Throwable {
        Response response = get("http://www.tesco.co.uk");
        Cookie cookies = response.getDetailedCookie("atrc");

        System.out.println("\nCookie has expiry date: "+cookies.hasExpiryDate());
        System.out.println("\nCookie expiry date: "+cookies.getExpiryDate());
        System.out.println("\nCookie value: "+cookies.getValue());
        System.out.println("\nCookie value: "+cookies.getComment());
    }

    private class Person{
        public String name;
        public int mass;
    }

    @Then("^Rest Assured - JasonPath and Person class$")
    public void restAssuredJasonPathAndPersonClass() throws Throwable {
        Response response = get("http://swapi.co/api/people/1/").andReturn();
        String json = response.getBody().asString();

        System.out.println("Testing " + new JsonPath(json).getObject("$",Person.class));

        Person luke = new JsonPath(json).getObject("$",Person.class);
        assertEquals("Luke Skywalker",luke.name);

    }
}
