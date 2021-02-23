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
    When I set size to <size1>
    Then The size should be <size1>
    When I set size to <size2>
    Then The size should be <size2>

    Examples:
      | intial size | size1 | size2 |
      | 0           | 10    | 48    |
      | 12          | 8     | 47    |
      | 43          | 20    | 2     |
      | 73          | 10    | 25    |
      | 100         | 0     | 100   |
      | 10          | 0     | 85    |