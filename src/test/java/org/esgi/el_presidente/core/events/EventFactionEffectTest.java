package org.esgi.el_presidente.core.events;

import org.apache.commons.lang3.StringUtils;
import org.assertj.core.api.Assertions;
import org.esgi.el_presidente.core.factions.FactionType;
import org.esgi.el_presidente.core.game.Difficulty;
import org.esgi.el_presidente.core.helper.MathHelper;
import org.junit.Test;

public class EventFactionEffectTest {
    @Test
    public void getFactionType() {
        FactionType factionType = FactionType.capitalist;
        EventFactionEffect eventFactionEffect = new EventFactionEffect(factionType, 0, 0);
        Assertions.assertThat(eventFactionEffect.getFactionType()).isEqualTo(factionType);
    }

    @Test
    public void getFactionTypeNull() {
        EventFactionEffect eventFactionEffect = new EventFactionEffect(null, 0, 0);
        Assertions.assertThat(eventFactionEffect.getFactionType()).isNull();
    }

    @Test
    public void getPartisansPercentEffect() {
        int partisansPercentEffect = 58;
        EventFactionEffect eventFactionEffect = new EventFactionEffect(FactionType.capitalist, partisansPercentEffect,
                0);
        Assertions.assertThat(eventFactionEffect.getPartisansPercentEffect()).isEqualTo(partisansPercentEffect);
    }

    @Test
    public void getSatisfactionEffect() {
        int satisfactionEffect = 27;
        EventFactionEffect eventFactionEffect = new EventFactionEffect(FactionType.capitalist, 0, satisfactionEffect);
        Assertions.assertThat(eventFactionEffect.getSatisfactionEffect()).isEqualTo(satisfactionEffect);
    }

    @Test
    public void effectAboveBelowPercentLimit() {
        EventFactionEffect eventFactionEffect = new EventFactionEffect(FactionType.capitalist, -128, 128);
        Assertions.assertThat(eventFactionEffect.getSatisfactionEffect()).isEqualTo(100);
        Assertions.assertThat(eventFactionEffect.getPartisansPercentEffect()).isEqualTo(-100);
    }

    @Test
    public void testToString() {
        FactionType factionType = FactionType.capitalist;
        int partisansPercentEffect = 58;
        int satisfactionEffect = 27;

        String expectedString = StringUtils.capitalize(factionType.toString()) + " : " + "+" + partisansPercentEffect
                + "% de partisans, " + "+" + satisfactionEffect + "% de satisfaction";
        EventFactionEffect eventFactionEffect = new EventFactionEffect(factionType, partisansPercentEffect,
                satisfactionEffect);

        Assertions.assertThat(eventFactionEffect.toString()).isEqualTo(expectedString);
    }

    @Test
    public void testToStringNegative() {
        FactionType factionType = FactionType.capitalist;
        int partisansPercentEffect = -58;
        int satisfactionEffect = -27;

        String expectedString = StringUtils.capitalize(factionType.toString()) + " : " + partisansPercentEffect
                + "% de partisans, " + satisfactionEffect + "% de satisfaction";
        EventFactionEffect eventFactionEffect = new EventFactionEffect(factionType, partisansPercentEffect,
                satisfactionEffect);

        Assertions.assertThat(eventFactionEffect.toString()).isEqualTo(expectedString);
    }

    @Test
    public void testToStringSpecial() {
        String expectedString = "Toute les factions : ";
        EventFactionEffect eventFactionEffect = new EventFactionEffect(null, 0, 0);

        Assertions.assertThat(eventFactionEffect.toString()).isEqualTo(expectedString);
        Assertions.assertThat(eventFactionEffect.toString(Difficulty.EASY)).isEqualTo(expectedString);
    }

    @Test
    public void testToStringDifficulty() {
        FactionType factionType = FactionType.capitalist;
        int partisansPercentEffect = 58;
        int satisfactionEffect = 27;
        Difficulty difficulty = Difficulty.EASY;

        String expectedString = StringUtils.capitalize(factionType.toString()) + " : " + "+"
                + MathHelper.multiplyIntDoubleToFloor(partisansPercentEffect, difficulty.getGainMultiplier())
                + "% de partisans, " + "+"
                + MathHelper.multiplyIntDoubleToFloor(satisfactionEffect, difficulty.getGainMultiplier())
                + "% de satisfaction";
        EventFactionEffect eventFactionEffect = new EventFactionEffect(factionType, partisansPercentEffect,
                satisfactionEffect);

        Assertions.assertThat(eventFactionEffect.toString(difficulty)).isEqualTo(expectedString);
    }

    @Test
    public void testToStringNegativeDifficulty() {
        FactionType factionType = FactionType.capitalist;
        int partisansPercentEffect = -58;
        int satisfactionEffect = -27;
        Difficulty difficulty = Difficulty.EASY;

        String expectedString = StringUtils.capitalize(factionType.toString()) + " : "
                + MathHelper.multiplyIntDoubleToFloor(partisansPercentEffect, difficulty.getLoseMultiplier())
                + "% de partisans, "
                + MathHelper.multiplyIntDoubleToFloor(satisfactionEffect, difficulty.getLoseMultiplier())
                + "% de satisfaction";
        EventFactionEffect eventFactionEffect = new EventFactionEffect(factionType, partisansPercentEffect,
                satisfactionEffect);

        Assertions.assertThat(eventFactionEffect.toString(difficulty)).isEqualTo(expectedString);

    }
}