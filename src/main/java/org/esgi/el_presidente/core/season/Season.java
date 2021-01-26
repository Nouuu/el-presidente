package org.esgi.el_presidente.core.season;

public enum Season {
    spring("printemps"),
    summer("été"),
    autumn("automne"),
    winter("hiver");

    private final String season;

    Season(String season) {
        this.season = season;
    }

    @Override
    public String toString() {
        return season;
    }
}
