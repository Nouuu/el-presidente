package org.esgi.el_presidente;


import org.assertj.core.api.Assertions;
import org.esgi.el_presidente.core.factions.Faction;
import org.esgi.el_presidente.core.factions.FactionManager;
import org.esgi.el_presidente.core.factions.FactionType;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

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
}
