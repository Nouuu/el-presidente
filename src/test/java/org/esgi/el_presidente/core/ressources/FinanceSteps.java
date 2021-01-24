package org.esgi.el_presidente.core.ressources;

import static org.junit.Assert.assertEquals;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

public class FinanceSteps {
  private Finances finances;
  private double moneyInCoffers;

  @Given("I have {double} in coffers")
  public void createFinance(double initalMoney) {
    finances = new Finances(initalMoney);
  }

  @When("I look in coffers")
  public void look_the_coffers() {
    moneyInCoffers = finances.getMoneyInCoffers();
  }

  @Then("The amount in coffers should be {double}")
  public void testValueInCoffers(double expectedAmount) {
    assertEquals(expectedAmount, moneyInCoffers, 0.0001);
  }
}
