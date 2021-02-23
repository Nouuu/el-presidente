package org.esgi.el_presidente.core.ressources;

public class Agriculture implements IslandPart {
  private int partOfIsland;
  private final int foodProductionCoefficient;

  public Agriculture(int partOfIsland, int foodProductionCoefficient) {
    this.partOfIsland = partOfIsland;
    this.foodProductionCoefficient = foodProductionCoefficient;
  }

  public int yearlyProductionOfFood() {
    return partOfIsland * foodProductionCoefficient;
  }

  public void setSize(int newSize) {
    partOfIsland = newSize;
  }

  public int getSize() {
    return partOfIsland;
  }

}
