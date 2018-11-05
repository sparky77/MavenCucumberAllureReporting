@regAll
Feature: BasicTescoPOCTests
  This feature will perform basic POC tests against the Tesco website

  Background: #This is called before every test
    Given User Navigaties to Tesco home page

  Scenario: Navigate to the Tesco Website and assert the homePage title
    Then Check the homePage title is as expected
    And close down webDriver Gracefully

  Scenario: Navigate tot he Tseco Website and confirm the HTTP responce is 200
    Then Test to return the HTTP responce of the server
    And close down webDriver Gracefully

    Scenario: RestAssured GET request - SWAPI
      Then Test Rest Assured - GET LUKE SKYWALKER

  Scenario: Guest users visits Tescos page and clicks on the x Cookie icon
    Then User clicks on close cookie policy button
    And close down webDriver Gracefully

    Scenario Outline: Validate title for page is correct
      When User navigates to "<url>"
      Then the title should be "<title>"
      And close down webDriver Gracefully

      Examples:
        |url                                              | title                                                                   |
        | http://www.tesco.com                            | Tesco :: Online Groceries, Banking & Mobile Phones                      |
        | https://www.google.co.uk/                       | Google                                                                  |
        | https://www.tesco.com/direct/gaming/            | Gaming - Tesco Groceries                                                |





