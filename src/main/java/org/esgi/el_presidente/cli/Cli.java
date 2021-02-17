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
      Event event = game.getCurrentEvent();
      displayCurrentEvent(event);
      Scanner input = new Scanner(System.in);
      game.triggerEventEffect(input.nextInt());
      System.out.println();
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

  public void printResult() {
    System.out.println("C'est fini");
  }
}
