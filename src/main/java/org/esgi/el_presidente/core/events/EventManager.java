package org.esgi.el_presidente.core.events;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class EventManager {
    private final List<Event> events;
    private int step;
    private final Random random;

    public EventManager(List<Event> events) {
        this.events = Objects.requireNonNullElseGet(events, ArrayList::new);
        step = 0;
        random = new Random();
    }

    public EventManager addEvent(Event event) {
        events.add(event);
        return this;
    }

    public Event getRandomEvent() {
        if (events.size() == 0) {
            return null;
        }
        return events.get(getRandomIndex());
    }

    public int getRandomIndex() {
        return random.nextInt(events.size());
    }

    public Event getNextEvent() {
        if (events.size() == 0) {
            return null;
        }
        Event event = events.get(step);
        step = (step + 1) % events.size();
        return event;
    }

    public void resetStep() {
        step = 0;
    }

    public List<Event> getEvents() {
        return events;
    }

    public int getStep() {
        return step;
    }
}
