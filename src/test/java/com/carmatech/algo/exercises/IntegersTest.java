package com.carmatech.algo.exercises;

import org.junit.Test;

import static com.carmatech.algo.exercises.Integers.NO_SOLUTION;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class IntegersTest {
    @Test
    public void findTwoIntegersSummingUpToTarget_BruteForce() {
        assertThat(Integers.findTwoIntegersSummingUpToTarget_BruteForce(0, new int[]{1, 2, 3}), equalTo(NO_SOLUTION));
        assertThat(Integers.findTwoIntegersSummingUpToTarget_BruteForce(5, new int[0]), equalTo(NO_SOLUTION));
        assertThat(Integers.findTwoIntegersSummingUpToTarget_BruteForce(5, new int[]{1}), equalTo(NO_SOLUTION));

        assertThat(Integers.findTwoIntegersSummingUpToTarget_BruteForce(5, new int[]{1, 2, 3}), equalTo(new int[]{1, 2}));
        assertThat(Integers.findTwoIntegersSummingUpToTarget_BruteForce(12, new int[]{1, 2, 2, 2, 5, 5, 5, 8}), equalTo(NO_SOLUTION));
        assertThat(Integers.findTwoIntegersSummingUpToTarget_BruteForce(13, new int[]{1, 2, 2, 2, 5, 5, 5, 8}), equalTo(new int[] {4, 7}));
    }

    @Test
    public void findTwoIntegersSummingUpToTarget_SortingAndSimplePass() {
        assertThat(Integers.findTwoIntegersSummingUpToTarget_SortingAndSimplePass(0, new int[]{1, 2, 3}), equalTo(NO_SOLUTION));
        assertThat(Integers.findTwoIntegersSummingUpToTarget_SortingAndSimplePass(5, new int[0]), equalTo(NO_SOLUTION));
        assertThat(Integers.findTwoIntegersSummingUpToTarget_SortingAndSimplePass(5, new int[]{1}), equalTo(NO_SOLUTION));

        assertThat(Integers.findTwoIntegersSummingUpToTarget_SortingAndSimplePass(5, new int[]{1, 2, 3}), equalTo(new int[]{1, 2}));
        assertThat(Integers.findTwoIntegersSummingUpToTarget_SortingAndSimplePass(12, new int[]{1, 2, 2, 2, 5, 5, 5, 8}), equalTo(NO_SOLUTION));
        assertThat(Integers.findTwoIntegersSummingUpToTarget_SortingAndSimplePass(13, new int[]{1, 2, 2, 2, 5, 5, 5, 8}), equalTo(new int[] {4, 7}));
    }

    @Test
    public void findTwoIntegersSummingUpToTarget_SortingAndBinarySearch() {
        assertThat(Integers.findTwoIntegersSummingUpToTarget_SortingAndBinarySearch(0, new int[]{1, 2, 3}), equalTo(NO_SOLUTION));
        assertThat(Integers.findTwoIntegersSummingUpToTarget_SortingAndBinarySearch(5, new int[0]), equalTo(NO_SOLUTION));
        assertThat(Integers.findTwoIntegersSummingUpToTarget_SortingAndBinarySearch(5, new int[]{1}), equalTo(NO_SOLUTION));

        assertThat(Integers.findTwoIntegersSummingUpToTarget_SortingAndBinarySearch(5, new int[]{1, 2, 3}), equalTo(new int[]{1, 2}));
        // Currently bugged:
        // assertThat(Integers.findTwoIntegersSummingUpToTarget_SortingAndBinarySearch(12, new int[]{1, 2, 2, 2, 5, 5, 5, 8}), equalTo(NO_SOLUTION));
        assertThat(Integers.findTwoIntegersSummingUpToTarget_SortingAndBinarySearch(13, new int[]{1, 2, 2, 2, 5, 5, 5, 8}), equalTo(new int[] {5, 7}));
        assertThat(Integers.findTwoIntegersSummingUpToTarget_SortingAndBinarySearch(13, new int[]{1, 2, 2, 2, 5, 6, 6, 8}), equalTo(new int[] {4, 7}));
    }

    @Test
    public void findTwoIntegersSummingUpToTarget_HashMap() {
        assertThat(Integers.findTwoIntegersSummingUpToTarget_HashMap(0, new int[]{1, 2, 3}), equalTo(NO_SOLUTION));
        assertThat(Integers.findTwoIntegersSummingUpToTarget_HashMap(5, new int[0]), equalTo(NO_SOLUTION));
        assertThat(Integers.findTwoIntegersSummingUpToTarget_HashMap(5, new int[]{1}), equalTo(NO_SOLUTION));

        assertThat(Integers.findTwoIntegersSummingUpToTarget_HashMap(5, new int[]{1, 2, 3}), equalTo(new int[]{1, 2}));
        assertThat(Integers.findTwoIntegersSummingUpToTarget_HashMap(12, new int[]{1, 2, 2, 2, 5, 5, 5, 8}), equalTo(NO_SOLUTION));
        assertThat(Integers.findTwoIntegersSummingUpToTarget_HashMap(13, new int[]{1, 2, 2, 2, 5, 5, 5, 8}), equalTo(new int[] {4, 7}));
    }
}
