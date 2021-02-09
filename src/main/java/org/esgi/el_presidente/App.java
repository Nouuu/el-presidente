package org.esgi.el_presidente;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.esgi.el_presidente.core.events.Event;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws IOException {
        String is = App.readFileFromRessource("events.json");

        ObjectMapper mapper = new ObjectMapper();
        List<Event> events = mapper.readValue(is, mapper.getTypeFactory().constructCollectionType(List.class, Event.class));


        System.out.println(events.stream().map(Event::toString).collect(Collectors.joining("\n\n")));
    }

    private static String readFileFromRessource(String fileName) {

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
