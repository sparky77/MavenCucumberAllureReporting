Feature: Adrian's learning

  Scenario Outline: Search for a product and check the search results
    When User navigates to <url>
    And Searches for <product>
    Then the user is landed on the search results page
    And close down webDriver Gracefully
    Examples:
      |url                                              | product                    |
      | http://www.tesco.com                            | wine                       |
