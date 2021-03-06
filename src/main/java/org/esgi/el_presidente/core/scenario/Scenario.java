package org.esgi.el_presidente.core.scenario;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.esgi.el_presidente.core.events.Event;
import org.esgi.el_presidente.core.events.EventManager;
import org.esgi.el_presidente.core.helper.FileHelper;
import org.esgi.el_presidente.core.season.Season;

import java.util.ArrayList;
import java.util.List;

public class Scenario {
    private final String introduction;
    private final int initialPartisansSatisfaction;
    private final int initialPartisans;
    private final int initialLoyalistPartisansSatisfaction;
    private final int initialLoyalistPartisans;
    protected final EventManager eventManager;
    private final int initialMoney;
    private final int initialFood;
    private final int initialIndustrialization;
    private final int initialAgriculture;

    /**
     * Instanciate Scenario
     *
     * @param introduction                  introduction
     * @param partisansSatisfaction         partisansSatisfaction
     * @param partisans                     partisans
     * @param loyalistPartisansSatisfaction loyalistPartisansSatisfaction
     * @param loyalistPartisans             loyalistPartisans
     * @param events                        events
     * @param initialMoney                  initialMoney
     * @param initialFood                   initialFood
     * @param initialIndustrialization      initialIndustrialization
     * @param initialAgriculture            initialAgriculture
     * @throws JsonProcessingException if bad parsing
     */
    protected Scenario(@JsonProperty("introduction") String introduction,
                       @JsonProperty("partisansSatisfaction") int partisansSatisfaction,
                       @JsonProperty("partisans") int partisans,
                       @JsonProperty("loyalistPartisansSatisfaction") int loyalistPartisansSatisfaction,
                       @JsonProperty("loyalistPartisans") int loyalistPartisans,
                       @JsonProperty("events") List<String> events,
                       @JsonProperty("initialMoney") int initialMoney,
                       @JsonProperty("initialFood") int initialFood,
                       @JsonProperty("initialIndustrialization") int initialIndustrialization,
                       @JsonProperty("initialAgriculture") int initialAgriculture) throws JsonProcessingException {

        this.introduction = introduction;
        this.initialPartisansSatisfaction = partisansSatisfaction;
        this.initialLoyalistPartisansSatisfaction = loyalistPartisansSatisfaction;
        this.initialPartisans = partisans;
        this.initialLoyalistPartisans = loyalistPartisans;
        this.initialMoney = initialMoney;
        this.initialFood = initialFood;
        this.initialIndustrialization = initialIndustrialization;
        this.initialAgriculture = initialAgriculture;
        this.eventManager = new EventManager(getEvents(events));

    }

    private List<Event> getEvents(List<String> eventsPath) throws JsonProcessingException {
        if (eventsPath == null || eventsPath.size() < 2) {
            throw new IllegalArgumentException("You must provide at least 2 events to your scenario !");
        }
        List<Event> events = new ArrayList<>();
        for (String path : eventsPath) {
            events.add(Event.createFromJson(path));
        }
        return events;
    }

    public static Scenario createFromJson(String ressourceJsonPath) throws JsonProcessingException {
        String content = FileHelper.readFileFromRessource("scenarios/" + ressourceJsonPath);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(content, Scenario.class);
    }

    public String getIntroduction() {
        return introduction;
    }

    public int getInitialPartisansSatisfaction() {
        return initialPartisansSatisfaction;
    }

    public int getInitialPartisans() {
        return initialPartisans;
    }

    public int getInitialLoyalistPartisansSatisfaction() {
        return initialLoyalistPartisansSatisfaction;
    }

    public int getInitialLoyalistPartisans() {
        return initialLoyalistPartisans;
    }

    public Event getNextEvent(Season season) {
        return eventManager.getNextEvent();
    }

    public int getEventManagerStep() {
        return eventManager.getStep();
    }

    public int getEventsCount() {
        return eventManager.getEvents().size();
    }

    public int getInitialMoney() {
        return initialMoney;
    }

    public int getInitialFood() {
        return initialFood;
    }

    public int getInitialIndustrialization() {
        return initialIndustrialization;
    }

    public int getInitialAgriculture() {
        return initialAgriculture;
    }

    public boolean isLastEvent() {
        return eventManager.isLooped();
    }
}
