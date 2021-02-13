package org.esgi.el_presidente.core.game;

import org.esgi.el_presidente.core.events.*;

public class Game {
  private GameMode gameMode;
  private Difficulty difficulty;
  private EventManager eventManager;
  private TimeManager timeManager;

  public Game(GameMode gameMode, Difficulty difficulty) {
    // How do we use gameMode
    this.gameMode = gameMode;
    // How do we use difficulty
    this.difficulty = difficulty;
    this.eventManager = new EventManager();
    this.timeManager = new TimeManager();
  }

  public void main() {
    while (isItLose()) {
      Event event = eventManager.getNextEvent(timeManager.getSeason());
      // TODO use event
      if (timeManager.isTheEndOfTheYear()) {
        // TODO Gestion de Bilan de fin d'année
      }

      timeManager.nextSeason();
    }
    reviewTheGame();
  }

  // TODO
  private void reviewTheGame() {
  }

  // TODO
  private boolean isItLose() {
    return false;
  }
}
