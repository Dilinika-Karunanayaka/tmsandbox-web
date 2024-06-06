Feature: Search Motors

  Scenario: Finding some cars listed
    Given I am on the home page
    When I search for "Cars"
    Then the all categories page title should start with "cars"