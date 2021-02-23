package org.esgi.el_presidente.core.factions;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Arrays;

public enum FactionType {
    capitalist("capitalistes"),
    communist("communistes"),
    liberal("liberaux"),
    religious("religieux"),
    ecologist("écologistes"),
    nationalist("nationalistes"),
    loyalist("loyalistes");

    private final String type;

    FactionType(String type) {
        this.type = type;
    }


    @Override
    public String toString() {
        return type;
    }

    @JsonCreator
    public static FactionType fromString(String string) {
        return Arrays.stream(FactionType.values()).filter(o -> o.type.equalsIgnoreCase(string)).findFirst().orElse(null);
    }
}
