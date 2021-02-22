package org.esgi.el_presidente.core.events;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.assertj.core.api.Assertions;
import org.esgi.el_presidente.core.factions.FactionType;
import org.esgi.el_presidente.core.season.Season;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class EventTest {

    private final EventFactionEffect eventFactionEffect = new EventFactionEffect(FactionType.capitalist, 1, 2);
    private final EventFactionEffect eventFactionEffect2 = new EventFactionEffect(null, 1, 2);
    private final List<EventFactionEffect> eventFactionEffects = new ArrayList<>();
    private final EventChoice eventChoice = new EventChoice("choix", 0, 0, 0, 0, eventFactionEffects);
    private final List<EventChoice> eventChoices = new ArrayList<>();
    private final Season season = Season.spring;
    private final String eventDescription = "lorem ipsum";
    private final Event event = new Event(season, eventDescription, eventChoices);

    @Before
    public void setUp() {
        eventFactionEffects.clear();
        eventFactionEffects.add(eventFactionEffect);
        eventFactionEffects.add(eventFactionEffect2);
        eventChoices.clear();
        eventChoices.add(eventChoice);
    }

    @Test
    public void getSeason() {

        Assertions.assertThat(event.getSeason()).isEqualTo(season);
    }

    @Test
    public void getEventDetails() {
        Assertions.assertThat(event.getEventDetails()).isEqualTo(eventDescription);
    }

    @Test
    public void getEventChoices() {

        Event event = new Event(season, eventDescription, eventChoices);

        Assertions.assertThat(event.getEventChoices()).hasSize(1).containsAll(eventChoices);
    }

    @Test
    public void testToString() {

        String expectedString = "lorem ipsum\n" + "Choix :\n" + "- choix\n" + "  Effets :\n"
                + "  - Capitalistes : +1% de partisans, +2% de satisfaction\n"
                + "  - Toute les factions : +1% de partisans, +2% de satisfaction";

        Event event = new Event(season, eventDescription, eventChoices);

        Assertions.assertThat(event.toString()).isEqualTo(expectedString);
    }

    @Test
    public void addEventChoice() {
        EventChoice newEventChoice = new EventChoice("nouveau choix", 4, -4, 10, -20, null);
        event.addEventChoice(newEventChoice);
        Assertions.assertThat(event.getEventChoices()).containsOnlyOnce(newEventChoice);
    }

    @Test
    public void testCreateFromJson() throws JsonProcessingException {
        Event event = Event.createFromJson("test/eventTest.json");
        Assertions.assertThat(event.getEventDetails()).isEqualTo("Event test");
        Assertions.assertThat(event.getEventChoices()).hasSize(2);

        List<EventFactionEffect> factionEffects1 = new ArrayList<>();
        factionEffects1.add(new EventFactionEffect(FactionType.communist, 10, 5));
        factionEffects1.add(new EventFactionEffect(FactionType.ecologist, -10, 0));
        EventChoice choice1Expected = new EventChoice("Choice 1", 7, -5, 8, 600, factionEffects1);

        EventChoice choice1 = event.getEventChoices().get(0);
        Assertions.assertThat(choice1.toString()).isEqualTo(choice1Expected.toString());

        List<EventFactionEffect> factionEffects2 = new ArrayList<>();
        factionEffects2.add(new EventFactionEffect(null, 1, 1));
        EventChoice choice2Expected = new EventChoice("Choice 2", 0, 0, 0, 60, factionEffects2);

        EventChoice choice2 = event.getEventChoices().get(1);
        Assertions.assertThat(choice2.toString()).isEqualTo(choice2Expected.toString());

    }

    @Test
    public void testCreateFromJsonError() {
        Assertions.assertThatThrownBy(() -> Event.createFromJson("test/eventTestError.json"))
                .isInstanceOf(JsonProcessingException.class);
    }

    @Test
    public void testCreateFromJsonError2() {
        Assertions.assertThatThrownBy(() -> Event.createFromJson("test/eventTestError2.json"))
                .isInstanceOf(JsonProcessingException.class);
    }
}