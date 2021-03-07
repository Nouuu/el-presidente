package org.esgi.el_presidente.core.ressources;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

public class FinanceSteps {
  private Finances finances;
  private int moneyInCoffers;

  @Given("I have {int} in coffers")
  public void createFinance(int initalMoney) {
    finances = new Finances(initalMoney, 1.0);
  }

  @When("I look in coffers")
  public void look_the_coffers() {
    moneyInCoffers = finances.getMoneyInCoffers();
  }

  @When("I want to buy {int} food")
  public void when_i_buy_food(int unitOfFood) {
    try {
      finances.buyFood(unitOfFood);
    } catch (Exception e) {
      System.err.println(e);
    }
  }

  @When("I buy {int} partisans")
  public void when_i_buy_partisans(int partisans) {
    try {
      finances.buyBribe(partisans);
    } catch (Exception e) {
      System.err.println(e);
    }
  }

  @When("I want to buy {int} food it should fail with error")
  public void when_i_cant_buy_food(int unitsOfFood) {
    try {
      finances.buyFood(unitsOfFood);
      fail("expect to fail");
    } catch (Exception e) {
      return;
    }
  }

  @When("I want to buy {int} bribe it should fail with error")
  public void when_i_cant_bribe_partisans(int partisans) {
    try {
      finances.buyBribe(partisans);
      fail("expect to fail");
    } catch (Exception e) {
      return;
    }
  }

  @When("I add {int} €")
  public void addToCoffers(int moneyImpact) {
    finances.handleMoneyAction(moneyImpact);
  }

  @Then("The amount in coffers should be {int}")
  public void testValueInCoffers(int expectedAmount) {
    assertEquals(expectedAmount, moneyInCoffers, 0.0001);
  }
}
