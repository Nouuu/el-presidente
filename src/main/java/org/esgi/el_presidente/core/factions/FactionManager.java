package org.esgi.el_presidente.core.factions;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

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
        double dividend = factionList.stream().mapToDouble(f -> f.getPartisans() * f.getStatisfaction()).sum();
        double divider = getTotalPartisan();

        return Math.round(dividend / divider * 100) / 100.;
    }

    public List<Faction> getFactionList() {
        return factionList;
    }

    public Faction getFaction(FactionType factionType) throws IllegalArgumentException {
        return factionList.stream()
                .filter(faction -> faction.getFactionType().equals(factionType))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Can't find " + factionType + " in factionList"));
    }

    public int getTotalPartisan() {
        return factionList.stream().mapToInt(Faction::getPartisans).sum();
    }

    public void addFactionSatisfaction(FactionType factionType, int satisfaction) {
        getFaction(factionType).addStatisfaction(satisfaction);
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
