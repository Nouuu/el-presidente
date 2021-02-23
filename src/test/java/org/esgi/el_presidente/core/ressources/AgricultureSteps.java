package org.esgi.el_presidente.core.ressources;

import static org.junit.Assert.assertEquals;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AgricultureSteps {
  private int islandPart;
  private int foodProductionCoefficient;
  private Agriculture agriculture;
  private int production;

  @Given("The agriculture represente {int}% of the islande")
  public void setIslandPart(int islandPart) {
    this.islandPart = islandPart;
  }

  @And("The food production coefficient is {int}")
  public void setCoefficient(int coefficient) {
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

  @Then("The result should be {int}")
  public void agricultureProductionIsCorrect(int expectedResult) {
    assertEquals(expectedResult, production, 0.001);
  }
}
