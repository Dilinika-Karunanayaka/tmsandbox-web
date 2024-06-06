Feature: Search Jobs

  Scenario: Finding some jobs listed
    Given I am on the home page
    When I search for "Jobs"
    Then the all categories page title should start with "jobs"