package org.esgi.el_presidente;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.esgi.el_presidente.cli.Cli;
import org.esgi.el_presidente.javafx.FxApp;

public class App {
    public static void main(String[] args) throws JsonProcessingException {
        if (args.length > 0 && args[0] == "--cli") {
            Cli cli = new Cli();
            cli.loop();
            cli.printResult();
        } else {
            FxApp.launchApp();
        }
    }

}