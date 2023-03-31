#@regAll
Feature: animeddirect POC Tests
  This feature will perform basic POC tests against the animeddirect website

  @regAll
  Background: #This is called before every test
    Given User Navigaties to Animed home page
    #And create person object

  @regAll
  Scenario: Navigate to the Animed Website and assert the homePage title
    Then Check the homePage title is as expected
    #And close down webDriver Gracefully

  #Scenario: Navigate tot he Tseco Website and confirm the HTTP responce is 200
    #Then Test to return the HTTP responce of the server
    #And close down webDriver Gracefully

  Scenario: Guest users visits Tescos page and clicks on the x Cookie icon
    Then User clicks on close cookie policy button
    #And close down webDriver Gracefully

    Scenario Outline: Validate title for page is correct
      When User navigates to "<url>"
      Then the title should be "<title>"
      #And close down webDriver Gracefully

      Examples:
        |url                                              | title                                                                   |
        | http://www.tesco.com                            | Tesco - Supermarkets | Online Groceries, Clubcard & Recipes                      |
        #| https://www.google.co.uk/                       | Google                                                                  |
        #| https://www.tesco.com/direct/gaming/            | Gaming - Tesco Groceries                                                |





