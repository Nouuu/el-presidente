package org.esgi.el_presidente.core.events;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.esgi.el_presidente.core.season.Season;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

public class EventManager {
    private final List<Event> events;
    private int step;
    private final Random random;
    private boolean looped;

    /**
     * @param events the event list
     */
    public EventManager(@JsonProperty("events") List<Event> events) {
        this.events = Objects.requireNonNullElseGet(events, ArrayList::new);
        step = 0;
        random = new Random();
        looped = false;
    }

    /**
     * Add event to event manager
     *
     * @param event the event to add
     * @return the current event manager
     */
    public EventManager addEvent(Event event) {
        events.add(event);
        return this;
    }

    /**
     * Gets random event.
     *
     * @return the random event from event manager list
     */
    public Event getRandomEvent() {
        if (events.size() == 0) {
            return null;
        }
        return events.get(getRandomIndex(events.size()));
    }

    /**
     * Gets random event by season.
     *
     * @param season the season
     * @return the random event corresponding to season (or with season set to null)
     */
    public Event getRandomEventBySeason(Season season) {
        List<Event> events = this.events.stream().filter(e -> e.getSeason() == season || e.getSeason() == null).collect(Collectors.toList());
        return events.get(getRandomIndex(events.size()));
    }

    public int getRandomIndex(int maxExcludedIndex) {
        return random.nextInt(maxExcludedIndex);
    }

    /**
     * Gets next event in order of the list of events. If this reach the last one, it loop back on the first and set isLooped() to true
     *
     * @return Event object
     */
    public Event getNextEvent() {
        if (events.size() == 0) {
            return null;
        }
        Event event = events.get(step);
        step = (step + 1) % events.size();
        if (step == 0) {
            looped = true;
        }
        return event;
    }

    public void resetStep() {
        looped = false;
        step = 0;
    }

    public List<Event> getEvents() {
        return events;
    }

    public int getStep() {
        return step;
    }

    /**
     * Check if all events in scenario have been read
     *
     * @return boolean
     */
    public boolean isLooped() {
        return looped;
    }
}
