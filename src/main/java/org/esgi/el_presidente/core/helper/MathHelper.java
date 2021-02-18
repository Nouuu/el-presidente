package org.esgi.el_presidente.core.helper;

public class MathHelper {
    public static int multiplyIntDoubleToFloor(int intV, double doubleV) {
        return (int) Math.floor(((double) intV) * doubleV);
    }
}
