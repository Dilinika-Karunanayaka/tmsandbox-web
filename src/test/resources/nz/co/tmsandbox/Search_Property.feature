Feature: Search Property

  Scenario: Finding some properties listed
    Given I am on the home page
    When I search for "Property"
    Then the all categories page title should start with "property"