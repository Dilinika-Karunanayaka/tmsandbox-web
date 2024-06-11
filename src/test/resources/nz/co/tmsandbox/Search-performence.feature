Feature: Search Performance Test

  Scenario: Measure search results performance
    Given I'm on the home page
    When I perform a search for "Home"
    Then I should see search results "Home" less than 5000 milliseconds

  Scenario: Measure search suggestion dropdown list's performance
    Given I'm on the home page
    When I type "Jobs" in the search box
    Then I should see search suggestions list in 5000 milliseconds