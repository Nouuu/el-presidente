package org.esgi.el_presidente.core.scenario;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.esgi.el_presidente.core.helper.FileHelper;

public class Scenario {
    private final int initialPartisansSatisfaction;
    private final int initialPartisans;
    private final int initialLoyalistPartisansSatisfaction;
    private final int initialLoyalistPartisans;
    //    private final EventManager eventManager;
    private final int initialMoney;
    private final int initialIndustrialization;
    private final int initialAgriculture;

    private Scenario(@JsonProperty("partisansSatisfaction") int partisansSatisfaction,
                     @JsonProperty("partisans") int partisans,
                     @JsonProperty("loyalistPartisansSatisfaction") int loyalistPartisansSatisfaction,
                     @JsonProperty("loyalistPartisans") int loyalistPartisans,
//                    @JsonProperty("events") List<Event> events,
                     @JsonProperty("initialMoney") int initialMoney,
                     @JsonProperty("initialIndustrialization") int initialIndustrialization,
                     @JsonProperty("initialAgriculture") int initialAgriculture) {

        initialPartisansSatisfaction = partisansSatisfaction;
        initialLoyalistPartisansSatisfaction = loyalistPartisansSatisfaction;
        initialPartisans = partisans;
        initialLoyalistPartisans = loyalistPartisans;
//        eventManager = new EventManager(events);

        this.initialMoney = initialMoney;
        this.initialIndustrialization = initialIndustrialization;
        this.initialAgriculture = initialAgriculture;
    }

    public static Scenario createFromJson(String ressourceJsonPath) throws JsonProcessingException {
        String content = FileHelper.readFileFromRessource(ressourceJsonPath);
        ObjectMapper mapper = new ObjectMapper();
        Scenario scenario = mapper.readValue(content, Scenario.class);
        return scenario;
    }
}
