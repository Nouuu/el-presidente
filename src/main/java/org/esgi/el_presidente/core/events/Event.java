package org.esgi.el_presidente.core.events;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.esgi.el_presidente.core.helper.FileHelper;
import org.esgi.el_presidente.core.season.Season;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Event {
    private final Season season;
    private final String eventDetails;
    private final List<EventChoice> eventChoices;

    /**
     * Instantiates a new Event.
     *
     * @param season       the season
     * @param eventDetails the event details
     * @param eventChoices the event faction effect list
     */
    public Event(@JsonProperty("season") Season season, @JsonProperty("eventDetails") String eventDetails,
            @JsonProperty("eventChoices") List<EventChoice> eventChoices) {
        this.season = season;
        this.eventDetails = eventDetails;

        this.eventChoices = Objects.requireNonNullElseGet(eventChoices, ArrayList::new);
    }

    /**
     * Add event choice
     *
     * @param choice EventChoice
     * @return current Event
     */
    public Event addEventChoice(EventChoice choice) {
        eventChoices.add(choice);
        return this;
    }

    public Season getSeason() {
        return season;
    }


    public String getEventDetails() {
        return eventDetails;
    }

    public List<EventChoice> getEventChoices() {
        return eventChoices;
    }

    public EventChoice getEventChoice(int index) {
        return eventChoices.get(index);
    }

    /**
     * To string string.
     *
     * @return the string
     */
    @Override
    public String toString() {
        return eventDetails + "\nChoix :\n"
                + eventChoices.stream().map(o -> "- " + o.toString()).collect(Collectors.joining("\n"));
    }

    public static Event createFromJson(String ressourceJsonPath) throws JsonProcessingException {
        String content = FileHelper.readFileFromRessource("events/" + ressourceJsonPath);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(content, Event.class);
    }
}
