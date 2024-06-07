Feature: Search Property

  Scenario: Finding some properties listed
    Given I'm on the home page
    When I try to search for "Property"
    Then the all categories page title should start with "property"