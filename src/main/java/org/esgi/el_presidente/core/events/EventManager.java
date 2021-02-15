package org.esgi.el_presidente.core.events;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.esgi.el_presidente.core.helper.FileHelper;
import org.esgi.el_presidente.core.season.Season;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * The Event manager.
 */
public class EventManager {
    private final List<Event> events;
    private int step;
    private final Random random;

    /**
     * Instantiates a new Event manager.
     *
     * @param events the event list
     */
    public EventManager(@JsonProperty("events") List<Event> events) {
        this.events = Objects.requireNonNullElseGet(events, ArrayList::new);
        step = 0;
        random = new Random();
    }

    /**
     * Add event to event manager.
     *
     * @param event the event
     * @return the current event manager
     */
    public EventManager addEvent(Event event) {
        events.add(event);
        return this;
    }

    /**
     * Gets random event.
     *
     * @return the random event
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
    public Event getRandomEvent(Season season) {
        List<Event> events = this.events.stream().filter(e -> e.getSeason() == season || e.getSeason() == null).collect(Collectors.toList());
        return events.get(getRandomIndex(events.size()));
    }

    /**
     * Gets random index.
     *
     * @param maxExcludedIndex the max excluded index
     * @return the random index
     */
    public int getRandomIndex(int maxExcludedIndex) {
        return random.nextInt(maxExcludedIndex);
    }

    /**
     * Gets next event.
     *
     * @return the next event
     */
    public Event getNextEvent() {
        if (events.size() == 0) {
            return null;
        }
        Event event = events.get(step);
        step = (step + 1) % events.size();
        return event;
    }

    /**
     * Reset step.
     */
    public void resetStep() {
        step = 0;
    }

    /**
     * Gets events.
     *
     * @return the events
     */
    public List<Event> getEvents() {
        return events;
    }

    /**
     * Gets step.
     *
     * @return the step
     */
    public int getStep() {
        return step;
    }

    /**
     * Gets event manager from json.
     * TODO put this mapper to scenario in the future
     *
     * @param ressourceJsonPath the ressource json path
     * @return the event manager from json
     * @throws IllegalArgumentException the illegal argument exception
     * @throws IOException              the io exception
     */
    public static EventManager getEventManagerFromJson(String ressourceJsonPath) throws IllegalArgumentException, IOException {
        String inputString = FileHelper.readFileFromRessource(ressourceJsonPath);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(inputString, EventManager.class);
    }

}
