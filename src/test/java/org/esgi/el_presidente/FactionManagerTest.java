package org.esgi.el_presidente;


import org.assertj.core.api.Assertions;
import org.assertj.core.data.Offset;
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
                Assertions.assertThat(faction.getStatisfaction()).isEqualTo(initialLoyalistSatisfaction);
                Assertions.assertThat(faction.getPartisans()).isEqualTo(initialLoyalistPartisan);
            } else {
                Assertions.assertThat(faction.getStatisfaction()).isEqualTo(initialSatisfaction);
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
        Assertions.assertThat(faction.getStatisfaction()).isEqualTo(initialLoyalistSatisfaction);
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
        int addStatisfaction = 28;
        FactionType factionToAddSatisfaction = FactionType.communist;

        FactionManager factionManager = new FactionManager()
                .initFactionList(initialSatisfaction, initialPartisan, initialLoyalistSatisfaction, initialLoyalistPartisan);

        factionManager.addFactionSatisfaction(factionToAddSatisfaction, addStatisfaction);
        Assertions.assertThat(factionManager.getFaction(factionToAddSatisfaction).getStatisfaction())
                .isEqualTo(initialSatisfaction + addStatisfaction);
    }


}
