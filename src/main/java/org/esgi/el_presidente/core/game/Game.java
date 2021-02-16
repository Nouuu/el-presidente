package org.esgi.el_presidente.core.game;

import org.esgi.el_presidente.core.events.*;
import org.esgi.el_presidente.core.factions.Faction;
import org.esgi.el_presidente.core.factions.FactionManager;
import org.esgi.el_presidente.core.factions.FactionType;
import org.esgi.el_presidente.core.ressources.Agriculture;
import org.esgi.el_presidente.core.ressources.Industry;
import org.esgi.el_presidente.core.ressources.RessourceManager;
import org.esgi.el_presidente.core.scenario.Scenario;

public class Game {
  private Difficulty difficulty;
  private TimeManager timeManager;
  private Scenario scenario;
  private FactionManager factionManager;
  private RessourceManager ressourceManager;
  private Event currentEvent;

  private FactionManager constructFactionManagerFromScenario(Scenario scenario) {
    int initialPartisansSatisfaction = scenario.getInitialPartisansSatisfaction();
    int initialPartisans = scenario.getInitialPartisans();
    int initialLoyalistPartisansSatisfaction = scenario.getInitialLoyalistPartisansSatisfaction();
    int initialLoyalistPartisans = scenario.getInitialLoyalistPartisans();

    return new FactionManager().initFactionList(initialPartisansSatisfaction, initialPartisans,
        initialLoyalistPartisansSatisfaction, initialLoyalistPartisans);
  }

  private RessourceManager constructRessourceManagerFromScenario(Scenario scenario) {
    Agriculture agriculture = new Agriculture(scenario.getInitialAgriculture(), 4);
    Industry industry = new Industry(scenario.getInitialIndustrialization(), 15);
    Faction loyalist = factionManager.getFaction(FactionType.loyalist);
    int initialMoney = scenario.getInitialMoney();
    return new RessourceManager(loyalist, initialMoney, 0, agriculture, industry);
  }

  public Game(Scenario scenario, Difficulty difficulty) {
    // How do we use difficulty
    this.difficulty = difficulty;
    this.scenario = scenario;

    this.factionManager = constructFactionManagerFromScenario(scenario);
    this.ressourceManager = constructRessourceManagerFromScenario(scenario);
    this.timeManager = new TimeManager();
  }

  public void nextTurn() {
    currentEvent = scenario.getNextEvent(timeManager.getSeason());
    currentEvent.getEventDetails();
    currentEvent.getEventChoices();
    // TODO use event
    if (timeManager.isTheEndOfTheYear()) {
      // TODO Gestion de Bilan de fin d'année
    }

    timeManager.nextSeason();
  }

  public Event getCurrentEvent() {
    return currentEvent;
  }

  // TODO
  private void reviewTheGame() {
  }

  // TODO
  private boolean isItLose() {
    return false;
  }
}
