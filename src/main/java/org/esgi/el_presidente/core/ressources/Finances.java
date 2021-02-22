package org.esgi.el_presidente.core.ressources;

public class Finances {
  private int moneyInCoffers;
  private final int foodPrice = 8;
  private final int bribePriceByPartisan = 15;

  public Finances(int initalMoney) {
    this.moneyInCoffers = initalMoney;
  }

  public int buyFood(int unitsOfFoodDesired) throws Exception {
    int price = unitsOfFoodDesired * foodPrice;
    if (price > moneyInCoffers) {
      throw new Exception("insufficient amount in coffers");
    }
    moneyInCoffers -= price;
    return price;
  }

  public int buyBribe(int partisansBribed) throws Exception {
    int price = bribePriceByPartisan * partisansBribed;
    if (price > moneyInCoffers) {
      throw new Exception("insufficient amount in coffers");
    }
    moneyInCoffers -= price;
    return price;
  }

  public int getBrideCost(int partisansCount) {
    return bribePriceByPartisan * partisansCount;
  }

  public int getMoneyInCoffers() {
    return moneyInCoffers;
  }

  public void handleMoneyAction(int moneyImpact) {
    moneyInCoffers += moneyImpact;
  }

  public int getFoodPrice() {
    return foodPrice;
  }
}
