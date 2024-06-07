Feature: Search Marketplace

  Scenario: Finding some marketplace listed
    Given I'm on the home page
    When I try to search "Marketplace"
    Then the all categories page title should start with "marketplace"