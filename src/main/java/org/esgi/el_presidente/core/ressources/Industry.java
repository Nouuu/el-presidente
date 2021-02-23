package org.esgi.el_presidente.core.ressources;

public class Industry implements IslandPart {
  private int partOfIsland;
  private final int moneyProductionCoefficient;

  public Industry(int partOfIsland, int moneyProductionCoefficient) {
    this.partOfIsland = partOfIsland;
    this.moneyProductionCoefficient = moneyProductionCoefficient;
  }

  public int yearlyProductionOfMoney() {
    return partOfIsland * moneyProductionCoefficient;
  }

  public void setSize(int newSize) {
    partOfIsland = newSize;
  }

  public int getSize() {
    return partOfIsland;
  }
}
