package com.carmatech.algo.combinatorics;

import java.util.Arrays;

import static java.lang.Math.max;
import static java.lang.Math.min;

public final class RectanglesTiling {
    private RectanglesTiling() {
        // Pure utility class, do NOT instantiate.
    }

    public static int greedyCountSquaresToFill(int x, int y) {
        if (x <= 0 || y <= 0)
            throw new IllegalArgumentException("Rectangle must have strictly positive width and height but got (" + x + "," + y + ").");
        if (x == y)
            return 1;

        int numSquares = 0;

        // Fill with the largest possible square, until the rectangle is filled OR there only remains a Nx1 rectangle:
        while ((x > 1) && (y > 1)) {
            if (x > y) {
                x -= y;
            } else if (x < y) {
                y -= x;
            } else { // (x == y)
                x = 0;
                y = 0;
            }
            ++numSquares;
        }

        // If there remains a Nx1 rectangle, we simply add N to the count of squares:
        if (x == 1) {
            numSquares += y;
        } else if (y == 1) {
            numSquares += x;
        }

        return numSquares;
    }

    public static int linearSplitCountSquaresToFill(final int x, final int y) {
        if (x <= 0 || y <= 0)
            throw new IllegalArgumentException("Rectangle must have strictly positive width and height but got (" + x + "," + y + ").");
        if (x == y)
            return 1;

        // Cache results and exploit symmetry:
        final int largestSide = max(x, y);
        final int[][] minSquares = new int[largestSide + 1][largestSide + 1];

        final int min = minSquaresToFill(x, y, minSquares);
        System.out.println(toString(minSquares));
        return min;
    }

    private static String toString(final int[][] minSquares) {
        final StringBuilder string = new StringBuilder();
        for (int i = 0; i < minSquares.length; ++i) {
            string.append(Arrays.toString(minSquares[i]));
            string.append('\n');
        }
        return string.toString();
    }

    private static int minSquaresToFill(final int x, final int y, final int[][] minSquares) {
        if (minSquares[x][y] != 0) {
            return minSquares[x][y];
        }

        if (x == y) {
            minSquares[x][y] = 1;
        } else if (x == 1) {
            minSquares[x][y] = y;
        } else if (y == 1) {
            minSquares[x][y] = x;
        } else {
            minSquares[x][y] = min(minForHorizontalSplit(x, y, minSquares), minForVerticalSplit(x, y, minSquares));
        }

        // Leverage symmetry:
        minSquares[y][x] = minSquares[x][y];

        return minSquares[x][y];
    }

    private static int minForHorizontalSplit(final int x, final int y, final int[][] minSquares) {
        int numSquares = Integer.MAX_VALUE;
        for (int i = 1; i <= (x + 1) / 2; ++i) { // Exploit symmetry.
            numSquares = min(numSquares, minSquaresToFill(i, y, minSquares) + minSquaresToFill(x - i, y, minSquares));
        }
        return numSquares;
    }

    private static int minForVerticalSplit(final int x, final int y, final int[][] minSquares) {
        int numSquares = Integer.MAX_VALUE;
        for (int j = 1; j <= (y + 1) / 2; ++j) { // Exploit symmetry.
            numSquares = min(numSquares, minSquaresToFill(x, j, minSquares) + minSquaresToFill(x, y - j, minSquares));
        }
        return numSquares;
    }
}
