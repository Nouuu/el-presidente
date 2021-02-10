package org.esgi.el_presidente.core.ressources;

public class Agriculture implements IslandPart {
  private double partOfIsland;
  private final double foodProductionCoefficient;

  public Agriculture(double partOfIsland, double foodProductionCoefficient) {
    this.partOfIsland = partOfIsland;
    this.foodProductionCoefficient = foodProductionCoefficient;
  }

  public double yearlyProductionOfFood() {
    return partOfIsland * foodProductionCoefficient;
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
