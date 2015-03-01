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
}
