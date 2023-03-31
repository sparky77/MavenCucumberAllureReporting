#@regAll
Feature: APIPOCTests
  This feature will perform basic API POC tests


  Scenario: Navigate to a website and confirm the HTTP responce is 200
    Then Test to return the HTTP response of the server at location 'https://www.tesco.com'

  Scenario: RestAssured GET request - Cookies
    Then Rest Assured - Cookie extraction

    Scenario: RestA get single cookie detailed information
    Then Rest Assured - Single Cookie extraction

  Scenario: RestAssured GET request - SWAPI
    Then Rest Assured - Assert LUKE SKYWALKER the name data returned

    @Test
  Scenario: Rest Assured - return all information
    Then Rest Assured - return all information

  Scenario: Rest Assured - return all information - using JasonPath
    Then Rest Assured - return all information using JasonPath

  Scenario: Rest Assured - return all information - using JasonPath and Person class
    Then Rest Assured - JasonPath and Person class

  Scenario: Rest Assured - basic auth
    Then Rest Assured basic Authentication

  Scenario: Rest Assured - basic auth PreemptiveBasicAuthScheme
    Then Rest Assured basic Authentication preemtiveBasicAuth





