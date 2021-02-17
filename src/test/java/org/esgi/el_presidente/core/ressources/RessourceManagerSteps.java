package org.esgi.el_presidente.core.ressources;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.esgi.el_presidente.core.factions.Faction;
import org.esgi.el_presidente.core.factions.FactionType;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RessourceManagerSteps {
  private int found;
  private int foodReservies;
  private Faction loyalist;
  private Faction faction;
  private RessourceManager manager;
  private Agriculture agriculture = new Agriculture(20, 40);
  private Industry industry = new Industry(20, 10);

  @Given("I have {int} €")
  public void given_money(int amount) {
    found = amount;
  }

  @Given("I have {int} food")
  public void given_i_have_food(int foodReserves) {
    foodReservies = foodReserves;
  }

  @Given("I have {int} partisans in {string} faction with {int} satisfaction")
  public void given_i_have_partisans(int partisans, String factionStr, int satisfaction) {
    FactionType factionType = FactionType.valueOf(factionStr);
    faction = new Faction(factionType, partisans, satisfaction);
    moveNumberOfPartisansTo(faction, partisans);
    moveSatisfactionTo(faction, satisfaction);
  }

  @Given("The loyalist have {int} satifaction")
  public void given_loyalist_are(int satisfaction) {
    loyalist = new Faction(FactionType.loyalist, 100, satisfaction);
    moveSatisfactionTo(loyalist, satisfaction);
  }

  @Given("The Agriculture segment represent {int}")
  public void given_agricuture_of_size(int size) {
    agriculture = new Agriculture(size, 40);
  }

  @Given("The Industrie segment represent {int}")
  public void given_industry_of_size(int size) {
    industry = new Industry(size, 4);
  }

  @When("I create Ressource Manager")
  public void createRessourceManager() {
    manager = new RessourceManager(loyalist, found, foodReservies, agriculture, industry);
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

  @When("I buy the partisans of this faction")
  public void buy_bribe() throws Exception {
    try {
      manager.buyBribe(faction);
    } catch (Exception e) {
      fail("Buy bribe fail");
      System.err.println(e);
    }
  }

  @When("I buy partisans of this faction it sould throw")
  public void buyBribeShouldThrow() {
    try {
      manager.buyBribe(faction);
      fail("It sould throw error");
    } catch (Exception e) {
      return;
    }
  }

  @When("I increase the size of {string} by {int} percent")
  public void when_i_increase_size_of(String segment, int additionalSize) {
    if (segment.equals("agriculture")) {
      manager.increaseSizeOfAgriculture(additionalSize);
    } else if (segment.equals("industry")) {
      manager.increaseSizeOfIndustry(additionalSize);
    }
  }

  @When("I try to grow {string} by {int} it should throw an error")
  public void when_i_increase_size_it_should_throw_and_error(String segment, int additionalSize) {
    try {
      if (segment.equals("agriculture")) {
        manager.increaseSizeOfAgriculture(additionalSize);
      } else if (segment.equals("industry")) {
        manager.increaseSizeOfIndustry(additionalSize);
      }
      fail("error should throw an error");
    } catch (Exception e) {
      return;
    }
  }

  @Then("My food reserves is equal to {int}")
  public void test_food_reserves(int expectedFoodReserves) {
    assertEquals(expectedFoodReserves, manager.getFoodReserves());
  }

  @Then("My finacial ressources are of {int}")
  public void test_financial_reserves(int expectedMoney) {
    assertEquals(expectedMoney, manager.getMoney(), 0.001);
  }

  @Then("The satisfaction of the faction sould be {int}")
  public void test_satisfaction(int expectedSatisfaction) {
    assertEquals(expectedSatisfaction, faction.getSatisfaction(), 0.001);
  }

  @Then("The Loyalist satisfaction should be {int}")
  public void test_loyalist_satisfaction(int expectedSatisfaction) {
    assertEquals(expectedSatisfaction, loyalist.getSatisfaction(), 0.001);
  }

  @Then("The Agriculture segment should be {int}")
  public void test_agriculture_size(int expectedSize) {
    assertEquals(expectedSize, agriculture.getSize(), 0.001);
  }

  @Then("The Industry segment should be {int}")
  public void test_industry_size(int expectedSize) {
    assertEquals(expectedSize, industry.getSize(), 0.001);
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
