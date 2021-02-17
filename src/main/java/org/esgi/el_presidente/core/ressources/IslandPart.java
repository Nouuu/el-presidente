package org.esgi.el_presidente.core.ressources;

public interface IslandPart {
  void shrink(int deductSize);

  void expand(int additionalSize);

  int getSize();
}
