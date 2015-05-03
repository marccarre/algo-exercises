package com.carmatech.algo.exercises;

public class Robot {
    private static final double ZERO = 0.0;
    private static final int INITIAL_POSITION = 0;

    public static double heightToPowerFor(final Point... path) {
        if ((path == null) || (path.length == 0))
            return ZERO;

        double minZ = path[INITIAL_POSITION].z;
        double maxHeight = ZERO;

        for (final Point point : path) {
            final double height = point.z - minZ;
            if (height > maxHeight)
                maxHeight = height;

            if (point.z < minZ)
                minZ = point.z;
        }

        return maxHeight;
    }
}
