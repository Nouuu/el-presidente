package org.esgi.el_presidente;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.esgi.el_presidente.cli.Cli;
import org.esgi.el_presidente.core.game.Difficulty;
import org.esgi.el_presidente.core.game.Game;
import org.esgi.el_presidente.core.scenario.Scenario;

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
        Scenario scenario = getScenario("sandbox.json");
        Game game = new Game(scenario, Difficulty.MEDIUM);
        Cli cli = new Cli(game);
        cli.loop();
        cli.printResult();
    }

}

/*
 * EventManager eventsManager =
 * EventManager.getEventManagerFromJson("eventManager1.json");
 * 
 * System.out.println( eventsManager.getEvents() .stream() .map(Event::toString)
 * .collect(Collectors.joining("\n")) );
 */