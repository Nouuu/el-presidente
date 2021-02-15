package org.esgi.el_presidente.core.scenario;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.esgi.el_presidente.core.helper.FileHelper;

import java.util.List;

public class Sandbox extends Scenario {


    protected Sandbox(@JsonProperty("introduction") String introduction,
                      @JsonProperty("partisansSatisfaction") int partisansSatisfaction,
                      @JsonProperty("partisans") int partisans,
                      @JsonProperty("loyalistPartisansSatisfaction") int loyalistPartisansSatisfaction,
                      @JsonProperty("loyalistPartisans") int loyalistPartisans,
                      @JsonProperty("events") List<String> events,
                      @JsonProperty("initialMoney") int initialMoney,
                      @JsonProperty("initialIndustrialization") int initialIndustrialization,
                      @JsonProperty("initialAgriculture") int initialAgriculture) throws JsonProcessingException {
        super(introduction, partisansSatisfaction, partisans, loyalistPartisansSatisfaction,
                loyalistPartisans, events, initialMoney, initialIndustrialization, initialAgriculture);
    }


    public static Sandbox createFromJson() throws JsonProcessingException {
        String content = FileHelper.readFileFromRessource("scenarios/sandbox.json");
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(content, Sandbox.class);
    }

}
