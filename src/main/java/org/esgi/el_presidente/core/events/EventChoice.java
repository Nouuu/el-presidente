package org.esgi.el_presidente.core.events;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class EventChoice {
    private final String choiceName;
    private final List<EventFactionEffect> factionEffects;

    public EventChoice(String choiceName, List<EventFactionEffect> factionEffects) {
        this.choiceName = choiceName;
        this.factionEffects = Objects.requireNonNullElseGet(factionEffects, ArrayList::new);
    }

    public String getChoiceName() {
        return choiceName;
    }

    public List<EventFactionEffect> getFactionEffects() {
        return factionEffects;
    }

    public EventChoice addEventFactionEffect(EventFactionEffect factionEffect) {
        this.factionEffects.add(factionEffect);
        return this;
    }

    @Override
    public String toString() {
        return choiceName + "\n  Effets :\n" +
                factionEffects.stream().map(f -> "  - " + f.toString()).collect(Collectors.joining("\n"));
    }
}
