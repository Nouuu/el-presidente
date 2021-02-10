package org.esgi.el_presidente.core.ressources;

import static org.junit.Assert.assertEquals;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class IslandPartSteps {
  private IslandPart subject;

  @Given("A Agriculture of size {double}")
  public void givenAgriculture(double size) {
    subject = new Agriculture(size, 14.0);
  }

  @Given("A Industry of size {double}")
  public void givenIndustry(double size) {
    subject = new Industry(size, 14.0);
  }

  @When("I increase size by {double}")
  public void whenIIncreaseSizeBy(double size) {
    subject.expand(size);
  }

  @When("I decrease size by {double}")
  public void whenIDecreaseSizeBy(double size) {
    subject.shrink(size);
  }

  @Then("The size should be {double}")
  public void testPartOfIsland(double expectedSize) {
    assertEquals(expectedSize, subject.getSize(), 0.001);
  }
}
