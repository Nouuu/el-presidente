package org.esgi.el_presidente.core.game;

import static org.junit.Assert.assertEquals;

import org.esgi.el_presidente.core.season.Season;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class timeManagerSteps {
  private TimeManager timeManager;

  @Given("a time manager")
  public void a_time_manager() {
    timeManager = new TimeManager();
  }

  @When("i go to next season")
  public void i_go_to_next_season() {
    timeManager.nextSeason();
  }

  @Then("the season is {string}")
  public void the_season_is_spring(String seasonString) {
    Season season = Season.fromString(seasonString);
    assertEquals(season, timeManager.getSeason());
  }

  @Then("the season count is {int}")
  public void the_season_count_is(int expectedCount) {
    assertEquals(expectedCount, timeManager.getSeasonCount());
  }

  @Then("the current year is {int}")
  public void the_current_year_is(int expectedCurrentYear) {
    assertEquals(expectedCurrentYear, timeManager.getCurrentYear());
  }
}
