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
    And The food result should be <units of food desired>

    Examples:
      | money | units of food desired | new amount |
      | 0.0   | 0                     | 0.0        |
      | 12.0  | 1                     | 4.0        |
      | 120.0 | 4                     | 88.0       |
      | 8.0   | 1                     | 0.0        |
      | 32.0  | 4                     | 0.0        |


  Scenario Outline: buy bribe
    Given I have <money> in coffers
    When I buy <partisans> partisans
    And I look in coffers
    Then The amount in coffers should be <new amount>
    And The bribe result should be <partisans>

    Examples:
      | money | partisans | new amount |
      | 0.0   | 0         | 0.0        |
      | 19.0  | 1         | 4.0        |
      | 120.0 | 4         | 60.0       |
      | 15.0  | 1         | 0.0        |
      | 480.0 | 4         | 420.0      |
      | 525.0 | 35        | 0.0        |