package org.esgi.el_presidente.core.game;

import static org.junit.Assert.assertEquals;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.esgi.el_presidente.core.events.Event;
import org.esgi.el_presidente.core.factions.FactionManager;
import org.esgi.el_presidente.core.ressources.RessourceManager;
import org.esgi.el_presidente.core.scenario.Scenario;
import org.esgi.el_presidente.core.season.Season;

import io.cucumber.core.plugin.PublishFormatter;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class GameSteps {
  private Game game;
  private boolean isEndOfYear;

  @Given("a game with test scenario in easy")
  public void init_with_test_scenario() throws JsonProcessingException {
    Difficulty difficulty = Difficulty.EASY;
    Scenario scenario = Scenario.createFromJson("test/scenarioTest.json");
    game = new Game(scenario, difficulty);
  }

  @When("i check end of year")
  public void checkEndOfYear() {
    isEndOfYear = game.isTheEndOfTheYear();
  }

  @When("i go to the next turn")
  public void goToTheNextTurn() {
    game.nextTurn();
  }

  @Then("it's the end of the year")
  public void itsTheEndOfYear() {
    assertEquals(true, isEndOfYear);
  }

  @Then("it's not the end of the year")
  public void itsNotTheEndOfYear() {
    assertEquals(false, isEndOfYear);
  }

  @Then("it's lost")
  public void itsLost() {
    assertEquals(false, game.isNotLost());
  }

  @Then("it's not lost")
  public void itsNotLost() {
    assertEquals(true, game.isNotLost());
  }

  @Then("the current event should be {string}")
  public void eventIs(String eventName) throws Exception {
    Event expectedEvent = getEventFromString(eventName);
    Event currentEvent = game.getCurrentEvent();
    assertEquals(expectedEvent.toString(), currentEvent.toString());
  }

  @Then("the current season is {string}")
  public void seasonIs(String season) {
    Season currentSeason = game.getCurrentSeason();
    assertEquals(season, currentSeason.toString());
  }

  @Then("the total amount of partisan is {int}")
  public void testAmountOfPartisan(int expectedAmountOfPartisan) {
    FactionManager factionManager = game.getFactionManager();
    int totalPartisan = factionManager.getTotalPartisan();
    assertEquals(expectedAmountOfPartisan, totalPartisan);
  }

  @Then("the food impact is {int}")
  public void testFoodImpact(int expectedFoodImpact) {
    int foodImpact = game.calculateFoodImpact();
    assertEquals(expectedFoodImpact, foodImpact);
  }

  private Event getEventFromString(String eventName) throws Exception {
    switch (eventName) {
      case "eventTest":
        return Event.createFromJson("test/eventTest.json");
      case "eventTest2":
        return Event.createFromJson("test/eventTest2.json");
      case "eventTestError":
        return Event.createFromJson("test/eventTest2.json");
      case "eventTestError2":
        return Event.createFromJson("test/eventTest2.json");

      default:
        throw new Exception("event is not in the list");
    }
  }
}
