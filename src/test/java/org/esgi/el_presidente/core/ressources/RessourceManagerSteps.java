package org.esgi.el_presidente.core.ressources;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.esgi.el_presidente.core.factions.Faction;
import org.esgi.el_presidente.core.factions.FactionType;
import org.esgi.el_presidente.core.game.Difficulty;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RessourceManagerSteps {
  private int found;
  private int foodReservies;
  private int bribeCost;
  private int foodPrice;
  private int foodProduced;
  private int moneyProduced;
  private Faction loyalist;
  private Faction faction;
  private RessourceManager manager;
  private Agriculture agriculture = new Agriculture(20, 40);
  private Industry industry = new Industry(20, 10);

  @Given("A basic Ressource Manager")
  public void given_basic_ressource_manager() {
    loyalist = new Faction(FactionType.loyalist, 100, 10);
    manager = new RessourceManager(loyalist, 0, 10, agriculture, industry, Difficulty.MEDIUM);
  }

  @Given("A ressource manager with {int} % agriculture and {int} % industry")
  public void given_a_ressource_manager_with_agriculture_and_industry(int agriculturePart, int industryPart) {
    loyalist = new Faction(FactionType.loyalist, 100, 10);
    Agriculture agriculture = new Agriculture(agriculturePart, 40);
    Industry industry = new Industry(industryPart, 10);

    manager = new RessourceManager(loyalist, 100, 10, agriculture, industry, Difficulty.MEDIUM);
  }

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

  // WHEN

  @When("I create Ressource Manager")
  public void createRessourceManager() {
    manager = new RessourceManager(loyalist, found, foodReservies, agriculture, industry, Difficulty.MEDIUM);
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
      manager.updateSizeOfAgriculture(additionalSize);
    } else if (segment.equals("industry")) {
      manager.updateSizeOfIndustry(additionalSize);
    }
  }

  @When("I try to grow {string} by {int} it should throw an error")
  public void when_i_increase_size_it_should_throw_and_error(String segment, int additionalSize) {
    try {
      if (segment.equals("agriculture")) {
        manager.updateSizeOfAgriculture(additionalSize);
      } else if (segment.equals("industry")) {
        manager.updateSizeOfIndustry(additionalSize);
      }
      fail("error should throw an error");
    } catch (Exception e) {
      return;
    }
  }

  @When("I trigger a money action with {int} action")
  public void when_i_trigger_money_action(int financeEffect) {
    manager.handleMoneyAction(financeEffect);
  }

  @When("I trigger a food action with {int} action")
  public void when_i_trigger_food_action(int foodEffect) {
    manager.handleFoodAction(foodEffect);
  }

  @When("I get bribe cost for {int} peaples")
  public void when_i_get_bribe_cost(int partisansToBribe) {
    bribeCost = manager.getBrideCost(partisansToBribe);
  }

  @When("I get food price")
  public void when_i_get_foodPrice() {
    foodPrice = manager.getFoodPrice();
  }

  @When("I add {int} % to the size of agriculture")
  public void when_i_get_max_size_of_agriculture(int sizeToAdd) {
    manager.updateSizeOfAgriculture(sizeToAdd);
  }

  @When("I add {int} % to the size of industry")
  public void when_i_get_max_size_of_industry(int sizeToAdd) {
    manager.updateSizeOfIndustry(sizeToAdd);
  }

  @When("I trigger end of year actions")
  public void when_i_trigger_end_of_year_actions() {
    manager.triggerEndOfYearAction();
  }

  @When("i buy partisans of loyalist it should throw an error")
  public void when_i_buy_bribe_of_loyalist() {
    try {
      manager.buyBribe(loyalist);
      fail("Buy loyalist sould fail");
    } catch (Exception e) {
      return;
    }
  }

  @When("I get the end of year food production")
  public void when_i_get_end_of_year_food_production() {
    foodProduced = manager.getEndOfYearFoodProduction();
  }

  @When("I get the end of year money production")
  public void when_i_get_end_of_year_money_production() {
    moneyProduced = manager.getEndOfYearMoneyProduction();
  }

  // THEN

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
    assertEquals(expectedSize, manager.getAgriculturePart());
  }

  @Then("The Industry segment should be {int}")
  public void test_industry_size(int expectedSize) {
    assertEquals(expectedSize, manager.getIndustryPart());
  }

  @Then("The bribe cost should be {int}")
  public void test_bribe_cost(int expectedBribeCost) {
    assertEquals(expectedBribeCost, bribeCost);
  }

  @Then("The food price should be {int}")
  public void test_foodPrice(int expectedFoodPrice) {
    assertEquals(expectedFoodPrice, foodPrice);
  }

  @Then("The money production should be {int}")
  public void test_money_production(int expectedMoneyProduction) {
    assertEquals(expectedMoneyProduction, manager.getEndOfYearMoneyProduction());
  }

  @Then("The food production should be {int}")
  public void test_food_production(int expectedfoodProduction) {
    assertEquals(expectedfoodProduction, manager.getEndOfYearFoodProduction());
  }

  private void moveNumberOfPartisansTo(Faction faction, int expectedNumberOfPartisans) {
    int numberOfPartisansIwantToAdd = expectedNumberOfPartisans - faction.getPartisans();
    faction.updatePartisans(numberOfPartisansIwantToAdd);
  }

  private void moveSatisfactionTo(Faction faction, int expectedSatisfaction) {
    int numberOfSatisfactionIwantToAdd = expectedSatisfaction - faction.getSatisfaction();
    faction.updateSatisfaction(numberOfSatisfactionIwantToAdd);
  }
}
