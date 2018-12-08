Feature: Adrian's learning

  Scenario Outline: Search for a product and check the search results
    When User navigates to <url>
    And Enters text '<firstName>' to the firstname field
    And Enters text '<surName>' to the surname field
    And Selects '<continent>' from continents drop down
    And close down webDriver Gracefully
    Examples:
      |url                                              | firstName  |  surName  | continent |
      | http://toolsqa.com/automation-practice-form/    | Adrian     |  Bossman  | Europe    |

