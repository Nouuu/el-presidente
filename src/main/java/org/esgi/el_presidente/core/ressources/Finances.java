package org.esgi.el_presidente.core.ressources;

public class Finances {
  private double moneyInCoffers;
  private final double foodPrice = 8;
  private final double bribePriceByPartisan = 15;

  public Finances(double initalMoney) {
    this.moneyInCoffers = initalMoney;
  }

  public int buyFood(int unitsOfFoodDesired) throws Exception {
    double price = unitsOfFoodDesired * foodPrice;
    if (price > moneyInCoffers) {
      throw new Exception("insufficient amount in coffers");
    }
    moneyInCoffers -= price;
    return unitsOfFoodDesired;
  }

  /**
   * TODO Should be change by loyalist rules
   * 
   * Currently I'm not sure if the implementation should be done here
   */
  public int buyBribe(int partisansBribed) throws Exception {
    double price = bribePriceByPartisan * partisansBribed;
    if (price > moneyInCoffers) {
      throw new Exception("insufficient amount in coffers");
    }
    moneyInCoffers -= price;
    return partisansBribed;
  }

  public double getMoneyInCoffers() {
    return moneyInCoffers;
  }
}
