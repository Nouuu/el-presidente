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

  @Given("A Agriculture of size {double}")
  public void givenAgriculture(double size) {
    agriculture = new Agriculture(size, 14.0);
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

  @When("I increase size by {double}")
  public void whenIIncreaseSizeBy(double size) {
    agriculture.expand(size);
  }

  @When("I decrease size by {double}")
  public void whenIDecreaseSizeBy(double size) {
    agriculture.shrink(size);
  }

  @And("I calculate the yearly production of food")
  public void calculateYearlyProduction() {
    production = agriculture.yearlyProductionOfFood();
  }

  @Then("The result should be {double}")
  public void agricultureProductionIsCorrect(double expectedResult) {
    assertEquals(expectedResult, production, 0.001);
  }

  @Then("The size should be {double}")
  public void testPartOfIsland(double expectedSize) {
    assertEquals(expectedSize, agriculture.getSize(), 0.001);
  }
}
