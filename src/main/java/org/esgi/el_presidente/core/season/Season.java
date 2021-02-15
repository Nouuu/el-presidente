package org.esgi.el_presidente.core.season;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Arrays;

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

    @JsonCreator
    public static Season fromString(String string) {
        return Arrays.stream(Season.values()).filter(o -> o.season.equals(string)).findFirst().orElse(null);
    }
}
