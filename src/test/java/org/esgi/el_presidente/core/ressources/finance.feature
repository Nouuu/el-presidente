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
    When I want to buy <units of food desired>
    Then the value return should be <units of food desired>
    And The amount in coffers should be <new amount>

    Examples:
      | money | units of food desired | new amount |
      | 0.0   | 0.0                   | 0.0        |
      | 12.0  | 1.0                   | 4.0        |
      | 120.0 | 4.0                   | 88.0       |
      | 8.0   | 1.0                   | 0.0        |
      | 32.0  | 4.0                   | 0.0        |