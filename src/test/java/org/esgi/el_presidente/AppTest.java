package org.esgi.el_presidente;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.io.IOException;

public class AppTest {

    @Test
    public void readFileFromRessource() {
        String testFilePath = "test/testfile.txt";
        String expected = "mais où est donc or ni car\n" +
                "retour lignes 1\n" +
                "retour 2\n" +
                "{\n" +
                "    flkehzf\n" +
                "}";
        Assertions.assertThat(App.readFileFromRessource(testFilePath)).isEqualTo(expected);
    }

    @Test
    public void readFileFromRessourceInvalidPath() {

        Assertions.assertThatThrownBy(() -> App.readFileFromRessource("invalid")).isInstanceOf(IllegalArgumentException.class);
    }
}