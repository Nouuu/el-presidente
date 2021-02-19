package org.esgi.el_presidente;

import org.esgi.el_presidente.javafx.FxApp;

public class App {

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