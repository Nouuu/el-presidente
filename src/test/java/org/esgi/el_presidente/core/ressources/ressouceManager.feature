Feature: Ressource Manager

  The ressource Manager is the agregator of ressource

  Scenario Outline: Buy food
    Given I have <money> €
    And I have <food> food
    When I create Ressource Manager
    And I buy <amount of food> food
    Then My food reserves is equal to <new food reserves>
    And My finacial ressources are of <new money>

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
    And The loyalist have <loyalist satisfaction> satifaction
    When I create Ressource Manager
    And I buy the partisans of this faction
    Then The satisfaction of the faction sould be <new satifaction>
    And My finacial ressources are of <new money>
    And The Loyalist satisfaction should be <new loyalist satifaction>

    Examples:
      | money   | partisans | faction     | satisfaction | loyalist satisfaction | amount of partisans | new satifaction | new money | new loyalist satifaction |
      | 12000.0 | 10        | 'ecologist' | 60           | 100                   | 10                  | 66              | 11850.0   | 85                       |


  Scenario: Buy bribe without the found
    Given I have 0 €
    And I have 10 partisans in "capitalist" faction with 20 satisfaction
    And The loyalist have 60 satifaction
    When I create Ressource Manager
    And I buy partisans of this faction it sould throw


  Scenario: Buy bribe without loyalist satifaction
    Given I have 1000 €
    And I have 10 partisans in "capitalist" faction with 20 satisfaction
    And The loyalist have 0 satifaction
    When I create Ressource Manager
    And I buy partisans of this faction it sould throw


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