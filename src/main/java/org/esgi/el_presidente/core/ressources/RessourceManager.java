package org.esgi.el_presidente.core.ressources;

import org.esgi.el_presidente.core.factions.Faction;
import org.esgi.el_presidente.core.factions.FactionType;
import org.esgi.el_presidente.core.game.Difficulty;
import org.esgi.el_presidente.core.helper.MathHelper;

public class RessourceManager {

    private Finances finances;
    private int foodReserves;
    private Faction loyalist;
    private Agriculture agriculture;
    private Industry industry;
    private Difficulty difficulty;

    public RessourceManager(Faction loyalist, int money, int foodReserves, Agriculture agriculture, Industry industry,
            Difficulty difficulty) {
        finances = new Finances(money, difficulty.getLoseMultiplier());
        this.foodReserves = foodReserves;
        this.loyalist = loyalist;
        this.industry = industry;
        this.agriculture = agriculture;
        this.difficulty = difficulty;
    }

    public void buyFood(int unitOfFood) throws Exception {
        try {
            finances.buyFood(unitOfFood);
            foodReserves += unitOfFood;
        } catch (Exception e) {
            throw new Exception("Can't buy Food");
        }
    }

    public void buyBribe(Faction faction) throws Exception {
        if (faction.getFactionType() == FactionType.loyalist) {
            throw new Exception("Try to buy loyalist");
        }
        try {
            int price = finances.buyBribe(faction.getPartisans());
            int loyalistSatisfactionLost = MathHelper.divideIntDoubleToFloor(price, 10);

            loyalist.updateSatisfaction(-loyalistSatisfactionLost);
            faction.updateSatisfaction(10);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void updateSizeOfAgriculture(int additionalSize) {
        try {
            int freeTerrain = getMaxSizeForAgriculture();
            int currentSize = getAgriculturePart();
            int newSize = MathHelper.restrictValue(additionalSize + currentSize, 0, freeTerrain);
            agriculture.setSize(newSize);
        } catch (Exception e) {
            throw new Error("cannot grow as expected");
        }
    }

    public void updateSizeOfIndustry(int additionalSize) {
        try {
            int freeTerrain = getMaxSizeForIndustry();
            int currentSize = getIndustryPart();
            int newSize = MathHelper.restrictValue(additionalSize + currentSize, 0, freeTerrain);
            industry.setSize(newSize);
        } catch (Exception e) {
            throw new Error("cannot grow as expected");
        }
    }

    public void handleMoneyAction(int moneyImpact) {
        finances.handleMoneyAction(moneyImpact);
    }

    public void handleFoodAction(int foodImpact) {
        int newFood = foodReserves + foodImpact;
        foodReserves = Math.max(0, newFood);
    }

    public int getMoney() {
        return finances.getMoneyInCoffers();
    }

    public int getFoodReserves() {
        return foodReserves;
    }

    public void triggerEndOfYearAction() {
        int moneyProduced = industry.yearlyProductionOfMoney();
        int foodProduced = agriculture.yearlyProductionOfFood();
        foodReserves += foodProduced;
        finances.handleMoneyAction(moneyProduced);
    }

    public int getAgriculturePart() {
        return agriculture.getSize();
    }

    public int getIndustryPart() {
        return industry.getSize();
    }

    public int getEndOfYearMoneyProduction() {
        return industry.yearlyProductionOfMoney();
    }

    public int getEndOfYearFoodProduction() {
        return agriculture.yearlyProductionOfFood();
    }

    public int getFoodPrice() {
        return finances.getFoodPrice();
    }

    private int getMaxSizeForAgriculture() {
        return Math.min(100, 100 - getIndustryPart());
    }

    private int getMaxSizeForIndustry() {
        return Math.min(100, 100 - getAgriculturePart());
    }

    public int getBrideCost(int partisansCount) {
        return finances.getBrideCost(partisansCount);
    }

}
