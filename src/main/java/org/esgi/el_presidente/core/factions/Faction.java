package org.esgi.el_presidente.core.factions;

public class Faction {
    private int satisfaction;
    private int partisans;
    private final FactionType factionType;

    public Faction(FactionType factionType, int satisfaction, int partisans) {
        this.factionType = factionType;
        this.satisfaction = Math.max(satisfaction, 0);
        this.partisans = Math.max(partisans, 0);
    }

    public FactionType getFactionType() {
        return factionType;
    }

    public int getSatisfaction() {
        return satisfaction;
    }

    public int getPartisans() {
        return partisans;
    }

    public void addSatisfaction(int additionalSatisfaction) {
        // Si une faction tombe à 0% de satisfaction, alors il ne sera plus possible de
        // remonter ce pourcentage
        if (this.satisfaction <= 0) {
            return;
        }
        this.satisfaction = Math.max(Math.min(this.satisfaction + additionalSatisfaction, 100), 0);
    }

    public void addPartisansPercent(int percent) {
        double diff = (double) partisans * ((double) percent / 100);
        this.partisans += Math.floor(diff);
    }

    public void addPartisans(int partisans) {
        this.partisans = Math.max(0, this.partisans + partisans);
    }
}
