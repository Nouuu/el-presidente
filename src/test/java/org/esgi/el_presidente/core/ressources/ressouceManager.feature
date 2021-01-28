Feature: Ressource Manager

  The ressource Manager is the agregator of ressource

  Background: I init the faction
    Given I init the factions


  Scenario Outline: Buy food
    Given I have <money> €
    And I have <food> food
    When I create Ressource Manager
    And I buy <amount of food> food
    Then my food reserves is equal to <new food reserves>
    And my finacial ressources are of <new money>

    Examples:
      | money  | food | amount of food | new food reserves | new money |
      | 1200.0 | 4    | 7              | 11                | 1144.0    |
      | 8.0    | 0    | 1              | 1                 | 0.0       |
      | 80.0   | 5    | 10             | 15                | 0.0       |
      | 34.0   | 2    | 3              | 5                 | 10.0      |


  Scenario: Buy food without the found
    Given I have 0 €
    And I have 0 food
    When I buy 1 food it should throw an error


  Scenario Outline: Buy bribe
    Given I have <money> €
    And I have <partisans> partisans in <faction> faction with <satisfaction> satisfaction
    And The loyalist satifaction is <current loyalist satisfaction>
    When I buy <amount of partisans> partisans
    Then The satisfaction of <faction> sould be <new satifaction>
    And My finacial ressources are of <new money>
    And The Loyalist satisfaction should be <new loyalist satifaction>

    Examples:
      | money | partisans | faction     | satisfaction | current loyalist satisfaction | amount of partisans | new satifaction | new money | new loyalist satifaction |
      | 12000 | 10        | écologistes | 60           | 100                           | 10                  | 60              | 1000      | 80                       |

  Scenario Outline: Grow segment
    Given The Agriculture segment represent <size of agricutlure>
    And The Industrie segment represent <size of industry>
    When I grow <segment>
    Then The Agriculture segment should be <new size of agriculture>
    And The Industry segment should be <new size of Industry>

  Scenario: Over grow segment
    Given The Agriculture segment represent <size of agricutlure>
    And The Industrie segment represent <size of industry>
    When I try to grow <segment> it should throw an error