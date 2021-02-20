package org.esgi.el_presidente.core.game;

public enum Difficulty {
    EASY("facile", 1.5, 0.75, 10),
    MEDIUM("normal", 1, 1, 30),
    HARD("difficile", 0.75, 1.5, 50),
    HARDCORE("niveau JDG", 0.5, 2, 65);

    private final String name;
    private final double gainMultiplier;
    private final double loseMultiplier;
    private final int satisfactionLimit;

    Difficulty(String name, double gainMultiplier, double loseMultiplier, int satisfactionLimit) {
        this.name = name;
        this.gainMultiplier = gainMultiplier;
        this.loseMultiplier = loseMultiplier;
        this.satisfactionLimit = satisfactionLimit;
    }

    public double getGainMultiplier() {
        return gainMultiplier;
    }

    public double getLoseMultiplier() {
        return loseMultiplier;
    }

    public int getSatisfactionLimit() {
        return satisfactionLimit;
    }

    @Override
    public String toString() {
        return name;
    }
}
