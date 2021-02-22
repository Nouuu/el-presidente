package org.esgi.el_presidente.core.helper;

import org.esgi.el_presidente.App;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class FileHelper {

    public static String readFileFromRessource(String fileName) throws IllegalArgumentException {
        InputStream inputStream = FileHelper.getRessourceInputStream(fileName);

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

    public static InputStream getRessourceInputStream(String fileName) {
        // The class loader that loaded the class
        ClassLoader classLoader = App.class.getClassLoader();
        return classLoader.getResourceAsStream(fileName);
    }
}
