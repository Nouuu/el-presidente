package org.esgi.el_presidente.core.ressources;

import static org.junit.Assert.assertEquals;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class IndustrySteps {
  private double islandPart;
  private double coefficient;
  private double moneyProduction;
  private Industry indsutry;

  @Given("The industry represente {double} of the island")
  public void setIslandPart(double islandPart) {
    this.islandPart = islandPart;
  }

  @And("The coefficient is {double}")
  public void setCoeff(double coeff) {
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

  @Then("the result should be {double}")
  public void testMoneyProduction(double expectedResult) {
    assertEquals(expectedResult, moneyProduction, 0.001);
  }
}