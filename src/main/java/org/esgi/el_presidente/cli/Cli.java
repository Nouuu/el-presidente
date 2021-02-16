package org.esgi.el_presidente.cli;

import java.util.List;
import java.util.Scanner;

import org.esgi.el_presidente.core.events.Event;
import org.esgi.el_presidente.core.events.EventChoice;
import org.esgi.el_presidente.core.game.Game;

public class Cli {

  private Game game;

  public Cli(Game game) {
    this.game = game;
  }

  public void loop() {

    while (game.isNotLost()) {
      game.nextTurn();
      displayCurrentEvent();
      Scanner input = new Scanner(System.in);
      System.out.println(input.nextLine());
      // Deal with input for send update to game
    }
  }

  private void displayCurrentEvent() {
    Event event = game.getCurrentEvent();
    System.out.println(event.getEventDetails());
    List<EventChoice> EventChoices = event.getEventChoices();
    EventChoices.forEach(e -> System.out.println(e.getChoiceName()));
    System.out.println();
  }

  public void printResult() {
    System.out.println("C'est fini");
  }
}
