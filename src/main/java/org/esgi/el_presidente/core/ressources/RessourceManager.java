package org.esgi.el_presidente.core.ressources;

import org.esgi.el_presidente.core.factions.Faction;

public class RessourceManager {

  private Finances finances;
  private int foodReserves;
  private Faction loyalist;

  public RessourceManager(Faction loyalist, double money, int foodReserves) {
    finances = new Finances(money);
    this.foodReserves = foodReserves;
    this.loyalist = loyalist;
  }

  public void buyFood(int unitOfFood) throws Exception {
    try {
      finances.buyFood(unitOfFood);
      foodReserves += unitOfFood;
    } catch (Exception e) {
      throw new Exception("Can't buy Food");
    }
  }

  public void buyBribe(Faction faction, int partisans) throws Exception {
    // try {
    // double price = finances.buyBribe(partisans);
    // int loyalistLost = (int) Math.ceil(price / 10);
    // loyalist.removeSatisfaction(loyalistLost);
    // faction.addSatisfactionPercent(10);
    // } catch (Exception e) {
    // throw new Exception("Can't buy Bribe");
    // }
  }

  public double getMoney() {
    return finances.getMoneyInCoffers();
  }

  public int getFoodReserves() {
    return foodReserves;
  }
}
