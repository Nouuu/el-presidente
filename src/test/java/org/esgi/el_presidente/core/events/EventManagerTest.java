package org.esgi.el_presidente.core.events;

import org.assertj.core.api.Assertions;
import org.esgi.el_presidente.core.season.Season;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class EventManagerTest {
    private final Event event1 = new Event(Season.spring, "details 1", null);
    private final Event event2 = new Event(Season.autumn, "details 2", null);
    private final Event event3 = new Event(Season.winter, "details 3", null);
    private final Event event4 = new Event(Season.summer, "details 4", null);
    private final Event event5 = new Event(null, "details 5", null);

    private final List<Event> events = new ArrayList<>();

    private final EventManager eventManager = new EventManager(events);

    @Before
    public void setUp() {
        eventManager.resetStep();
        events.clear();
        events.add(event1);
        events.add(event2);
        events.add(event3);
        events.add(event4);
        events.add(event5);
    }

    @Test
    public void addEvent() {
        Event newEvent = new Event(null, "new event", null);
        eventManager.addEvent(newEvent);
        Assertions.assertThat(eventManager.getEvents()).containsOnlyOnce(newEvent);
    }

    @Test
    public void getRandomEvent() {
        for (int i = 0; i < 4; i++) {
            Assertions.assertThat(eventManager.getRandomEvent()).isIn(events);
        }
    }

    @Test
    public void getRandomEventBySeason() {
        List<Season> authorizedSeason = new ArrayList<>();
        authorizedSeason.add(Season.autumn);
        authorizedSeason.add(null);

        for (int i = 0; i < 5; i++) {
            Assertions.assertThat(eventManager.getRandomEventBySeason(Season.autumn).getSeason()).isIn(authorizedSeason);
        }
    }

    @Test
    public void getRandomIndex() {
        int maxSize = 5;
        int previousRandom = eventManager.getRandomIndex(maxSize);
        Assertions.assertThat(previousRandom).isLessThan(maxSize);

        for (int i = 0; i < 10; i++) {
            int nextRandom = eventManager.getRandomIndex(maxSize);
            Assertions.assertThat(nextRandom).isLessThan(maxSize);
            if (nextRandom != previousRandom) {
                return;
            }
        }
        Assertions.fail("Random don't work");
    }


    @Test
    public void getNextEvent() {
        Assertions.assertThat(eventManager.getNextEvent()).isEqualTo(event1);
        Assertions.assertThat(eventManager.getNextEvent()).isEqualTo(event2);
        Assertions.assertThat(eventManager.getNextEvent()).isEqualTo(event3);
        Assertions.assertThat(eventManager.getNextEvent()).isEqualTo(event4);
        Assertions.assertThat(eventManager.getNextEvent()).isEqualTo(event5);
        Assertions.assertThat(eventManager.getNextEvent()).isEqualTo(event1);
    }

    @Test
    public void getEvents() {
        Assertions.assertThat(eventManager.getEvents()).isEqualTo(events);
    }

    @Test
    public void getStep() {
        Assertions.assertThat(eventManager.getStep()).isEqualTo(0);
        eventManager.getNextEvent();
        Assertions.assertThat(eventManager.getStep()).isEqualTo(1);
        eventManager.getNextEvent();
        eventManager.getNextEvent();
        eventManager.getNextEvent();
        eventManager.getNextEvent();
        Assertions.assertThat(eventManager.getStep()).isEqualTo(0);
    }

    @Test
    public void getEmptyEvent() {
        EventManager emptyEventManager = new EventManager(null);
        Assertions.assertThat(emptyEventManager.getRandomEvent()).isNull();
        Assertions.assertThat(emptyEventManager.getNextEvent()).isNull();
    }

    @Test
    public void isLooped() {
        Assertions.assertThat(eventManager.isLooped()).isFalse();
        for (int i = 0; i < eventManager.getEvents().size(); i++) {
            eventManager.getNextEvent();
        }
        Assertions.assertThat(eventManager.isLooped()).isTrue();
        eventManager.resetStep();
        Assertions.assertThat(eventManager.isLooped()).isFalse();
    }
}