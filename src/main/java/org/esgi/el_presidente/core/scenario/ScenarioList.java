package org.esgi.el_presidente.core.scenario;

public enum ScenarioList {
    SANDBOX("Bac à sable", "sandbox.json"),
    SCENARIO_1("Premier scénario", "scenario1.json"),
    SIMCITY("SimCity", "simcity.json");

    private final String name;
    private final String path;

    ScenarioList(String name, String path) {
        this.name = name;
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    @Override
    public String toString() {
        return name;
    }
}
