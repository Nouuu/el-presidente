package org.esgi.el_presidente.core.game;

public enum Difficulty {
    ;

    private String name;
    private double gainMultiplier;
    private double loseMultiplier;

    Difficulty(String name, double gainMultiplier, double loseMultiplier) {
        this.name = name;
        this.gainMultiplier = gainMultiplier;
        this.loseMultiplier = loseMultiplier;
    }
}
