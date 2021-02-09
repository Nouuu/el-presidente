package org.esgi.el_presidente.core.ressources;

public interface IslandPart {
  void shrink(double deductSize);

  void expand(double additionalSize);
}
