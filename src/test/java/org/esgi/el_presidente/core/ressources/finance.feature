Feature: Finance

  Finance is the money available is the game
  cash can be used to pay for bribes or food

  Scenario: Creation of finances
    Given I have <amount> in coffers
    When I look in coffers
    Then The amount in coffers should be <amount>
    Examples:
      | amount |
      | 10     |
      | 12000  |
      | 99999  |
      | 0      |
      | 5      |


  Scenario Outline: buy some food
    Given I have <money> in coffers
    When I want to buy <units of food desired> food
    And I look in coffers
    Then The amount in coffers should be <new amount>

    Examples:
      | money | units of food desired | new amount |
      | 0.0   | 0                     | 0.0        |
      | 12.0  | 1                     | 4.0        |
      | 120.0 | 4                     | 88.0       |
      | 8.0   | 1                     | 0.0        |
      | 32.0  | 4                     | 0.0        |


  Scenario: Buys food without the necessary money
    Given I have 0 in coffers
    When I want to buy 1 food it should fail with error

  Scenario: Buys bribe without the necessary money
    Given I have 0 in coffers
    When I want to buy 1 bribe it should fail with error

  Scenario Outline: buy bribe
    Given I have <money> in coffers
    When I buy <partisans> partisans
    And I look in coffers
    Then The amount in coffers should be <new amount>

    Examples:
      | money | partisans | new amount |
      | 0.0   | 0         | 0.0        |
      | 19.0  | 1         | 4.0        |
      | 120.0 | 4         | 60.0       |
      | 15.0  | 1         | 0.0        |
      | 480.0 | 4         | 420.0      |
      | 525.0 | 35        | 0.0        |


  Scenario Outline: Add money
    Given I have <inital banking> in coffers
    When I add <investment> €
    And I look in coffers
    Then The amount in coffers should be <expected banking>
    Examples:
      | inital banking | investment | expected banking |
      | 2021.0         | 40.0       | 2061.0           |
      | 0.0            | 19.4       | 19.4             |
      | 50.0           | 47.12      | 97.12            |
      | 50.0           | -47.12     | 2.88             |