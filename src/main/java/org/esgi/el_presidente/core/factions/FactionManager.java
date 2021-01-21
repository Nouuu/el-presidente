package org.esgi.el_presidente.core.factions;

import java.util.ArrayList;
import java.util.List;

public class FactionManager {
    private List<Faction> factionList;

    public FactionManager() {
    }

    public FactionManager initFactionList(int initialSatisfaction, int initialPartisan, int initialLoyalistSatisfaction, int initialLoyalistPartisan) {
        factionList = new ArrayList<>();

        for (FactionType factionType : FactionType.values()) {
            if (factionType.equals(FactionType.loyalist)) {
                factionList.add(new Faction(factionType, initialLoyalistSatisfaction, initialLoyalistPartisan));
            } else {
                factionList.add(new Faction(factionType, initialSatisfaction, initialPartisan));
            }
        }
        return this;
    }

    public double getGlobalSatisfaction() {
        double dividend = 0;
        double divider = 0;
        for (Faction f : factionList) {
            dividend += f.getPartisans() * f.getStatisfaction();
            divider += f.getPartisans();
        }
        return Math.round(dividend / divider * 100) / 100.;
    }

    public List<Faction> getFactionList() {
        return factionList;
    }

    public Faction getFaction(FactionType factionType) {
        return factionList.stream()
                .filter(faction -> faction.getFactionType().equals(factionType))
                .findFirst()
                .orElse(null);
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
