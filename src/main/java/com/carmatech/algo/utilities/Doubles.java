package com.carmatech.algo.utilities;

import static java.lang.Double.isInfinite;
import static java.lang.Double.isNaN;
import static java.lang.Math.signum;

public class Doubles {
    public static double checkedAdd(final double a, final double b) {
        final double result = a + b;
        if (isInfinite(result))
            throw new ArithmeticException("Failed to add " + a + " and " + b + ". Overflow: " + result + ".");
        if (isNaN(result))
            throw new ArithmeticException("Failed to add " + a + " and " + b + ". Not-a-number: " + result + ".");
        if (((b != 0) && (a == result)) || ((a != 0) && (b == result)))
            throw new ArithmeticException("Failed to add " + a + " and " + b + ". Absorption error: " + result + ".");
        if (sameSign(a, b) && (!sameSign(a, result) || !sameSign(b, result)))
            throw new ArithmeticException("Failed to add " + a + " and " + b + ". Bad sign: " + result + ".");
        return result;
    }

    private static boolean sameSign(final double a, final double b) {
        return signum(a) * signum(b) > 0;
    }
}
