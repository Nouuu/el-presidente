package org.esgi.el_presidente.core.helper;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class MathHelperTest {
    private int intV;
    private double doubleV;
    private int expectedValue;

    @Test
    public void multiplyIntDoubleToFloor0() {
        intV = 0;
        doubleV = 0;
        expectedValue = 0;

        Assertions.assertThat(MathHelper.multiplyIntDoubleToFloor(intV, doubleV)).isEqualTo(expectedValue);
    }

    @Test
    public void multiplyIntDoubleToFloorBasic() {
        intV = 5;
        doubleV = 6.;
        expectedValue = 30;

        Assertions.assertThat(MathHelper.multiplyIntDoubleToFloor(intV, doubleV)).isEqualTo(expectedValue);
    }

    @Test
    public void multiplyIntDoubleToFloorWithComma() {
        intV = 6;
        doubleV = 2.5;
        expectedValue = 15;

        Assertions.assertThat(MathHelper.multiplyIntDoubleToFloor(intV, doubleV)).isEqualTo(expectedValue);
    }

    @Test
    public void multiplyIntDoubleToFloorWithCommaAndFloor() {
        intV = 7;
        doubleV = 2.7;
        expectedValue = 18;

        Assertions.assertThat(MathHelper.multiplyIntDoubleToFloor(intV, doubleV)).isEqualTo(expectedValue);
    }
}