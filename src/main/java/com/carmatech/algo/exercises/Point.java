package com.carmatech.algo.exercises;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Point {
    public final double x;
    public final double y;
    public final double z;

    private String toString;

    Point(final double x, final double y, final double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double euclideanDistanceTo(final Point next) {
        return sqrt(pow(next.x - x, 2) + pow(next.y - y, 2) + pow(next.z - z, 2));
    }

    @Override
    public String toString() {
        if (toString == null)
            toString = "(" + x + "," + y + "," + z + ")";
        return toString;
    }
}
