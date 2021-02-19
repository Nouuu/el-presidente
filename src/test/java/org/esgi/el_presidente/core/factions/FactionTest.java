package org.esgi.el_presidente.core.factions;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

public class FactionTest {

    @Test
    public void testCreationOfAllFactionsTypes() {
        FactionType[] factionTypes = FactionType.values();
        List<Faction> factionList = new ArrayList<>();

        for (FactionType factionType : factionTypes) {
            factionList.add(new Faction(factionType, 0, 0));
        }

        for (final FactionType factionType : factionTypes) {
            Assertions.assertThat(
                    factionList.stream().filter(faction -> faction.getFactionType().equals(factionType)).count())
                    .isEqualTo(1);
        }
    }

    @Test
    public void testCreationFactionInitialValues() {
        FactionType factionType = FactionType.capitalist;
        int initialSatisfaction = 50;
        int initialPartisans = 15;

        Faction faction = new Faction(factionType, initialSatisfaction, initialPartisans);
        Assertions.assertThat(faction.getFactionType()).isEqualTo(factionType);
        Assertions.assertThat(faction.getSatisfaction()).isEqualTo(initialSatisfaction);
        Assertions.assertThat(faction.getPartisans()).isEqualTo(initialPartisans);
    }

    @Test
    public void testFactionAddSatisfaction() {
        FactionType factionType = FactionType.capitalist;
        int initialSatisfaction = 50;
        int addSatisfaction = 10;
        int initialPartisans = 15;

        Faction faction = new Faction(factionType, initialSatisfaction, initialPartisans);
        faction.updateSatisfaction(addSatisfaction);
        Assertions.assertThat(faction.getSatisfaction()).isEqualTo(initialSatisfaction + addSatisfaction);
    }

    @Test
    public void testFactionAddSatisfactionAbove100() {
        FactionType factionType = FactionType.capitalist;
        int initialSatisfaction = 50;
        int addSatisfaction = 80;
        int initialPartisans = 15;

        Faction faction = new Faction(factionType, initialSatisfaction, initialPartisans);
        faction.updateSatisfaction(addSatisfaction);
        Assertions.assertThat(faction.getSatisfaction()).isEqualTo(100);
    }

    @Test
    public void testFactionAddSatisfactionBelow0() {
        FactionType factionType = FactionType.capitalist;
        int initialSatisfaction = 50;
        int addSatisfaction = -80;
        int initialPartisans = 15;

        Faction faction = new Faction(factionType, initialSatisfaction, initialPartisans);
        faction.updateSatisfaction(addSatisfaction);
        Assertions.assertThat(faction.getSatisfaction()).isEqualTo(0);
    }

    @Test
    public void testFactionAddSatisfactionStuckAt0() {
        FactionType factionType = FactionType.capitalist;
        int initialSatisfaction = 0;
        int addSatisfaction = 80;
        int initialPartisans = 15;

        Faction faction = new Faction(factionType, initialSatisfaction, initialPartisans);
        faction.updateSatisfaction(addSatisfaction);
        Assertions.assertThat(faction.getSatisfaction()).isEqualTo(0);
    }

    @Test
    public void testAddStatisfactionPercent() {
        FactionType factionType = FactionType.capitalist;
        int initialSatisfaction = 60;
        int initialPartisans = 15;
        Faction faction = new Faction(factionType, initialSatisfaction, initialPartisans);
        faction.addSatisfactionPercent(10);
        Assertions.assertThat(faction.getSatisfaction()).isEqualTo(66);
    }

    @Test
    public void testAddStatisfactionPercentForFactionStuckAt0() {
        FactionType factionType = FactionType.capitalist;
        int initialSatisfaction = 0;
        int initialPartisans = 15;
        Faction faction = new Faction(factionType, initialSatisfaction, initialPartisans);
        faction.addSatisfactionPercent(100);
        Assertions.assertThat(faction.getSatisfaction()).isEqualTo(0);
    }

    @Test
    public void testRemoveSatisfaction() {
        FactionType factionType = FactionType.capitalist;
        int initialSatisfaction = 15;
        int initialPartisans = 15;
        Faction faction = new Faction(factionType, initialSatisfaction, initialPartisans);
        faction.updateSatisfaction(-15);
        Assertions.assertThat(faction.getSatisfaction()).isEqualTo(0);
    }

    @Test
    public void testFactionAddPartisans() {
        FactionType factionType = FactionType.capitalist;
        int initialSatisfaction = 50;
        int initialPartisans = 15;
        int addPartisans = 10;

        Faction faction = new Faction(factionType, initialSatisfaction, initialPartisans);
        faction.updatePartisans(addPartisans);
        Assertions.assertThat(faction.getPartisans()).isEqualTo(initialPartisans + addPartisans);
    }

    @Test
    public void testFactionAddPartisansBelow0() {
        FactionType factionType = FactionType.capitalist;
        int initialSatisfaction = 50;
        int initialPartisans = 15;
        int addPartisans = -80;

        Faction faction = new Faction(factionType, initialSatisfaction, initialPartisans);
        faction.updatePartisans(addPartisans);
        Assertions.assertThat(faction.getPartisans()).isEqualTo(0);
    }

    @Test
    public void testFactionAddPartisansPercent() {
        FactionType factionType = FactionType.capitalist;
        int initialSatisfaction = 50;
        int initialPartisans = 50;
        int addPartisansPercent = 20;

        Faction faction = new Faction(factionType, initialSatisfaction, initialPartisans);
        faction.updatePartisansPercent(addPartisansPercent);
        Assertions.assertThat(faction.getPartisans()).isEqualTo(60);
        faction.updatePartisansPercent(-2 * addPartisansPercent);
        Assertions.assertThat(faction.getPartisans()).isEqualTo(36);
    }

    @Test
    public void testGetType() {
        Faction capitalist = new Faction(FactionType.capitalist, 100, 15);
        Faction loyalist = new Faction(FactionType.loyalist, 100, 15);
        Faction ecologist = new Faction(FactionType.ecologist, 100, 15);
        assertEquals("capitalistes", capitalist.getType());
        assertEquals("loyalistes", loyalist.getType());
        assertEquals("écologistes", ecologist.getType());
    }

}
