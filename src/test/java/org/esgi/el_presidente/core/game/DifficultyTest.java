package org.esgi.el_presidente.core.game;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class DifficultyTest {

    @Test
    public void testDifficultyEasyExists() {
        Assertions.assertThatNoException().isThrownBy(() -> Difficulty.valueOf("EASY"));
    }

    @Test
    public void testDifficultyMediumExists() {
        Assertions.assertThatNoException().isThrownBy(() -> Difficulty.valueOf("MEDIUM"));
    }

    @Test
    public void testDifficultyHardExists() {
        Assertions.assertThatNoException().isThrownBy(() -> Difficulty.valueOf("HARD"));
    }

    @Test
    public void testDifficultyToString() {
        for (Difficulty difficulty : Difficulty.values()) {
            Assertions.assertThat(difficulty.toString()).hasSizeGreaterThan(0);
        }
    }

    @Test
    public void testDifficultyGetGainMultiplier() {
        for (Difficulty difficulty : Difficulty.values()) {
            Assertions.assertThat(difficulty.getGainMultiplier()).isGreaterThanOrEqualTo(0.01);
        }
    }

    @Test
    public void testDifficultyGetLoseMultiplier() {
        for (Difficulty difficulty : Difficulty.values()) {
            Assertions.assertThat(difficulty.getLoseMultiplier()).isGreaterThanOrEqualTo(0.01);
        }
    }

    @Test
    public void testDifficultyGetSatisfactionLimit() {
        int[] expecteds = { 10, 30, 50, 65 };
        for (Difficulty difficulty : Difficulty.values()) {
            int expected = expecteds[difficulty.ordinal()];
            Assertions.assertThat(expected).isEqualTo(difficulty.getSatisfactionLimit());
        }
    }
}
