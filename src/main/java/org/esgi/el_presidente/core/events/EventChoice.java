package org.esgi.el_presidente.core.events;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.esgi.el_presidente.core.game.Difficulty;
import org.esgi.el_presidente.core.helper.MathHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * The type Event choice.
 */
public class EventChoice {
    private final String choiceName;
    private final int industryEffect;
    private final int agricultureEffect;
    private final int foodEffect;
    private final int financeEffect;
    private final List<EventFactionEffect> factionEffects;


    /**
     * Instanciate Event choice
     *
     * @param choiceName        choice description
     * @param industryEffect    industryEffect
     * @param agricultureEffect agricultureEffect
     * @param foodEffect        foodEffect
     * @param financeEffect     financeEffect
     * @param factionEffects    List<EventFactionEffect>
     */
    public EventChoice(@JsonProperty("choiceName") String choiceName,
                       @JsonProperty("industryEffect") int industryEffect,
                       @JsonProperty("agricultureEffect") int agricultureEffect,
                       @JsonProperty("foodEffect") int foodEffect,
                       @JsonProperty("financeEffect") int financeEffect,
                       @JsonProperty("factionEffects") List<EventFactionEffect> factionEffects) {
        this.choiceName = choiceName;
        this.industryEffect = industryEffect;
        this.agricultureEffect = agricultureEffect;
        this.foodEffect = foodEffect;
        this.financeEffect = financeEffect;
        this.factionEffects = Objects.requireNonNullElseGet(factionEffects, ArrayList::new);
    }

    /**
     * Add faction effect
     *
     * @param factionEffect EventFactionEffect
     * @return current object
     */
    public EventChoice addEventFactionEffect(EventFactionEffect factionEffect) {
        this.factionEffects.add(factionEffect);
        return this;
    }

    /**
     * Gets choice name/description.
     *
     * @return the choice name
     */
    public String getChoiceName() {
        return choiceName;
    }

    /**
     * Gets industry effect.
     *
     * @return the industry effect
     */
    public int getIndustryEffect() {
        return industryEffect;
    }

    /**
     * Gets agriculture effect.
     *
     * @return the agriculture effect
     */
    public int getAgricultureEffect() {
        return agricultureEffect;
    }

    /**
     * Gets food effect.
     *
     * @return the food effect
     */
    public int getFoodEffect() {
        return foodEffect;
    }

    /**
     * Gets finance effect.
     *
     * @return the finance effect
     */
    public int getFinanceEffect() {
        return financeEffect;
    }

    /**
     * Gets faction effects.
     *
     * @return the faction effects
     */
    public List<EventFactionEffect> getFactionEffects() {
        return factionEffects;
    }

    /**
     * To string string.
     *
     * @return the string
     */
    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append(choiceName).append("\n")
                .append("  Effets :");

        if (!factionEffects.isEmpty()) {
            string.append("\n");
            string.append(factionEffects.stream().map(f -> "  - " + f.toString()).collect(Collectors.joining("\n")));
        }

        if (industryEffect > 0) {
            string.append("\n  - ").append("+").append(industryEffect).append("% d'industrie");
        } else if (industryEffect < 0) {
            string.append("\n  - ").append(industryEffect).append("% d'industrie");
        }
        if (agricultureEffect > 0) {
            string.append("\n  - ").append("+").append(agricultureEffect).append("% d'agriculture");
        } else if (agricultureEffect < 0) {
            string.append("\n  - ").append(agricultureEffect).append("% d'agriculture");
        }

        if (foodEffect > 0) {
            string.append("\n  - ").append("+").append(foodEffect).append(" nourriture");
        } else if (foodEffect < 0) {
            string.append("\n  - ").append(foodEffect).append(" nourriture");

        }
        if (financeEffect > 0) {
            string.append("\n  - ").append("+").append(financeEffect).append("$");
        } else if (financeEffect < 0) {
            string.append("\n  - ").append(financeEffect).append("$");
        }

        return string.toString();
    }

    public String toString(Difficulty difficulty) {
        StringBuilder string = new StringBuilder();
        string.append(choiceName).append("\n")
                .append("  Effets :");

        if (!factionEffects.isEmpty()) {
            string.append("\n");
            string.append(factionEffects.stream().map(f -> "  - " + f.toString(difficulty)).collect(Collectors.joining("\n")));
        }

        if (industryEffect > 0) {
            string.append("\n  - ").append("+")
                    .append(MathHelper.multiplyIntDoubleToFloor(industryEffect, difficulty.getGainMultiplier()))
                    .append("% d'industrie");
        } else if (industryEffect < 0) {
            string.append("\n  - ")
                    .append(MathHelper.multiplyIntDoubleToFloor(industryEffect, difficulty.getLoseMultiplier()))
                    .append("% d'industrie");
        }
        if (agricultureEffect > 0) {
            string.append("\n  - ").append("+")
                    .append(MathHelper.multiplyIntDoubleToFloor(agricultureEffect, difficulty.getGainMultiplier()))
                    .append("% d'agriculture");
        } else if (agricultureEffect < 0) {
            string.append("\n  - ")
                    .append(MathHelper.multiplyIntDoubleToFloor(agricultureEffect, difficulty.getLoseMultiplier()))
                    .append("% d'agriculture");
        }

        if (foodEffect > 0) {
            string.append("\n  - ").append("+")
                    .append(MathHelper.multiplyIntDoubleToFloor(foodEffect, difficulty.getGainMultiplier()))
                    .append(" nourriture");
        } else if (foodEffect < 0) {
            string.append("\n  - ")
                    .append(MathHelper.multiplyIntDoubleToFloor(foodEffect, difficulty.getLoseMultiplier()))
                    .append(" nourriture");

        }
        if (financeEffect > 0) {
            string.append("\n  - ").append("+")
                    .append(MathHelper.multiplyIntDoubleToFloor(financeEffect, difficulty.getGainMultiplier()))
                    .append("$");
        } else if (financeEffect < 0) {
            string.append("\n  - ")
                    .append(MathHelper.multiplyIntDoubleToFloor(financeEffect, difficulty.getLoseMultiplier()))
                    .append("$");
        }

        return string.toString();
    }
}
