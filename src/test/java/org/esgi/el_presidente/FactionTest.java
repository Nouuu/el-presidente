package org.esgi.el_presidente;

import org.assertj.core.api.Assertions;
import org.esgi.el_presidente.core.factions.Faction;
import org.esgi.el_presidente.core.factions.FactionType;
import org.junit.Test;

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
            Assertions.assertThat(factionList.stream().filter(faction -> faction.getFactionType().equals(factionType)).count()).isEqualTo(1);
        }
    }

    @Test
    public void testCreationFactionInitialValues() {
        FactionType factionType = FactionType.capitalist;
        int initialSatisfaction = 50;
        int initialPartisans = 15;

        Faction faction = new Faction(factionType, initialSatisfaction, initialPartisans);
        Assertions.assertThat(faction.getFactionType()).isEqualTo(factionType);
        Assertions.assertThat(faction.getStatisfaction()).isEqualTo(initialSatisfaction);
        Assertions.assertThat(faction.getPartisans()).isEqualTo(initialPartisans);
    }

    @Test
    public void testFactionAddStatisfaction() {
        FactionType factionType = FactionType.capitalist;
        int initialSatisfaction = 50;
        int addStatisfaction = 10;
        int initialPartisans = 15;

        Faction faction = new Faction(factionType, initialSatisfaction, initialPartisans);
        faction.addStatisfaction(addStatisfaction);
        Assertions.assertThat(faction.getStatisfaction()).isEqualTo(initialSatisfaction + addStatisfaction);
    }

    @Test
    public void testFactionAddStatisfactionAbove100() {
        FactionType factionType = FactionType.capitalist;
        int initialSatisfaction = 50;
        int addStatisfaction = 80;
        int initialPartisans = 15;

        Faction faction = new Faction(factionType, initialSatisfaction, initialPartisans);
        faction.addStatisfaction(addStatisfaction);
        Assertions.assertThat(faction.getStatisfaction()).isEqualTo(100);
    }

    @Test
    public void testFactionAddStatisfactionBelow0() {
        FactionType factionType = FactionType.capitalist;
        int initialSatisfaction = 50;
        int addStatisfaction = -80;
        int initialPartisans = 15;

        Faction faction = new Faction(factionType, initialSatisfaction, initialPartisans);
        faction.addStatisfaction(addStatisfaction);
        Assertions.assertThat(faction.getStatisfaction()).isEqualTo(0);
    }

    @Test
    public void testFactionAddStatisfactionStuckAt0() {
        FactionType factionType = FactionType.capitalist;
        int initialSatisfaction = 0;
        int addStatisfaction = 80;
        int initialPartisans = 15;

        Faction faction = new Faction(factionType, initialSatisfaction, initialPartisans);
        faction.addStatisfaction(addStatisfaction);
        Assertions.assertThat(faction.getStatisfaction()).isEqualTo(0);
    }

    @Test
    public void testFactionAddPartisans() {
        FactionType factionType = FactionType.capitalist;
        int initialSatisfaction = 50;
        int initialPartisans = 15;
        int addPartisans = 10;

        Faction faction = new Faction(factionType, initialSatisfaction, initialPartisans);
        faction.addPartisans(addPartisans);
        Assertions.assertThat(faction.getPartisans()).isEqualTo(initialPartisans + addPartisans);
    }

    @Test
    public void testFactionAddPartisansBelow0() {
        FactionType factionType = FactionType.capitalist;
        int initialSatisfaction = 50;
        int initialPartisans = 15;
        int addPartisans = -80;

        Faction faction = new Faction(factionType, initialSatisfaction, initialPartisans);
        faction.addPartisans(addPartisans);
        Assertions.assertThat(faction.getPartisans()).isEqualTo(0);
    }

    @Test
    public void testFactionAddPartisansPercent() {
        FactionType factionType = FactionType.capitalist;
        int initialSatisfaction = 50;
        int initialPartisans = 50;
        int addPartisansPercent = 20;

        Faction faction = new Faction(factionType, initialSatisfaction, initialPartisans);
        faction.addPartisansPercent(addPartisansPercent);
        Assertions.assertThat(faction.getPartisans()).isEqualTo(60);
        faction.addPartisansPercent(-2 * addPartisansPercent);
        Assertions.assertThat(faction.getPartisans()).isEqualTo(36);
    }

}
