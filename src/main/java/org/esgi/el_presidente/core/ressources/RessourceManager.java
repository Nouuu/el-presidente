package org.esgi.el_presidente.core.ressources;

public class RessourceManager {

  private Finances finances;
  private int foodReserves;

  public RessourceManager(double money, int foodReserves) {
    finances = new Finances(money);
    this.foodReserves = foodReserves;
  }

  public void buyFood(int unitOfFood) throws Exception {
    try {
      foodReserves += finances.buyFood(unitOfFood);
    } catch (Exception e) {
      throw new Exception("Can't buy Food");
    }
  }

  public void buyBribe(int partisans) throws Exception {
    try {
      finances.buyBribe(partisans);

    } catch (Exception e) {
      throw new Exception("Can't buy Bribe");
    }
  }

  public double getMoney() {
    return finances.getMoneyInCoffers();
  }

  public int getFoodReserves() {
    return foodReserves;
  }
}
