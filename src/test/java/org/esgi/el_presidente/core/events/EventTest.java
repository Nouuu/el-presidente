package org.esgi.el_presidente.core.events;

import org.assertj.core.api.Assertions;
import org.esgi.el_presidente.core.factions.FactionType;
import org.esgi.el_presidente.core.season.Season;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class EventTest {

    @Test
    public void addfactionEffect() {
        EventFactionEffect eventFactionEffect = new EventFactionEffect(FactionType.capitalist, 1, 2);

        Event event = new Event(Season.autumn, "", null).addfactionEffect(eventFactionEffect);

        Assertions.assertThat(event.getFactionEffects()).hasSize(1).containsExactly(eventFactionEffect);
    }

    @Test
    public void getSeason() {
        EventFactionEffect eventFactionEffect = new EventFactionEffect(FactionType.capitalist, 1, 2);
        List<EventFactionEffect> eventFactionEffects = new ArrayList<>();
        eventFactionEffects.add(eventFactionEffect);
        Season season = Season.spring;

        Event event = new Event(season, "", eventFactionEffects);

        Assertions.assertThat(event.getSeason()).isEqualTo(season);
    }

    @Test
    public void getEventDetails() {
        EventFactionEffect eventFactionEffect = new EventFactionEffect(FactionType.capitalist, 1, 2);
        List<EventFactionEffect> eventFactionEffects = new ArrayList<>();
        eventFactionEffects.add(eventFactionEffect);
        Season season = Season.spring;
        String eventDescription = "lorem ipsum";

        Event event = new Event(season, eventDescription, eventFactionEffects);

        Assertions.assertThat(event.getEventDetails()).isEqualTo(eventDescription);

    }

    @Test
    public void getFactionEffects() {
        EventFactionEffect eventFactionEffect = new EventFactionEffect(FactionType.capitalist, 1, 2);
        EventFactionEffect eventFactionEffect2 = new EventFactionEffect(null, 1, 2);
        List<EventFactionEffect> eventFactionEffects = new ArrayList<>();
        eventFactionEffects.add(eventFactionEffect);
        eventFactionEffects.add(eventFactionEffect2);
        Season season = Season.spring;
        String eventDescription = "lorem ipsum";

        Event event = new Event(season, eventDescription, eventFactionEffects);

        Assertions.assertThat(event.getFactionEffects()).hasSize(2).containsAll(eventFactionEffects);
    }

    @Test
    public void testToString() {
        EventFactionEffect eventFactionEffect = new EventFactionEffect(FactionType.capitalist, 1, 2);
        EventFactionEffect eventFactionEffect2 = new EventFactionEffect(null, 1, 2);
        List<EventFactionEffect> eventFactionEffects = new ArrayList<>();
        eventFactionEffects.add(eventFactionEffect);
        eventFactionEffects.add(eventFactionEffect2);
        Season season = Season.spring;
        String eventDescription = "lorem ipsum";

        String expectedString = eventDescription + "\nEffet(s) :\n" +
                eventFactionEffects.stream()
                        .map(o -> "- " + o.toString())
                        .collect(Collectors.joining("\n"));

        Event event = new Event(season, eventDescription, eventFactionEffects);

        Assertions.assertThat(event.toString()).isEqualTo(expectedString);
    }
}