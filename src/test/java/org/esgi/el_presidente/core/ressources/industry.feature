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
      | 18.0       | 10.0        | 180.0  |
      | 53.0       | 15.0        | 795.0  |
      | 56.0       | 45.0        | 2520.0 |
      | 100.0      | 10.0        | 1000.0 |
      | 0.0        | 10.0        | 0.0    |

  ## Island
  Scenario Outline: Increase and decrease size of the island
    Given A Industry of size <intial size>
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
      | 10.0        | 0.0           | 10.0              | 10.0          | 0.0        |