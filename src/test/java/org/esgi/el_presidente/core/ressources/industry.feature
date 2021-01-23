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