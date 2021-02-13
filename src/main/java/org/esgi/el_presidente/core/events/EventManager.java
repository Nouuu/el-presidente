package org.esgi.el_presidente.core.events;

import java.util.ArrayList;

import org.esgi.el_presidente.core.season.Season;

public class EventManager {
  private ArrayList<Event> value;

  public EventManager() {
    this.value = new ArrayList<Event>();
  }

  public Event getNextEvent(Season season) {
    return null;
  }
}
