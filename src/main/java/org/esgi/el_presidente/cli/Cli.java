package org.esgi.el_presidente.cli;

import java.util.List;
import java.util.Scanner;

import org.esgi.el_presidente.core.events.Event;
import org.esgi.el_presidente.core.events.EventChoice;
import org.esgi.el_presidente.core.factions.FactionManager;
import org.esgi.el_presidente.core.game.Game;
import org.esgi.el_presidente.core.ressources.RessourceManager;

public class Cli {

  private Game game;

  public Cli(Game game) {
    this.game = game;
  }

  public void loop() {

    while (game.isNotLost()) {
      game.nextTurn();
      Event event = game.getCurrentEvent();
      displayCurrentEvent(event);
      Scanner input = new Scanner(System.in);
      game.triggerEventEffect(input.nextInt());
      if (game.isTheEndOfTheYear()) {
        RessourceManager ressourceManager = game.getRessourceManager();
        ressourceManager.triggerEndOfYearAction();
        displayEndOfYearBilan();
        handleEndOfYearAction();
        displayEndOfYearBilan();
      }
    }
  }

  private void handleEndOfYearAction() {
  }

  private void displayEndOfYearBilan() {
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
    StringBuilder game = new StringBuilder();
    game.append("Money: " + ressourceManager.getMoney() + "\n");
    game.append("FoodReseve: " + ressourceManager.getFoodReserves() + "\n");
    game.append("GlobalSatisfaction: " + factionManager.getGlobalSatisfaction() + "\n");
    return game.toString();
  }

  public void printResult() {
    System.out.println(reviewTheGame());
    System.out.println("C'est fini");
  }
}
