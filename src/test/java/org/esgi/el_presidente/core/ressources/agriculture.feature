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
        When I increase size by <increase size>
        Then The size should be <intermediate size>
        When I decrease size by <decrease size>
        Then The size should be <final size>

        Examples:
            | intial size | increase size | intermediate size | decrease size | final size |
            | 0           | 10            | 10                | 5             | 5          |
            | 12          | 8             | 20                | 15            | 5          |
            | 43          | 20            | 63                | 3             | 60         |
            | 73          | 10            | 83                | 80            | 3          |
            | 100         | 0             | 100               | 47            | 53         |