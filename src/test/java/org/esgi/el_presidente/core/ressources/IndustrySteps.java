package org.esgi.el_presidente.core.ressources;

import static org.junit.Assert.assertEquals;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class IndustrySteps {
  private int islandPart;
  private int coefficient;
  private int moneyProduction;
  private Industry indsutry;

  @Given("The industry represente {int} of the island")
  public void setIslandPart(int islandPart) {
    this.islandPart = islandPart;
  }

  @And("The coefficient is {int}")
  public void setCoeff(int coeff) {
    this.coefficient = coeff;
  }

  @When("I instantiate Industry")
  public void createIdustry() {
    indsutry = new Industry(islandPart, coefficient);
  }

  @And("I calculate the yearly production of money")
  public void calculateYearlyProduction() {
    moneyProduction = indsutry.yearlyProductionOfMoney();
  }

  @Then("the result should be {int}")
  public void testMoneyProduction(int expectedResult) {
    assertEquals(expectedResult, moneyProduction, 0.001);
  }
}