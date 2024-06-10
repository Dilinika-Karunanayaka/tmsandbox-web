Feature: Search Functionality

  Scenario Outline: Search with valid input
    Given I'm on the home page
    When I try to search for "<Search Input>"
    Then I can see search results for "<Search Input>"

    Examples:
      | Search Input |
      | Home         |

  Scenario Outline: Search with invalid input
    Given  I'm on the home page
    When I try to search "<Search Input>"
    Then I can see the search result for "<Search Input>" and a result count of "0"

    Examples:
      | Search Input |
      | caravan      |

  @WIP
  Scenario: Verify search bar placeholder text
    Given I'm on the home page
    When I check the search text box
    Then I can see "Search all of Trade Me" as a place holder text

  Scenario Outline: Search suggestions relevance
    Given I'm on the home page
    When  I enter "<Search Input>" and wait for the suggestion list to appear
    Then I observe suggestions in the dropdown list relevant to "<Search Input>"

    Examples:
      | Search Input |
      | Home         |
      | Jobs         |

  Scenario Outline: Search with suggested item
    Given I'm on the home page
    When I enter "<Search Input>" and wait for the suggestion list to appear
    Then I choose "<Suggested Text>" from the suggestion list
    And I can see the search results that match "<Suggested Text>"

    Examples:
      | Search Input | Suggested Text   |
      | Home         | home and income  |
      | Jobs         | jobs in auckland |