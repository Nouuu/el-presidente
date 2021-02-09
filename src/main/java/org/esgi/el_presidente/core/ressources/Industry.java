package org.esgi.el_presidente.core.ressources;

public class Industry implements IslandPart {
  private double partOfIsland;
  private final double moneyProductionCoefficient;

  public Industry(double partOfIsland, double moneyProductionCoefficient) {
    this.partOfIsland = partOfIsland;
    this.moneyProductionCoefficient = moneyProductionCoefficient;
  }

  public double yearlyProductionOfMoney() {
    return partOfIsland * moneyProductionCoefficient;
  }

  public void expand(double additionalSize) {
    partOfIsland += additionalSize;
  }

  public double getSize() {
    return partOfIsland;
  }

  public void shrink(double deductSize) {
    partOfIsland -= deductSize;
  }
}
