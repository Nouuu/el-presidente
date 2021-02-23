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
      | 0     | 0                     | 0          |
      | 12    | 1                     | 4          |
      | 120   | 4                     | 88         |
      | 8     | 1                     | 0          |
      | 32    | 4                     | 0          |


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
      | 0     | 0         | 0          |
      | 19    | 1         | 4          |
      | 120   | 4         | 60         |
      | 15    | 1         | 0          |
      | 480   | 4         | 420        |
      | 525   | 35        | 0          |


  Scenario Outline: Add money
    Given I have <inital banking> in coffers
    When I add <investment> €
    And I look in coffers
    Then The amount in coffers should be <expected banking>
    Examples:
      | inital banking | investment | expected banking |
      | 2021           | 40         | 2061             |
      | 0              | 19         | 19               |
      | 50             | 47         | 97               |
      | 50             | -47        | 3                |