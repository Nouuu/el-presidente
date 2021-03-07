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


  Scenario: test yeat count
    Given a time manager
    Then the current year is 0
    When i go to next season
    Then the current year is 0
    When i go to next season
    When i go to next season
    Then the current year is 0
    When i go to next season
    Then the current year is 1
    When i go to next season
    When i go to next season
    When i go to next season
    When i go to next season
    When i go to next season
    Then the current year is 2


  Scenario: test is the end of the year
    Given a time manager
    When i go to next season
    Then it should not be the end of the year

  Scenario: test is the end of the year
    Given a time manager
    When i go to next season
    When i go to next season
    When i go to next season
    Then it should be the end of the year