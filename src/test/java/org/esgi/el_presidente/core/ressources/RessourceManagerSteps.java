package org.esgi.el_presidente.core.ressources;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.esgi.el_presidente.core.factions.Faction;
import org.esgi.el_presidente.core.factions.FactionManager;
import org.esgi.el_presidente.core.factions.FactionType;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RessourceManagerSteps {
  private double found;
  private int foodReservies;
  private FactionManager factionManager = new FactionManager();
  private RessourceManager manager;

  @Given("I init the factions")
  public void initFaction() {
    factionManager.initFactionList(60, 15, 60, 15);
  }

  @Given("I have {double} €")
  public void given_money(double amount) {
    found = amount;
  }

  @Given("I have {int} food")
  public void given_i_have_food(int foodReserves) {
    foodReservies = foodReserves;
  }

  @Given("I have {int} partisans in {string} faction with {int} satisfaciton")
  public void given_i_have_partisans(int partisans, String factionStr, int satisfaction) {
    FactionType factionType = FactionType.valueOf(factionStr);
    Faction faction = factionManager.getFaction(factionType);
    moveNumberOfPartisansTo(faction, partisans);
    moveSatisfactionTo(faction, satisfaction);
  }

  @Given("The loyalist satifaction is {int}")
  public void given_loyalist_are(int satisfaction) {
    Faction loyalist = factionManager.getFaction(FactionType.loyalist);
    moveSatisfactionTo(loyalist, satisfaction);
  }

  @When("I create Ressource Manager")
  public void createRessourceManager() {
    manager = new RessourceManager(found, foodReservies);
  }

  @When("I buy {int} food")
  public void buy_food(int unitOfFoodBuy) {
    try {
      manager.buyFood(unitOfFoodBuy);
    } catch (Exception e) {
      fail("Buy food fail");
      System.err.println(e);
    }
  }

  @When("I buy {int} food it should throw an error")
  public void buy_food_should_throw_error(int unitOfFoodBuy) {
    try {
      manager.buyFood(unitOfFoodBuy);
      fail("Buy food sould fail");
    } catch (Exception e) {
      return;
    }
  }

  @When("I buy {int} partisans")
  public void buy_bribe(int partisans) {
    try {
      manager.buyBribe(partisans);
    } catch (Exception e) {
      fail("Should not throw exeption");
    }
  }

  @Then("my food reserves is equal to {int}")
  public void test_food_reserves(int expectedFoodReserves) {
    assertEquals(expectedFoodReserves, manager.getFoodReserves());
  }

  @Then("my finacial ressources are of {double}")
  public void test_financial_reserves(double expectedMoney) {
    assertEquals(expectedMoney, manager.getMoney(), 0.001);
  }

  @Then("The satisfaction of {String} sould be {int}")
  public void test_satisfaction(String factionStr, int expectedSatisfaction) {
    FactionType factionType = FactionType.valueOf(factionStr);
    Faction faction = factionManager.getFaction(factionType);

    assertEquals(expectedSatisfaction, faction.getSatisfaction(), 0.001);
  }

  @Then("The Loyalist satisfaction should be {int}")
  public void test_loyalist_satisfaction(int expectedSatisfaction) {
    Faction loyalist = factionManager.getFaction(FactionType.loyalist);

    assertEquals(expectedSatisfaction, loyalist.getSatisfaction(), 0.001);
  }

  private void moveNumberOfPartisansTo(Faction faction, int expectedNumberOfPartisans) {
    int numberOfPartisansIwantToAdd = expectedNumberOfPartisans - faction.getPartisans();
    faction.addPartisans(numberOfPartisansIwantToAdd);
  }

  private void moveSatisfactionTo(Faction faction, int expectedSatisfaction) {
    int numberOfSatisfactionIwantToAdd = expectedSatisfaction - faction.getSatisfaction();
    faction.addSatisfaction(numberOfSatisfactionIwantToAdd);
  }
}
