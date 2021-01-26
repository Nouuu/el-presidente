Feature: Ressource Manager

  The ressource Manager is the agregator of ressource

  Scenario Outline: Buy food
    Given I have <money> $
    And I have <food>
    When I buy <amount of food> food
    Then my food reserves is equal to <new food reserves>
    And my finacial ressources are of <new money>

  Scenario Outline: Buy bribe
    Given I have <money> $
    And I have <partisans>
    And the loyalist satifaction is <current loyalist satisfaction>
    When I buy <amount of partisans> food
    Then The satisfaction sould be <new satifaction>
    And My finacial ressources are of <new money>
    And The Loyalist satisfaction should be <new loyalist satifaction>

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