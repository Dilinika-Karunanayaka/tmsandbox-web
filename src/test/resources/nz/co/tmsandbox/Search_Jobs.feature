Feature: Search Jobs

  Scenario: Finding some jobs listed
    Given I'm on the home page
    When I try to search "Jobs"
    Then the all categories page title should start with "jobs"