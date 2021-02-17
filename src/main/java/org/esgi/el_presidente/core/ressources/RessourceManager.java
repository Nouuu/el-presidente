package org.esgi.el_presidente.core.ressources;

import org.esgi.el_presidente.core.factions.Faction;

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
    try {
      int price = finances.buyBribe(faction.getPartisans());
      int loyalistSatisfactionLost = (int) Math.ceil(price / 10);

      if (loyalist.getSatisfaction() < loyalistSatisfactionLost) {
        throw new Exception("Can't buy oyalists are not satisfied");
      }

      loyalist.removeSatisfaction(loyalistSatisfactionLost);
      faction.addSatisfactionPercent(10);
    } catch (Exception e) {
      throw new Exception("Can't buy Bribe");
    }
  }

  public void increaseSizeOfAgriculture(int additionalSize) {
    try {
      testSizeOfIsland(additionalSize);
      agriculture.expand(additionalSize);
    } catch (Exception e) {
      System.err.println("Cannot grow as expected");
    }
  }

  public void increaseSizeOfIndustry(int additionalSize) {
    try {
      testSizeOfIsland(additionalSize);
      industry.expand(additionalSize);
    } catch (Exception e) {
      System.err.println("Cannot grow as expected");
    }
  }

  private void testSizeOfIsland(int additionalSize) throws Exception {
    if (agriculture.getSize() + industry.getSize() + additionalSize > 100) {
      throw new Exception("Incorrect size");
    }
  }

  public int getMoney() {
    return finances.getMoneyInCoffers();
  }

  public int getFoodReserves() {
    return foodReserves;
  }
}
