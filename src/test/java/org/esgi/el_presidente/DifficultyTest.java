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
    }

    @Test
    public void testDifficultyToString() {
    }

    @Test
    public void testDifficultyGetGainMultiplier() {
    }

    @Test
    public void testDifficultyGetLoseMultiplier() {
    }
}
