package org.esgi.el_presidente.core.factions;

public class Faction {
    private int statisfaction;
    private int partisans;
    private final FactionType factionType;

    public Faction(FactionType factionType, int statisfaction, int partisans) {
        this.factionType = factionType;
        this.statisfaction = Math.max(statisfaction, 0);
        this.partisans = Math.max(partisans, 0);
    }

    public FactionType getFactionType() {
        return factionType;
    }

    public int getStatisfaction() {
        return statisfaction;
    }

    public int getPartisans() {
        return partisans;
    }

    public void addStatisfaction(int statisfaction) {
        // Si une factiontombe à 0% de satisfaction, alors il ne sera plus possible de remonter ce pourcentage
        if (this.statisfaction > 0) {
            this.statisfaction = Math.max(Math.min(this.statisfaction + statisfaction, 100), 0);
        }
    }

    public void addPartisansPercent(int percent) {
        double diff = (double) partisans * ((double) percent / 100);
        this.partisans += Math.floor(diff);
    }

    public void addPartisans(int partisans) {
        this.partisans = Math.max(0, this.partisans + partisans);
    }
}
