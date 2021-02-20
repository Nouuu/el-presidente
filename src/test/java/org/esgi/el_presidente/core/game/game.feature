Feature: Game

  Scenario: test game in scenario
    Given a game with test scenario in easy
    When i check end of year
    Then it's not the end of the year
    And it's not lost
    When i go to the next turn
    And the current event should be "eventTest"