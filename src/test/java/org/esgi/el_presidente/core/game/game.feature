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

  Scenario: test event effect
    Given a game with test scenario in easy
    When i go to the next turn
    Then the current event should be "eventTest"
    When i trigger eventTest effects
    Then test event action

  Scenario: End of year cost
    Given a game with test scenario in easy
    When i go to the next turn
    When i go to the next turn
    When i go to the next turn
    When i trigger end of year cost
    Then the population must have 0 members and 0 food
    Then it's lose

  Scenario: End of year add partisans
    Given a game with many food
    When i go to the next turn
    When i go to the next turn
    When i go to the next turn
    When i trigger end of year ressource
    When i trigger end of year cost
    Then the population must have 25 members and 500 food
    Then it's not lose

  Scenario: test food price
    Given a game with test scenario in easy
    When i get food price
    Then the food price should be 8

  Scenario: end of scenario
    Given a game with test scenario in easy
    Then it's not the end of scenario
    When i go to the next turn
    When i go to the next turn
    Then it's the end of scenario


  Scenario: End of year benefit
    Given a game with test scenario in easy
    When i go to the next turn
    When i go to the next turn
    Then it's not lose
    When i buy 150 food
    When i go to the next turn
    When i trigger end of year cost
    Then the population must have 38 members and 0 food

  Scenario: is lost
    Given a game with test scenario in easy
    Then it's not lose
    When i go to the next turn
    When i go to the next turn
    When i go to the next turn
    When i go to the next turn
    When i trigger end of year cost
    Then it's lose


  Scenario: End of year money production
    Given a game with test scenario in easy
    When i go to the next turn
    When i go to the next turn
    When i go to the next turn
    When i go to the next turn
    When i get the end of year money production
    Then the money produced in a year should be 150

  Scenario: End of year food production
    Given a game with test scenario in easy
    When i go to the next turn
    When i go to the next turn
    When i go to the next turn
    When i go to the next turn
    When i get the end of year food production
    Then the food produced in a year should be 400

  Scenario: test difficulty
    Given a game with test scenario in easy
    Then The difficulty is easy

  Scenario: test scenario
    Given a game with test scenario in easy
    Then The scenario is test scenario

  Scenario: test satisfaction limit
    Given a game with test scenario in easy
    Then The satisfaction limit is 10


  Scenario: buy food fail
    Given a game with test scenario in easy
    When i buy 15000 food it should print an error
    When i go to the next turn
    When i go to the next turn
    When i go to the next turn
    When i go to the next turn
    When i trigger end of year ressource