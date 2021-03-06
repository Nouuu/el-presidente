package org.esgi.el_presidente.core.season;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.Arrays;

public class SeasonTest {

    @Test
    public void testSeasonsCount4() {
        Assertions.assertThat(Arrays.stream(Season.values()).count()).isEqualTo(4);
    }

    @Test
    public void testSeasonToString() {
        Assertions.assertThat(Season.summer.toString()).isEqualTo("été");
    }

    @Test
    public void testGetSpringSeasonFromString() {
        Assertions.assertThat(Season.valueOf("spring")).isEqualTo(Season.spring);
    }

    @Test
    public void testGetSummerSeasonFromString() {
        Assertions.assertThat(Season.valueOf("summer")).isEqualTo(Season.summer);
    }

    @Test
    public void testGetAutumnSeasonFromString() {
        Assertions.assertThat(Season.valueOf("autumn")).isEqualTo(Season.autumn);
    }

    @Test
    public void testGetWinterSeasonFromString() {
        Assertions.assertThat(Season.valueOf("winter")).isEqualTo(Season.winter);
    }

    @Test
    public void testGetInexistantSeasonFromString() {
        Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> Season.valueOf("inexistant"));
    }

    @Test
    public void fromString() {
        Assertions.assertThat(Season.fromString("hiver")).isEqualTo(Season.winter);
        Assertions.assertThat(Season.fromString("printemps")).isEqualTo(Season.spring);
        Assertions.assertThat(Season.fromString("été")).isEqualTo(Season.summer);
    }
}
