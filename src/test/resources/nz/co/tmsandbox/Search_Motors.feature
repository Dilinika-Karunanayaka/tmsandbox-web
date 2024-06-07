Feature: Search Motors

  Scenario: Finding some cars listed
    Given I'm on the home page
    When I try to search for "Cars"
    Then the all categories page title should start with "cars"