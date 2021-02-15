package org.esgi.el_presidente.core.scenario;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.esgi.el_presidente.core.events.Event;
import org.esgi.el_presidente.core.events.EventManager;
import org.esgi.el_presidente.core.helper.FileHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Scenario {
    private final int initialPartisansSatisfaction;
    private final int initialPartisans;
    private final int initialLoyalistPartisansSatisfaction;
    private final int initialLoyalistPartisans;
    private final EventManager eventManager;
    private final int initialMoney;
    private final int initialIndustrialization;
    private final int initialAgriculture;

    private Scenario(@JsonProperty("partisansSatisfaction") int partisansSatisfaction,
                     @JsonProperty("partisans") int partisans,
                     @JsonProperty("loyalistPartisansSatisfaction") int loyalistPartisansSatisfaction,
                     @JsonProperty("loyalistPartisans") int loyalistPartisans,
                     @JsonProperty("events") List<String> events,
                     @JsonProperty("initialMoney") int initialMoney,
                     @JsonProperty("initialIndustrialization") int initialIndustrialization,
                     @JsonProperty("initialAgriculture") int initialAgriculture) throws JsonProcessingException {

        this.initialPartisansSatisfaction = partisansSatisfaction;
        this.initialLoyalistPartisansSatisfaction = loyalistPartisansSatisfaction;
        this.initialPartisans = partisans;
        this.initialLoyalistPartisans = loyalistPartisans;
        this.initialMoney = initialMoney;
        this.initialIndustrialization = initialIndustrialization;
        this.initialAgriculture = initialAgriculture;
        this.eventManager = new EventManager(getEvents(events));

    }

    private List<Event> getEvents(List<String> eventsPath) throws JsonProcessingException {
        List<Event> events = new ArrayList<>();
        for (String path:eventsPath) {
            events.add(Event.createFromJson(path));
        }
        return events;
    }

    public static Scenario createFromJson(String ressourceJsonPath) throws JsonProcessingException {
        String content = FileHelper.readFileFromRessource("scenarios/" + ressourceJsonPath);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(content, Scenario.class);
    }
}
