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
      | money | food | amount of food | new food reserves | new money |
      | 1200  | 4    | 7              | 11                | 1144      |
      | 8     | 0    | 1              | 1                 | 0         |
      | 80    | 5    | 10             | 15                | 0         |
      | 34    | 2    | 3              | 5                 | 10        |


  Scenario: Buy food without the found
    Given I have 0 €
    And I have 0 food
    When I create Ressource Manager
    And I buy 1 food it should throw an error


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
      | money | partisans | faction     | satisfaction | loyalist satisfaction | amount of partisans | new satifaction | new money | new loyalist satifaction |
      | 12000 | 10        | 'ecologist' | 60           | 100                   | 10                  | 70              | 11850     | 85                       |


  Scenario: Buy bribe without the found
    Given I have 0 €
    And I have 10 partisans in "capitalist" faction with 20 satisfaction
    And The loyalist have 60 satifaction
    When I create Ressource Manager
    And I buy partisans of this faction it sould throw


  Scenario Outline: Grow segment
    Given The Agriculture segment represent <size of agricutlure>
    And The Industrie segment represent <size of industry>
    When I create Ressource Manager
    And I increase the size of "<segment>" by <additional size> percent
    Then The Agriculture segment should be <new size of agriculture>
    And The Industry segment should be <new size of industry>

    Examples:
      | size of agricutlure | size of industry | segment     | additional size | new size of agriculture | new size of industry |
      | 10                  | 10               | agriculture | 20              | 30                      | 10                   |
      | 10                  | 10               | industry    | 20              | 10                      | 30                   |
      | 10                  | 80               | industry    | 10              | 10                      | 90                   |
      | 40                  | 40               | agriculture | 20              | 60                      | 40                   |


  Scenario Outline: Over grow segment
    Given The Agriculture segment represent <size of agricutlure>
    And The Industrie segment represent <size of industry>
    When I try to grow "<segment>" by <additional size> it should throw an error

    Examples:
      | size of agricutlure | size of industry | segment     | additional size |
      | 50                  | 50               | agriculture | 20              |
      | 70                  | 20               | agriculture | 20              |
      | 70                  | 20               | industry    | 20              |

  Scenario Outline: trigger Money Action
    Given I have <inital money> €
    When I create Ressource Manager
    And I trigger a money action with <effect> action
    Then My finacial ressources are of <new money>

    Examples:
      | inital money | effect | new money |
      | 200          | 20     | 220       |
      | 200          | -20    | 180       |
      | 200          | -200   | 0         |
      | 0            | 20     | 20        |

  Scenario Outline: trigger food Action
    Given I have <inital food> food
    When I create Ressource Manager
    And I trigger a food action with <effect> action
    Then My food reserves is equal to <new food>

    Examples:
      | inital food | effect | new food |
      | 20          | 4      | 24       |
      | 200         | -20    | 180      |
      | 2           | -2     | 0        |
      | 0           | 2      | 2        |

  Scenario: Get bribe cost
    Given A basic Ressource Manager
    When I get bribe cost for 10 peaples
    Then The bribe cost should be 150

  Scenario: get max size industry / agricutlure
    Given A ressource manager with 20 % agriculture and 40 % industry
    When I add 20 % to the size of agriculture
    Then The Agriculture segment should be 40
    When I add 40 % to the size of industry
    Then The Industry segment should be 60


  Scenario: get food price
    Given A basic Ressource Manager
    When I get food price
    Then The food price should be 8

  Scenario: Trigger end of year action
    Given A basic Ressource Manager
    When I trigger end of year actions
    Then My food reserves is equal to 810
    And My finacial ressources are of 200

  Scenario: Buy bribe for loyalist
    Given A basic Ressource Manager
    When i buy partisans of loyalist it should throw an error

  Scenario: Get end of year food production
    Given A basic Ressource Manager
    When I get the end of year food production
    Then The food production should be 800

  Scenario: Get end of year money production
    Given A basic Ressource Manager
    When I get the end of year money production
    Then The money production should be 200
