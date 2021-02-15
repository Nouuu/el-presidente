package org.esgi.el_presidente;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class AppTest {

    @Test
    public void readFileFromRessource() {
        String testFilePath = "test/testfile";
        String expected = "mais où est donc or ni car\n" +
                "retour lignes 1\n" +
                "retour 2\n" +
                "{\n" +
                "    flkehzf\n" +
                "}";
        Assertions.assertThat(App.readFileFromRessource(testFilePath)).isEqualTo(expected);
    }
}