Feature: Adrian's learning

  Scenario Outline: Search for a product and check the search results
    When User navigates to <url>
    And Enter text '<firstName>' to the firstname field
    And Enter text '<surName>' to the surname field
    #Then the user is landed on the search results page
    And close down webDriver Gracefully
    Examples:
      |url                                              | firstName  |  surName  |
      | http://toolsqa.com/automation-practice-form/    | Adrian     |  Bossman  |

