package org.esgi.el_presidente.core.factions;

import java.util.List;

public class FactionManager {
    private List<Faction> factionList;

    public FactionManager() {
    }

    public double getGlobalSatisfaction() {
        return 0;
    }

    public void initFactionList(int initialSatisfaction, int initialPartisans, int initialLoyalistSatisfaction, int initialLoyalistPartisan) {

    }

    public List<Faction> getFactionList() {
        return null;
    }

    public Faction getFaction(FactionType factionType) {
        return null;
    }

    public int getTotalPartisan() {
        return 0;
    }

    public void addFactionSatisfaction(FactionType factionType, int statisfaction) {

    }

    public void addAllFactionSatisfaction(int statisfaction) {

    }

    public void addFactionPartisan(FactionType factionType, int partisans) {

    }

    public void addFactionPartisanPercent(FactionType factionType, int partisansPercent) {

    }

    public void addAllFactionsPartisan(int partisans) {

    }

    public void addAllFactionsPartisanPercent(int partisansPercent) {

    }

    public void removeRandomlyFactionPartisans(int partisans) {

    }

}
