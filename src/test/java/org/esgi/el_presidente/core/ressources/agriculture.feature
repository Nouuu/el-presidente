Feature: Agriculture

    Agriculture generates a number of units of food each year.

    Scenario Outline: Yearly production of food
        Given The agriculture represente <agriculture part>% of the islande
        And The food production coefficient is <coefficient>
        When I instantiate Agriculture
        And I calculate the yearly production of food
        Then The result should be <result>

        Examples:
            | agriculture part | coefficient | result |
            | 12.0             | 40.0        | 480.0  |
            | 43.0             | 10.0        | 430.0  |
            | 73.0             | 40.0        | 2920.0 |
            | 100.0            | 98.0        | 9800.0 |