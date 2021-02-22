Feature: Game

  Scenario: test game in scenario
    Given a game with test scenario in easy
    When i check end of year
    Then it's not the end of the year
    And it's not lost
    And the current season is "printemps"
    When i go to the next turn
    And the current event should be "eventTest"
    And the current season is "été"


  Scenario: calculate food impact
    Given a game with test scenario in easy
    Then the total amount of partisan is 80
    And the food impact is 320

# TODO test buy food test endOfYearCost EventEffect