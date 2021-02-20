package org.esgi.el_presidente.core.ressources;

import org.esgi.el_presidente.core.factions.Faction;
import org.esgi.el_presidente.core.factions.FactionType;

public class RessourceManager {

    private Finances finances;
    private int foodReserves;
    private Faction loyalist;
    private Agriculture agriculture;
    private Industry industry;

    public RessourceManager(Faction loyalist, int money, int foodReserves, Agriculture agriculture, Industry industry) {
        finances = new Finances(money);
        this.foodReserves = foodReserves;
        this.loyalist = loyalist;
        this.industry = industry;
        this.agriculture = agriculture;
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
            int loyalistSatisfactionLost = (int) Math.floor(price / 10);

            if (loyalist.getSatisfaction() < loyalistSatisfactionLost) {
                throw new Exception("Can't buy loyalists are not satisfied");
            }

            loyalist.updateSatisfaction(-loyalistSatisfactionLost);
            faction.updateSatisfaction(10);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void updateSizeOfAgriculture(int additionalSize) {
        try {
            int maxSize = getMaxSizeForAgriculture();
            int expectedSize = additionalSize + agriculture.getSize();

            int newSize = Math.min(maxSize, expectedSize);
            agriculture.setSize(newSize);
        } catch (Exception e) {
            throw new Error("cannot grow as expected");
        }
    }

    public void updateSizeOfIndustry(int additionalSize) {
        try {
            int maxSize = getMaxSizeForIndustry();
            int expectedSize = additionalSize + industry.getSize();

            int newSize = Math.min(maxSize, expectedSize);
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
        return Math.min(100, 100 - industry.getSize());
    }

    private int getMaxSizeForIndustry() {
        return Math.min(100, 100 - agriculture.getSize());
    }

    public int getBrideCost(int partisansCount) {
        return finances.getBrideCost(partisansCount);
    }

}
