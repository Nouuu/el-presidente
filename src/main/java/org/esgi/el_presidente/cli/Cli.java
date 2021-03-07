package org.esgi.el_presidente.cli;

import java.util.List;
import java.util.Scanner;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.esgi.el_presidente.core.events.Event;
import org.esgi.el_presidente.core.events.EventChoice;
import org.esgi.el_presidente.core.factions.Faction;
import org.esgi.el_presidente.core.factions.FactionManager;
import org.esgi.el_presidente.core.factions.FactionType;
import org.esgi.el_presidente.core.game.Difficulty;
import org.esgi.el_presidente.core.game.Game;
import org.esgi.el_presidente.core.ressources.RessourceManager;
import org.esgi.el_presidente.core.scenario.Scenario;
import org.esgi.el_presidente.core.scenario.ScenarioList;

public class Cli {

  private Game game;

  public Cli() throws JsonProcessingException {
    Scenario scenario = selectScenario();
    Difficulty difficulty = selectGameMode();
    System.out.println(scenario.getIntroduction() + " IN " + difficulty.name());
    this.game = new Game(scenario, difficulty);
  }

  public void loop() {
    Scanner input = new Scanner(System.in);
    RessourceManager ressourceManager = game.getRessourceManager();
    Event event;

    while (game.isNotLost()) {
      game.nextTurn();
      event = game.getCurrentEvent();
      displayCurrentEvent(event);
      game.triggerEventEffect(input.nextInt());

      if (game.isTheEndOfTheYear()) {
        ressourceManager.triggerEndOfYearAction();
        displayEndOfYearBilan();
        game.triggerEndOfYearCost();
        System.out.println(reviewTheGame());
      }
    }
  }

  private Difficulty selectGameMode() {
    Scanner input = new Scanner(System.in);
    System.out.println("Select Game Mode: 1 for EASY 2 for NORMAL 3 press 4 if you are JDG");
    int choice = input.nextInt();
    switch (choice) {
    case 1:
      return Difficulty.EASY;
    case 2:
      return Difficulty.MEDIUM;
    case 3:
      return Difficulty.HARD;
    case 4:
      return Difficulty.HARDCORE;
    default:
      return Difficulty.MEDIUM;
    }
  }

  private Scenario selectScenario() throws JsonProcessingException {
    Scanner input = new Scanner(System.in);
    ScenarioList[] scenarioList = ScenarioList.values();
    System.out.println("Veuillez chosir un scenario");
    for (int i = 0; i < scenarioList.length; i++) {
      System.out.println(i + ": " + scenarioList[i]);
    }

    int choice = input.nextInt();
    Scenario scenario = Scenario.createFromJson(scenarioList[choice].getPath());
    return scenario;
  }

  private void displayEndOfYearBilan() {
    Scanner input = new Scanner(System.in);
    String userEntry;
    System.out.println(reviewTheGame());
    System.out.println("It's the end of the year you can buy bribe and food\n");
    do {
      System.out.println("Enter \"food\" for buy food or \"faction\" to see the faction bribe sequence or q to quit\n");
      userEntry = input.next();
      switch (userEntry) {
      case "food":
        buyFood();
        break;
      case "faction":
        buyFaction();
        break;
      default:
        break;
      }
    } while (!userEntry.equals("q"));
  }

  private void buyFaction() {
    String factionTypeStr;
    FactionType factionType;
    FactionManager factionManager = game.getFactionManager();
    Faction faction;
    RessourceManager ressourceManager = game.getRessourceManager();
    Scanner input = new Scanner(System.in);
    do {
      System.out.println("enter the type of your faction or \"q\" for exit");
      factionTypeStr = input.next();
      factionType = FactionType.fromString(factionTypeStr);
      if (factionType != null) {
        faction = factionManager.getFaction(factionType);
        try {
          ressourceManager.buyBribe(faction);
        } catch (Exception exception) {
          System.out.println(exception.getMessage());
        }
      } else if (!factionTypeStr.equals("q")) {
        System.out.println("Your faction is lot in the list");
      }
    } while (!factionTypeStr.equals("q"));
  }

  private void buyFood() {
    RessourceManager ressourceManager = game.getRessourceManager();
    Scanner input = new Scanner(System.in);
    int unitOfFood;
    try {
      System.out.println("How many unit of food do you want ?");
      unitOfFood = input.nextInt();
      ressourceManager.buyFood(unitOfFood);
    } catch (Exception e) {
      System.out.println("you don't have the money to buy this amount of food");
    }
  }

  private void displayCurrentEvent(Event event) {
    System.out.println(event.getEventDetails());
    List<EventChoice> eventChoices = event.getEventChoices();
    for (int i = 0; i < eventChoices.size(); i += 1) {
      EventChoice eventChoice = eventChoices.get(i);
      System.out.println(String.valueOf(i) + ' ' + eventChoice.getChoiceName());
    }
  }

  public String reviewTheGame() {
    RessourceManager ressourceManager = game.getRessourceManager();
    FactionManager factionManager = game.getFactionManager();
    StringBuilder gameStrBuilder = new StringBuilder();

    System.out.println("GlobalSatisfaction: " + factionManager.getGlobalSatisfaction());
    askForFactionsDetails(gameStrBuilder);

    gameStrBuilder.append("GlobalSatisfaction: " + factionManager.getGlobalSatisfaction() + "\n");
    gameStrBuilder.append("Money: " + ressourceManager.getMoney() + "\n");
    gameStrBuilder.append("FoodReseve: " + ressourceManager.getFoodReserves() + "\n");
    gameStrBuilder.append("Agriculture part: " + ressourceManager.getAgriculturePart() + "\n");
    gameStrBuilder.append("Industry part: " + ressourceManager.getIndustryPart() + "\n");
    return gameStrBuilder.toString();
  }

  private void askForFactionsDetails(StringBuilder gameStrBuilder) {
    if (game.isNotLost()) {
      System.out.println("Do you want to see the details of the factions ? (true/false)");
      Scanner input = new Scanner(System.in);
      if (input.nextBoolean()) {
        gameStrBuilder.append(reviewFaction());
      }
    } else {
      gameStrBuilder.append(reviewFaction());
    }
  }

  private String reviewFaction() {
    StringBuilder factionReview = new StringBuilder();
    FactionManager factionManager = game.getFactionManager();
    List<Faction> factionList = factionManager.getFactionList();
    for (Faction faction : factionList) {
      factionReview.append(faction);
    }
    return factionReview.toString();
  }

  public void printResult() {
    System.out.println(reviewTheGame());
    System.out.println("C'est fini");
  }
}
