package org.esgi.el_presidente;

import org.esgi.el_presidente.core.events.Event;
import org.esgi.el_presidente.core.events.EventManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) throws IOException {

        EventManager eventsManager = EventManager.getEventManagerFromJson("eventManager1.json");

        System.out.println(
                eventsManager.getEvents()
                        .stream()
                        .map(Event::toString)
                        .collect(Collectors.joining("\n"))
        );
    }

    public static String readFileFromRessource(String fileName) {

        // The class loader that loaded the class
        ClassLoader classLoader = App.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        // the stream holding the file content
        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return new BufferedReader(
                    new InputStreamReader(inputStream, StandardCharsets.UTF_8)
            )
                    .lines()
                    .collect(Collectors.joining("\n"));
        }

    }
}
