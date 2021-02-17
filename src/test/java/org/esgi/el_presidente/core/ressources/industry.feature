Feature: Industry

  Industrialization represents the percentage of the island dedicated to industry.
  Industry generates money at the end of each year.

  Scenario Outline: Yearly production of money
    Given The industry represente <islandPart> of the island
    And The coefficient is <coefficient>
    When I instantiate Industry
    And I calculate the yearly production of money
    Then the result should be <result>

    Examples:
      | islandPart | coefficient | result |
      | 18         | 10          | 180    |
      | 53         | 15          | 795    |
      | 56         | 45          | 2520   |
      | 100        | 10          | 1000   |
      | 0          | 10          | 0      |

  ## Island
  Scenario Outline: Increase and decrease size of the island
    Given A Industry of size <intial size>
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
      | 10          | 0             | 10                | 10            | 0          |