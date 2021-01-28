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

    /**
     * TODO add test
     * 
     */
    public void addSatisfactionPercent(int additionalSatisfactionPercent) {
        satisfaction += (additionalSatisfactionPercent / 100) * satisfaction;
    }

    public void addSatisfaction(int additionalSatisfaction) {
        // Si une faction tombe à 0% de satisfaction, alors il ne sera plus possible de
        // remonter ce pourcentage
        if (this.satisfaction <= 0) {
            return;
        }
        this.satisfaction = Math.max(Math.min(this.satisfaction + additionalSatisfaction, 100), 0);
    }

    /**
     * TODO add test
     */
    public void removeSatisfaction(int satisfaction) {
        addPartisans(satisfaction);
    }

    public void addPartisansPercent(int percent) {
        double diff = (double) partisans * ((double) percent / 100);
        this.partisans += Math.floor(diff);
    }

    /**
     * TODO throw error when partisans is negative
     */
    public void addPartisans(int partisans) {
        this.partisans = Math.max(0, this.partisans + partisans);
    }

    /**
     * TODO add test
     */
    public String getType() {
        return factionType.toString();
    }
}
