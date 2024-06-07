Feature: Search Services

  Scenario: Finding some services listed
    Given I'm on the home page
    When I try to search for "Services"
    Then the all categories page title should start with "services"