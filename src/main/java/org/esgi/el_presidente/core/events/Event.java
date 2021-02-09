package org.esgi.el_presidente.core.events;

import org.esgi.el_presidente.core.season.Season;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * The type Event.
 */
public class Event {
    private final Season season;
    private final String eventDetails;
    private final List<EventFactionEffect> factionEffects;

    /**
     * Instantiates a new Event.
     *
     * @param season         the season
     * @param eventDetails   the event details
     * @param factionEffects the event faction effect list
     */
    public Event(Season season, String eventDetails, List<EventFactionEffect> factionEffects) {
        this.season = season;
        this.eventDetails = eventDetails;

        this.factionEffects = Objects.requireNonNullElseGet(factionEffects, ArrayList::new);
    }

    /**
     * Add Faction effect event.
     *
     * @param factionEffect the event faction effect
     * @return the current event
     */
    public Event addfactionEffect(EventFactionEffect factionEffect) {
        factionEffects.add(factionEffect);
        return this;
    }

    public Season getSeason() {
        return season;
    }

    public String getEventDetails() {
        return eventDetails;
    }

    public List<EventFactionEffect> getFactionEffects() {
        return factionEffects;
    }

    @Override
    public String toString() {
        return eventDetails + "\nEffet(s) :\n" +
                factionEffects.stream()
                        .map(o -> "- " + o.toString())
                        .collect(Collectors.joining("\n"));
    }
}
