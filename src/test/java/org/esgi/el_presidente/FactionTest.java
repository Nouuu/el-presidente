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
        faction.addSatisfaction(addSatisfaction);
        Assertions.assertThat(faction.getSatisfaction()).isEqualTo(initialSatisfaction + addSatisfaction);
    }

    @Test
    public void testFactionAddSatisfactionAbove100() {
        FactionType factionType = FactionType.capitalist;
        int initialSatisfaction = 50;
        int addSatisfaction = 80;
        int initialPartisans = 15;

        Faction faction = new Faction(factionType, initialSatisfaction, initialPartisans);
        faction.addSatisfaction(addSatisfaction);
        Assertions.assertThat(faction.getSatisfaction()).isEqualTo(100);
    }

    @Test
    public void testFactionAddSatisfactionBelow0() {
        FactionType factionType = FactionType.capitalist;
        int initialSatisfaction = 50;
        int addSatisfaction = -80;
        int initialPartisans = 15;

        Faction faction = new Faction(factionType, initialSatisfaction, initialPartisans);
        faction.addSatisfaction(addSatisfaction);
        Assertions.assertThat(faction.getSatisfaction()).isEqualTo(0);
    }

    @Test
    public void testFactionAddSatisfactionStuckAt0() {
        FactionType factionType = FactionType.capitalist;
        int initialSatisfaction = 0;
        int addSatisfaction = 80;
        int initialPartisans = 15;

        Faction faction = new Faction(factionType, initialSatisfaction, initialPartisans);
        faction.addSatisfaction(addSatisfaction);
        Assertions.assertThat(faction.getSatisfaction()).isEqualTo(0);
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
