Feature: Search Marketplace

  Scenario: Finding some marketplace listed
    Given I am on the home page
    When I search for "Marketplace"
    Then the all categories page title should start with "marketplace"