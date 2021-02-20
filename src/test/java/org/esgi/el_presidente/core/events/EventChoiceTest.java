package org.esgi.el_presidente.core.events;

import org.assertj.core.api.Assertions;
import org.esgi.el_presidente.core.factions.FactionType;
import org.esgi.el_presidente.core.game.Difficulty;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class EventChoiceTest {

    private final String choiceName = "Event description";
    private final int industryEffect = 10;
    private final int agricultureEffect = -7;
    private final int foodEffect = 0;
    private final int financeEffect = 8;
    private final List<EventFactionEffect> factionEffects = new ArrayList<>();
    private final EventFactionEffect eventFactionEffect = new EventFactionEffect(FactionType.capitalist, 1, -2);
    private final EventFactionEffect eventFactionEffect2 = new EventFactionEffect(null, 11, 7);

    private final EventChoice eventChoice = new EventChoice(choiceName, industryEffect, agricultureEffect, foodEffect, financeEffect, factionEffects);

    @Before
    public void setUp() {
        factionEffects.clear();
        factionEffects.add(eventFactionEffect);
        factionEffects.add(eventFactionEffect2);
    }

    @Test
    public void addEventFactionEffect() {
        EventFactionEffect newEventFactionEffect = new EventFactionEffect(FactionType.ecologist, 11, 22);
        eventChoice.addEventFactionEffect(newEventFactionEffect);
        Assertions.assertThat(eventChoice.getFactionEffects()).containsOnlyOnce(newEventFactionEffect);
    }

    @Test
    public void getChoiceName() {
        Assertions.assertThat(eventChoice.getChoiceName()).isEqualTo(choiceName);
    }

    @Test
    public void getIndustryEffect() {
        Assertions.assertThat(eventChoice.getIndustryEffect()).isEqualTo(industryEffect);
    }

    @Test
    public void getAgricultureEffect() {
        Assertions.assertThat(eventChoice.getAgricultureEffect()).isEqualTo(agricultureEffect);
    }

    @Test
    public void getFoodEffect() {
        Assertions.assertThat(eventChoice.getFoodEffect()).isEqualTo(foodEffect);
    }

    @Test
    public void getFinanceEffect() {
        Assertions.assertThat(eventChoice.getFinanceEffect()).isEqualTo(financeEffect);
    }

    @Test
    public void getFactionEffects() {
        Assertions.assertThat(eventChoice.getFactionEffects()).containsOnlyOnceElementsOf(factionEffects);
    }

    @Test
    public void testToString() {
        String expected = "Event description\n" +
                "  Effets :\n" +
                "  - Capitalistes : +1% de partisans, -2% de satisfaction\n" +
                "  - Toute les factions : +11% de partisans, +7% de satisfaction\n" +
                "  - +10% d'industrie\n" +
                "  - -7% d'agriculture\n" +
                "  - +8$";
        Assertions.assertThat(eventChoice.toString()).isEqualTo(expected);
    }

    @Test
    public void testToString2() {
        String expected = "Event description\n" +
                "  Effets :\n" +
                "  - -3% d'industrie\n" +
                "  - +9% d'agriculture\n" +
                "  - -10 nourriture\n" +
                "  - -5$";

        String choiceName2 = "Event description";
        int industryEffect2 = -3;
        int agricultureEffect2 = 9;
        int foodEffect2 = -10;
        int financeEffect2 = -5;
        List<EventFactionEffect> factionEffects2 = new ArrayList<>();
        EventChoice eventChoice = new EventChoice(choiceName2, industryEffect2, agricultureEffect2, foodEffect2, financeEffect2, factionEffects2);

        Assertions.assertThat(eventChoice.toString()).isEqualTo(expected);
    }

    @Test
    public void testToStringDifficulty() {
        String expected = "Event description\n" +
                "  Effets :\n" +
                "  - Capitalistes : +0% de partisans, -3% de satisfaction\n" +
                "  - Toute les factions : +8% de partisans, +5% de satisfaction\n" +
                "  - +7% d'industrie\n" +
                "  - -11% d'agriculture\n" +
                "  - +6$";
        Difficulty difficulty = Difficulty.HARD;
        Assertions.assertThat(eventChoice.toString(difficulty)).isEqualTo(expected);
    }

    @Test
    public void testToStringDifficulty2() {
        String expected = "Event description\n" +
                "  Effets :\n" +
                "  - -5% d'industrie\n" +
                "  - +6% d'agriculture\n" +
                "  - -15 nourriture\n" +
                "  - -8$";

        Difficulty difficulty = Difficulty.HARD;
        String choiceName2 = "Event description";
        int industryEffect2 = -3;
        int agricultureEffect2 = 9;
        int foodEffect2 = -10;
        int financeEffect2 = -5;
        List<EventFactionEffect> factionEffects2 = new ArrayList<>();
        EventChoice eventChoice = new EventChoice(choiceName2, industryEffect2, agricultureEffect2, foodEffect2, financeEffect2, factionEffects2);

        Assertions.assertThat(eventChoice.toString(difficulty)).isEqualTo(expected);
    }
}