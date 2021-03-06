package org.esgi.el_presidente.core.events;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.StringUtils;
import org.esgi.el_presidente.core.factions.FactionType;
import org.esgi.el_presidente.core.game.Difficulty;
import org.esgi.el_presidente.core.helper.MathHelper;

import java.util.ArrayList;
import java.util.List;

public class EventFactionEffect {
    private final FactionType factionType;
    private final int partisansPercentEffect;
    private final int satisfactionEffect;

    /**
     * Instantiates a new Event faction effect.
     *
     * @param factionType        the faction type. Setting this null means that
     *                           concern all factions
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

    public FactionType getFactionType() {
        return factionType;
    }

    public int getPartisansPercentEffect() {
        return partisansPercentEffect;
    }

    public int getSatisfactionEffect() {
        return satisfactionEffect;
    }


    /**
     * Get the event faction effect details
     * @return String
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
            effectList.add(partisansPercentEffect + "% de partisans");
        }

        if (satisfactionEffect > 0) {
            effectList.add("+" + satisfactionEffect + "% de satisfaction");
        } else if (satisfactionEffect < 0) {
            effectList.add(satisfactionEffect + "% de satisfaction");
        }

        description.append(String.join(", ", effectList));

        return description.toString();
    }

    /**
     * Get the event faction effect details in terms of difficulty ratio
     * @param difficulty Difficulty enum
     * @return String
     */
    public String toString(Difficulty difficulty) {
        StringBuilder description = new StringBuilder();
        List<String> effectList = new ArrayList<>();

        if (factionType == null) {
            description.append("Toute les factions : ");
        } else {
            description.append(StringUtils.capitalize(factionType.toString())).append(" : ");
        }

        if (partisansPercentEffect > 0) {
            effectList.add("+" +
                    MathHelper.multiplyIntDoubleToFloor(partisansPercentEffect, difficulty.getGainMultiplier())
                    + "% de partisans");
        } else if (partisansPercentEffect < 0) {
            effectList.add(
                    MathHelper.multiplyIntDoubleToFloor(partisansPercentEffect, difficulty.getLoseMultiplier())
                            + "% de partisans");
        }

        if (satisfactionEffect > 0) {
            effectList.add("+" +
                    MathHelper.multiplyIntDoubleToFloor(satisfactionEffect, difficulty.getGainMultiplier())
                    + "% de satisfaction");
        } else if (satisfactionEffect < 0) {
            effectList.add(
                    MathHelper.multiplyIntDoubleToFloor(satisfactionEffect, difficulty.getLoseMultiplier())
                            + "% de satisfaction");
        }

        description.append(String.join(", ", effectList));

        return description.toString();
    }
}
