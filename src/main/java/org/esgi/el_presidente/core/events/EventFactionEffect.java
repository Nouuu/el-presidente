package org.esgi.el_presidente.core.events;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.StringUtils;
import org.esgi.el_presidente.core.factions.FactionType;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Event faction effect.
 */
public class EventFactionEffect {
    private final FactionType factionType;
    private final int partisansPercentEffect;
    private final int satisfactionEffect;


    /**
     * Instantiates a new Event faction effect.
     *
     * @param factionType        the faction type. Setting this null means that concern all factions
     * @param partisansEffect    the partisans effect
     * @param satisfactionEffect the satisfaction effect
     */
    public EventFactionEffect(@JsonProperty("factionType") FactionType factionType,
                              @JsonProperty("partisansEffect") int partisansEffect,
                              @JsonProperty("satisfactionEffect") int satisfactionEffect) {
        this.factionType = factionType;
        this.partisansPercentEffect = Math.min(100, Math.max(-100, partisansEffect));
        this.satisfactionEffect = Math.min(100, Math.max(-100, satisfactionEffect));
    }

    /**
     * Gets faction type.
     *
     * @return the faction type
     */
    public FactionType getFactionType() {
        return factionType;
    }

    /**
     * Gets partisans effect.
     *
     * @return the partisans effect
     */
    public int getPartisansPercentEffect() {
        return partisansPercentEffect;
    }

    /**
     * Gets satisfaction effect.
     *
     * @return the satisfaction effect
     */
    public int getSatisfactionEffect() {
        return satisfactionEffect;
    }


    /**
     * Gets the description of the faction effect
     *
     * @return the description of the faction effect
     */
    @Override
    public String toString() {
        StringBuilder description = new StringBuilder();
        List<String> effectList = new ArrayList<>();

        if (factionType == null) {
            description.append("Toute les factions : ");
        } else {
            description.append(StringUtils.capitalize(factionType.toString())).append(" : ");
        }

        if (partisansPercentEffect > 0) {
            effectList.add("+" + partisansPercentEffect + "% de partisans");
        } else if (partisansPercentEffect < 0) {
            effectList.add( partisansPercentEffect + "% de partisans");
        }

        if (satisfactionEffect > 0) {
            effectList.add("+" + satisfactionEffect + "% de satisfaction");
        } else if (satisfactionEffect < 0) {
            effectList.add(satisfactionEffect + "% de satisfaction");
        }

        description.append(String.join(", ", effectList));

        return description.toString();
    }
}
