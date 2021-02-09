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

    ## Island
    Scenario Outline: Increase and decrease size of the island
        Given A Agriculture of size <intial size>
        When I increase size by <increase size>
        Then The size should be <intermediate size>
        When I decrease size by <decrease size>
        Then The size should be <final size>

        Examples:
            | intial size | increase size | intermediate size | decrease size | final size |
            | 0.0         | 10.0          | 10.0              | 5.0           | 5.0        |
            | 12.0        | 8.0           | 20.0              | 15.0          | 5.0        |
            | 43.0        | 20.0          | 63.0              | 3.0           | 60.0       |
            | 73.0        | 10.0          | 83.0              | 80.0          | 3.0        |
            | 100.0       | 0.0           | 100.0             | 47.0          | 53.0       |