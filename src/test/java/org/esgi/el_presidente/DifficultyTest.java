package org.esgi.el_presidente;

import org.assertj.core.api.Assertions;
import org.esgi.el_presidente.core.game.Difficulty;
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
}
