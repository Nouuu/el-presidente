package org.esgi.el_presidente.core.factions;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FactionManager {
    private List<Faction> factionList;

    public FactionManager() {
    }

    public FactionManager initFactionList(int initialSatisfaction, int initialPartisan, int initialLoyalistSatisfaction,
                                          int initialLoyalistPartisan) {
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

    public int getGlobalSatisfaction() {
        double dividend = factionList.stream().mapToDouble(f -> f.getPartisans() * f.getSatisfaction()).sum();
        double divider = getTotalPartisan();

        return (int) Math.round(dividend / divider);
    }

    public List<Faction> getFactionList() {
        return factionList;
    }

    public Faction getFaction(FactionType factionType) throws IllegalArgumentException {
        return factionList.stream().filter(faction -> faction.getFactionType().equals(factionType)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Can't find " + factionType + " in factionList"));
    }

    public int getTotalPartisan() {
        return factionList.stream().mapToInt(Faction::getPartisans).sum();
    }

    public void addFactionSatisfaction(FactionType factionType, int satisfaction) {
        if (factionType == null) {
            addAllFactionSatisfaction(satisfaction);
        } else {
            getFaction(factionType).updateSatisfaction(satisfaction);
        }
    }

    public void addAllFactionSatisfaction(int satisfaction) {
        factionList.forEach(f -> f.updateSatisfaction(satisfaction));
    }

    public void addFactionPartisan(FactionType factionType, int partisans) {
        if (factionType == null) {
            addAllFactionsPartisan(partisans);
        } else {
            getFaction(factionType).updatePartisans(partisans);
        }
    }

    public void addFactionPartisanPercent(FactionType factionType, int partisansPercent) {
        if (factionType == null) {
            addAllFactionsPartisanPercent(partisansPercent);
        } else {
            getFaction(factionType).updatePartisansPercent(partisansPercent);
        }
    }

    public void addAllFactionsPartisan(int partisans) {
        factionList.forEach(f -> f.updatePartisans(partisans));
    }

    public void addAllFactionsPartisanPercent(int partisansPercent) {
        factionList.forEach(f -> f.updatePartisansPercent(partisansPercent));
    }

    public void removeRandomlyFactionPartisans(int partisansToRemove) {
        Random rand = new Random();
        while (partisansToRemove > 0) {
            factionList.get(rand.nextInt(factionList.size())).updatePartisans(-1);
            partisansToRemove--;
        }
    }
}
