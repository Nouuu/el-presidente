package org.esgi.el_presidente.core.ressources;

import static org.junit.Assert.assertEquals;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AgricultureSteps {
  private double islandPart;
  private double foodProductionCoefficient;
  private Agriculture agriculture;
  private double production;

  @Given("The agriculture represente {double}% of the islande")
  public void setIslandPart(double islandPart) {
    this.islandPart = islandPart;
  }

  @And("The food production coefficient is {double}")
  public void setCoefficient(double coefficient) {
    this.foodProductionCoefficient = coefficient;
  }

  @When("I instantiate Agriculture")
  public void createAgriculture() throws Exception {
    try {
      agriculture = new Agriculture(islandPart, foodProductionCoefficient);
    } catch (Exception e) {
      System.err.println("Fail to create Agricuture");
    }
  }

  @And("I calculate the yearly production of food")
  public void calculateYearlyProduction() {
    production = agriculture.yearlyProductionOfFood();
  }

  @Then("The result should be {double}")
  public void agricultureProductionIsCorrect(double expectedResult) {
    assertEquals(expectedResult, production, 0.001);
  }
}
