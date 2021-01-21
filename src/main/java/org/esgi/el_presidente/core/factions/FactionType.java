package org.esgi.el_presidente.core.factions;

public enum FactionType {
    capitalist("capitaliste"),
    communist("communiste"),
    liberal("liberaux"),
    religious("religieux"),
    ecologist("écologistes"),
    nationalist("nationalist"),
    loyalist("loyalistes");

    private final String type;

    FactionType(String type) {
        this.type = type;
    }


    @Override
    public String toString() {
        return type;
    }
}
