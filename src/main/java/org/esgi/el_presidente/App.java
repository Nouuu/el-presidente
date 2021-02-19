package org.esgi.el_presidente;

import org.esgi.el_presidente.javafx.FxApp;

public class App {

    public static Scenario getScenario(String path) {
        Scenario scenario;
        try {
            scenario = Scenario.createFromJson(path);
            return scenario;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new Error("Error invalide path to scenario");
        }
    }

    public static void main(String[] args) {
//        System.out.println("Coucou");
        FxApp.launchApp();
    }

}

/*
 * EventManager eventsManager =
 * EventManager.getEventManagerFromJson("eventManager1.json");
 * 
 * System.out.println( eventsManager.getEvents() .stream() .map(Event::toString)
 * .collect(Collectors.joining("\n")) );
 */