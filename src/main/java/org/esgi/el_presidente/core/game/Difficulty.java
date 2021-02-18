package org.esgi.el_presidente.core.game;

public enum Difficulty {
    EASY("facile", 1.5, 0.75), MEDIUM("normal", 1, 1), HARD("difficile", 0.75, 1.5), HARDCORE("niveau JDG", 0.5, 2);

    private final String name;
    private final double gainMultiplier;
    private final double loseMultiplier;

    Difficulty(String name, double gainMultiplier, double loseMultiplier) {
        this.name = name;
        this.gainMultiplier = gainMultiplier;
        this.loseMultiplier = loseMultiplier;
    }

    public double getGainMultiplier() {
        return gainMultiplier;
    }

    public double getLoseMultiplier() {
        return loseMultiplier;
    }

    public int getSatisfactionLimit() {
        switch (this) {
            case EASY:
                return 10;
            case MEDIUM:
                return 30;
            case HARD:
                return 50;
            case HARDCORE:
                return 65;
            default:
                return 100;
        }
    }

    @Override
    public String toString() {
        return name;
    }
}
