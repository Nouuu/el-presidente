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
            | 12               | 40          | 480    |
            | 43               | 10          | 430    |
            | 73               | 40          | 2920   |
            | 100              | 98          | 9800   |

    ## Island
    Scenario Outline: Increase and decrease size of the island
        Given A Agriculture of size <intial size>
        When I set size to <size1>
        Then The size should be <size1>
        When I set size to <size2>
        Then The size should be <size2>

        Examples:
            | intial size | size1 | size2 |
            | 0           | 10    | 45    |
            | 12          | 8     | 18    |
            | 43          | 20    | 21    |
            | 73          | 10    | 58    |
            | 100         | 0     | 96    |