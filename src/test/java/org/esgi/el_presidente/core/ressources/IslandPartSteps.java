package org.esgi.el_presidente.core.ressources;

import static org.junit.Assert.assertEquals;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class IslandPartSteps {
  private IslandPart subject;

  @Given("A Agriculture of size {int}")
  public void givenAgriculture(int size) {
    subject = new Agriculture(size, 14);
  }

  @Given("A Industry of size {int}")
  public void givenIndustry(int size) {
    subject = new Industry(size, 14);
  }

  @When("I increase size by {int}")
  public void whenIIncreaseSizeBy(int size) {
    subject.expand(size);
  }

  @When("I decrease size by {int}")
  public void whenIDecreaseSizeBy(int size) {
    subject.shrink(size);
  }

  @Then("The size should be {int}")
  public void testPartOfIsland(int expectedSize) {
    assertEquals(expectedSize, subject.getSize(), 0.001);
  }
}
