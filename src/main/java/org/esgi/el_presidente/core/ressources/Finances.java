package org.esgi.el_presidente.core.ressources;

public class Finances {
  private int moneyInCoffers;
  private final int foodPrice = 8;
  private final int bribePriceByPartisan = 15;
  private final double loseMultiplier;

  public Finances(int initalMoney, double loseMultiplier) {
    this.moneyInCoffers = initalMoney;
    this.loseMultiplier = loseMultiplier;
  }

  public int buyFood(int unitsOfFoodDesired) throws Exception {
    int price = (int) Math.floor(unitsOfFoodDesired * foodPrice * loseMultiplier);
    if (price > moneyInCoffers) {
      throw new Exception("insufficient amount in coffers");
    }
    moneyInCoffers -= price;
    return price;
  }

  public int buyBribe(int partisansBribed) throws Exception {
    int price = (int) Math.floor(bribePriceByPartisan * partisansBribed * loseMultiplier);
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
