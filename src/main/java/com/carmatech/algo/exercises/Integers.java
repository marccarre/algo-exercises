package com.carmatech.algo.exercises;

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
}
