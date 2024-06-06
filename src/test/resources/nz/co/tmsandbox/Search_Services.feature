Feature: Search Services

  Scenario: Finding some services listed
    Given I am on the home page
    When I search for "Services"
    Then the all categories page title should start with "services"