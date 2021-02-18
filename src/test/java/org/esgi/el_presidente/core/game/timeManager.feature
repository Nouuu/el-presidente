Feature: Time Managment

  Scenario: test end of year
    Given a time manager
    Then the season is "printemps"
    And the season count is 0
    When i go to next season
    Then the season count is 1
    And the current year is 0
    And the season is "été"
    When i go to next season
    Then the season count is 2
    And the season is "automne"
    When i go to next season
    Then the season count is 3
    And the season is "hiver"
    When i go to next season
    Then the season count is 4
    And the season is "printemps"
    And the current year is 1