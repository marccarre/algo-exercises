package com.carmatech.algo.exercises;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Integers {
    public static final int[] NO_SOLUTION = new int[0];

    /**
     * Time complexity:  O(n^2)
     * Space complexity: O(1)
     */
    public static int[] findTwoIntegersSummingUpToTarget_BruteForce(final int target, final int[] array) {
        if ((target == 0) || (array == null) || (array.length < 2))
            return NO_SOLUTION;

        for (int i = 0; i < array.length; ++i)
            for (int j = i + 1; j < array.length; ++j)
                if (array[i] + array[j] == target)
                    return new int[] {i, j};

        return NO_SOLUTION;
    }

    /**
     * Time complexity:  O(n.(log(n)+1)) = O(n.log(n))
     * Space complexity: O(1)
     */
    public static int[] findTwoIntegersSummingUpToTarget_SortingAndSimplePass(final int target, final int[] array) {
        if ((target == 0) || (array == null) || (array.length < 2))
            return NO_SOLUTION;

        Arrays.sort(array);

        int i = 0;
        int j = array.length - 1;
        int sum;

        while (i < j) {
            sum = array[i] + array[j];
            if (sum == target) return new int[] {i, j};
            if (sum < target) ++i;
            if (sum > target) --j;
        }
        return NO_SOLUTION;
    }

    /**
     * Time complexity:  O((n+1).log(n)) = O(n.log(n))
     * Space complexity: O(1)
     * Potential bugs: Stationary points & non-convergence.
     */
    public static int[] findTwoIntegersSummingUpToTarget_SortingAndBinarySearch(final int target, final int[] array) {
        if ((target == 0) || (array == null) || (array.length < 2))
            return NO_SOLUTION;

        Arrays.sort(array);

        int i = 0;
        int j = array.length - 1;
        int prevI = -1;
        int prevJ = -1;
        int sum;
        boolean wasGreater = false;
        boolean wasSmaller = false;
        boolean hasBacktracked = false;

        while ((i < j) && (i != prevI || j != prevJ)) {
            sum = array[i] + array[j];
            if (sum == target)
                return new int[] {i, j};

            System.out.println("i=" + i + ", j=" + j + ", sum=" + sum + ", target=" + target );

            if (sum < target) {
                if (wasGreater && (prevJ != -1) && !hasBacktracked) {
                    hasBacktracked = true;
                    int currentJ = j;
                    do {
                        System.out.println("i=" + i + ", j=" + j + ", sum=" + (array[i] + array[j]) + ", prevJ=" + prevJ + " (before)");
                        j = j + (prevJ - j) / 2;
                        System.out.println("i=" + i + ", j=" + j + ", sum=" + (array[i] + array[j]) + ", prevJ=" + prevJ + " (after)");
                    } while ((array[i] + array[j] < target) && (j != currentJ) && (j != prevJ));
                } else {
                    hasBacktracked = false;
                    prevI = i;
                    i = i + (j - i) / 2;
                }
                wasSmaller = true;
                wasGreater = false;
            }
            if (sum > target) {
                if (wasSmaller && (prevI != -1) && !hasBacktracked) {
                    hasBacktracked = true;
                    int currentI = i;
                    do {
                        System.out.println("i=" + i + ", j=" + j + ", sum=" + (array[i] + array[j]) + ", prevI=" + prevI + " (before)");
                        i = prevI + (i - prevI) / 2;
                        System.out.println("i=" + i + ", j=" + j + ", sum=" + (array[i] + array[j]) + ", prevI=" + prevI + " (after)");
                    } while ((array[i] + array[j] > target) && (i != currentI) && (i != prevI));
                } else {
                    hasBacktracked = false;
                    prevJ = j;
                    j = i + (j - i) / 2;
                }
                wasSmaller = false;
                wasGreater = true;
            }
        }
        return NO_SOLUTION;
    }

    /**
     * Time complexity:  O(n)
     * Space complexity: O(n)
     */
    public static int[] findTwoIntegersSummingUpToTarget_HashMap(final int target, final int[] array) {
        if ((target == 0) || (array == null) || (array.length < 2))
            return NO_SOLUTION;

        int remainder;
        int current;
        final Map<Integer, Integer> remainders = new HashMap<Integer, Integer>();
        for (int i = 0; i < array.length; ++i) {
            current = array[i];
            if (remainders.containsKey(current))
                return new int[] {remainders.get(current), i};

            remainder = target - current;
            if (!remainders.containsKey(remainder))
                remainders.put(remainder, i);
        }

        for (int i = 0; i < array.length; ++i) {
            current = array[i];
            if (remainders.containsKey(current))
                return new int[]{remainders.get(current), i};
        }

        return NO_SOLUTION;
    }

    public static int parseInt(final String s) {
        int integer = 0;
        int decade = 1;
        for (int i = s.length() - 1; i >= 0; --i) {
            char c = s.charAt(i);
            if (isDigit(c)) {
                integer += toInt(c) * decade;
                decade *= 10;
            } else if (isNegativeSign(c, i)) {
                integer *= -1;
            } else {
                throw new NumberFormatException("'" + s + "' is not a valid integer.");
            }
        }
        return integer;
    }

    private static boolean isDigit(final char c) {
        return (c >= '0') && (c <= '9');
    }

    private static boolean isNegativeSign(final char c, final int i) {
        return (i == 0) && (c == '-');
    }

    private static int toInt(final char c) {
        return c - '0';
    }

    public static String toString(int integer) {
        if (integer == 0)
            return "0";

        final StringBuilder string = new StringBuilder();
        final boolean isNegative = (integer < 0);
        while (integer != 0) {
            final int unit = integer % 10;
            string.append(unit >= 0 ? unit : -unit);
            integer /= 10;
        }
        if (isNegative)
            string.append('-');
        return string.reverse().toString();
    }
}
