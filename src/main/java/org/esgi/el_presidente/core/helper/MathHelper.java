package org.esgi.el_presidente.core.helper;

public class MathHelper {
    public static int multiplyIntDoubleToFloor(int intV, double doubleV) {
        return (int) Math.floor(((double) intV) * doubleV);
    }

    public static int divideIntDoubleToFloor(int intV, double doubleV) {
        return (int) Math.floor(((double) intV) / doubleV);
    }

    public static int restrictValue(int value, int min, int max) {
        return Math.max(min, Math.min(max, value));
    }
}
