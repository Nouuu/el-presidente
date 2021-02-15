package org.esgi.el_presidente.core.helper;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class FileHelperTest {

    @Test
    public void readFileFromRessource() {
        String testFilePath = "test/testfile.txt";
        String expected = "mais où est donc or ni car\n" +
                "retour lignes 1\n" +
                "retour 2\n" +
                "{\n" +
                "    flkehzf\n" +
                "}";
        Assertions.assertThat(FileHelper.readFileFromRessource(testFilePath)).isEqualTo(expected);
    }

    @Test
    public void readFileFromRessourceInvalidPath() {

        Assertions.assertThatThrownBy(() -> FileHelper.readFileFromRessource("invalid")).isInstanceOf(IllegalArgumentException.class);
    }
}