package org.esgi.el_presidente.core.factions;

import org.assertj.core.api.Assertions;
import org.esgi.el_presidente.core.factions.Faction;
import org.esgi.el_presidente.core.factions.FactionManager;
import org.esgi.el_presidente.core.factions.FactionType;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.offset;

public class FactionManagerTest {

    @Test
    public void testFactionManagerInit() {
        int initialPartisan = 15;
        int initialSatisfaction = 60;
        int initialLoyalistPartisan = 17;
        int initialLoyalistSatisfaction = 100;

        FactionManager factionManager = new FactionManager()
            .initFactionList(initialSatisfaction, initialPartisan, initialLoyalistSatisfaction, initialLoyalistPartisan);

        List<Faction> factionList = factionManager.getFactionList();
        Assertions.assertThat(factionList.size()).isEqualTo(Arrays.stream(FactionType.values()).count());

        for (Faction faction : factionList) {
            if (faction.getFactionType().equals(FactionType.loyalist)) {
                Assertions.assertThat(faction.getSatisfaction()).isEqualTo(initialLoyalistSatisfaction);
                Assertions.assertThat(faction.getPartisans()).isEqualTo(initialLoyalistPartisan);
            } else {
                Assertions.assertThat(faction.getSatisfaction()).isEqualTo(initialSatisfaction);
                Assertions.assertThat(faction.getPartisans()).isEqualTo(initialPartisan);
            }
        }
    }

    @Test
    public void testFactionManagerGetLoyalistFaction() {
        int initialPartisan = 15;
        int initialSatisfaction = 60;
        int initialLoyalistPartisan = 17;
        int initialLoyalistSatisfaction = 100;

        FactionManager factionManager = new FactionManager()
            .initFactionList(initialSatisfaction, initialPartisan, initialLoyalistSatisfaction, initialLoyalistPartisan);

        Faction faction = factionManager.getFaction(FactionType.loyalist);
        Assertions.assertThat(faction.getFactionType()).isEqualTo(FactionType.loyalist);
        Assertions.assertThat(faction.getPartisans()).isEqualTo(initialLoyalistPartisan);
        Assertions.assertThat(faction.getSatisfaction()).isEqualTo(initialLoyalistSatisfaction);
    }

    @Test
    public void testFactionManagerGetInexistantFaction() {
        int initialPartisan = 15;
        int initialSatisfaction = 60;
        int initialLoyalistPartisan = 17;
        int initialLoyalistSatisfaction = 100;

        FactionManager factionManager = new FactionManager()
            .initFactionList(initialSatisfaction, initialPartisan, initialLoyalistSatisfaction, initialLoyalistPartisan);

        Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> factionManager.getFaction(null));
    }

    @Test
    public void testFactionManagerGetGlobalSatisfaction() {
        int initialPartisan = 15;
        int initialSatisfaction = 60;
        int initialLoyalistPartisan = 17;
        int initialLoyalistSatisfaction = 100;
        double globalSatisfaction = 66.36;

        FactionManager factionManager = new FactionManager()
        .initFactionList(initialSatisfaction, initialPartisan, initialLoyalistSatisfaction, initialLoyalistPartisan);

        Assertions.assertThat(factionManager.getGlobalSatisfaction()).isEqualTo(globalSatisfaction, offset(0.001));
    }

    @Test
    public void testFactionManagerGetTotalPartisan() {
        int initialPartisan = 15;
        int initialSatisfaction = 60;
        int initialLoyalistPartisan = 17;
        int initialLoyalistSatisfaction = 100;
        int totalPartisan = 107;

        FactionManager factionManager = new FactionManager()
        .initFactionList(initialSatisfaction, initialPartisan, initialLoyalistSatisfaction, initialLoyalistPartisan);

        Assertions.assertThat(factionManager.getTotalPartisan()).isEqualTo(totalPartisan);
    }

    @Test
    public void testFactionManagerAddFactionSatisfaction() {
        int initialPartisan = 15;
        int initialSatisfaction = 60;
        int initialLoyalistPartisan = 17;
        int initialLoyalistSatisfaction = 100;
        int addSatisfaction = 28;
        FactionType factionToAddSatisfaction = FactionType.communist;

        FactionManager factionManager = new FactionManager()
        .initFactionList(initialSatisfaction, initialPartisan, initialLoyalistSatisfaction, initialLoyalistPartisan);

        factionManager.addFactionSatisfaction(factionToAddSatisfaction, addSatisfaction);
        Assertions.assertThat(factionManager.getFaction(factionToAddSatisfaction).getSatisfaction())
                .isEqualTo(initialSatisfaction + addSatisfaction);
    }

    @Test
    public void testFactionManagerAddAllFactionSatisfaction() {
        int initialPartisan = 15;
        int initialSatisfaction = 60;
        int initialLoyalistPartisan = 15;
        int initialLoyalistSatisfaction = 60;
        int addSatisfaction = 28;

        FactionManager factionManager = new FactionManager().initFactionList(initialSatisfaction, initialPartisan, initialLoyalistSatisfaction, initialLoyalistPartisan);

        factionManager.addAllFactionSatisfaction(addSatisfaction);

        factionManager.getFactionList().forEach(
                f -> Assertions.assertThat(f.getSatisfaction()).isEqualTo(initialSatisfaction + addSatisfaction));
    }

    @Test
    public void testFactionManagerAddFactionPartisan() {
        int initialPartisan = 15;
        int initialSatisfaction = 60;
        int initialLoyalistPartisan = 15;
        int initialLoyalistSatisfaction = 60;
        int factionAddPartisan = 18;
        FactionType factionToAddPartisan = FactionType.communist;

        FactionManager factionManager = new FactionManager()
        .initFactionList(initialSatisfaction, initialPartisan, initialLoyalistSatisfaction, initialLoyalistPartisan);

        factionManager.addFactionPartisan(factionToAddPartisan, factionAddPartisan);
        Assertions.assertThat(factionManager.getFaction(factionToAddPartisan).getPartisans())
                .isEqualTo(initialPartisan + factionAddPartisan);
    }

    @Test
    public void testFactionManagerAddFactionPartisanPercent() {
        int initialPartisan = 15;
        int initialSatisfaction = 60;
        int initialLoyalistPartisan = 15;
        int initialLoyalistSatisfaction = 60;
        int factionAddPartisanPercent = 18;
        FactionType factionToAddPartisan = FactionType.communist;

        FactionManager factionManager = new FactionManager()
        .initFactionList(initialSatisfaction, initialPartisan, initialLoyalistSatisfaction, initialLoyalistPartisan);

        factionManager.addFactionPartisanPercent(factionToAddPartisan, factionAddPartisanPercent);
        Assertions.assertThat(factionManager.getFaction(factionToAddPartisan).getPartisans()).isEqualTo(17);

        factionManager.addFactionPartisanPercent(factionToAddPartisan, -2 * factionAddPartisanPercent);
        Assertions.assertThat(factionManager.getFaction(factionToAddPartisan).getPartisans()).isEqualTo(10);
    }

    @Test
    public void testFactionManagerAddAllFactionPartisan() {
        int initialPartisan = 15;
        int initialSatisfaction = 60;
        int initialLoyalistPartisan = 15;
        int initialLoyalistSatisfaction = 60;
        int factionAddPartisan = 18;

        FactionManager factionManager = new FactionManager()
        .initFactionList(initialSatisfaction, initialPartisan, initialLoyalistSatisfaction, initialLoyalistPartisan);

        factionManager.addAllFactionsPartisan(factionAddPartisan);

        factionManager.getFactionList()
                .forEach(f -> Assertions.assertThat(f.getPartisans()).isEqualTo(initialPartisan + factionAddPartisan));

    }

    @Test
    public void testFactionManagerAddAllFactionPartisanPercent() {
        int initialPartisan = 15;
        int initialSatisfaction = 60;
        int initialLoyalistPartisan = 15;
        int initialLoyalistSatisfaction = 60;
        int factionAddPartisanPercent = 18;

        FactionManager factionManager = new FactionManager()
        .initFactionList(initialSatisfaction, initialPartisan, initialLoyalistSatisfaction, initialLoyalistPartisan);

        factionManager.addAllFactionsPartisanPercent(factionAddPartisanPercent);

        factionManager.getFactionList().forEach(f -> Assertions.assertThat(f.getPartisans()).isEqualTo(17));
    }

    @Test
    public void testFactionManagerRemoveRandomlyFactionPartisans() {
        int initialPartisan = 15;
        int initialSatisfaction = 60;
        int initialLoyalistPartisan = 15;
        int initialLoyalistSatisfaction = 60;
        int removeRandomlyPartisan = 18;

        FactionManager factionManager = new FactionManager()
        .initFactionList(initialSatisfaction, initialPartisan, initialLoyalistSatisfaction, initialLoyalistPartisan);

        factionManager.removeRandomlyFactionPartisans(removeRandomlyPartisan);

        Assertions.assertThat(factionManager.getTotalPartisan())
                .isEqualTo(initialPartisan * 7 - removeRandomlyPartisan);
    }

}
