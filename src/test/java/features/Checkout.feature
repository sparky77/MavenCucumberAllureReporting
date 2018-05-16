Feature: Checkout

  Scenario: Click and collect checkout
    Given User Navigaties to Tesco home page
    And User adds product to basket
    And Logs in with existing credentials
    And Selects click and collect delivery
    And Navigates to checkout
    When Pays for the order using credit card
    Then The user is landed on the order confirmation page