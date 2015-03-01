package com.carmatech.algo.exercises;

import org.junit.Test;

import java.util.Arrays;

import static com.carmatech.algo.exercises.Integers.NO_SOLUTION;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class IntegersTest {
    @Test
    public void findTwoIntegersSummingUpToTarget_BruteForce() {
        assertThat(Arrays.equals(Integers.findTwoIntegersSummingUpToTarget_BruteForce(0, new int[]{1, 2, 3}), NO_SOLUTION), is(true));
        assertThat(Arrays.equals(Integers.findTwoIntegersSummingUpToTarget_BruteForce(5, new int[0]), NO_SOLUTION), is(true));
        assertThat(Arrays.equals(Integers.findTwoIntegersSummingUpToTarget_BruteForce(5, new int[]{1}), NO_SOLUTION), is(true));

        assertThat(Arrays.equals(Integers.findTwoIntegersSummingUpToTarget_BruteForce(5, new int[]{1, 2, 3}), new int[]{1, 2}), is(true));
        assertThat(Arrays.equals(Integers.findTwoIntegersSummingUpToTarget_BruteForce(12, new int[]{1, 2, 2, 2, 5, 5, 5, 8}), NO_SOLUTION), is(true));
        assertThat(Arrays.equals(Integers.findTwoIntegersSummingUpToTarget_BruteForce(13, new int[]{1, 2, 2, 2, 5, 5, 5, 8}), new int[] {4, 7}), is(true));
    }

    @Test
    public void findTwoIntegersSummingUpToTarget_SortingAndSimplePass() {
        assertThat(Arrays.equals(Integers.findTwoIntegersSummingUpToTarget_SortingAndSimplePass(0, new int[]{1, 2, 3}), NO_SOLUTION), is(true));
        assertThat(Arrays.equals(Integers.findTwoIntegersSummingUpToTarget_SortingAndSimplePass(5, new int[0]), NO_SOLUTION), is(true));
        assertThat(Arrays.equals(Integers.findTwoIntegersSummingUpToTarget_SortingAndSimplePass(5, new int[]{1}), NO_SOLUTION), is(true));

        assertThat(Arrays.equals(Integers.findTwoIntegersSummingUpToTarget_SortingAndSimplePass(5, new int[]{1, 2, 3}), new int[]{1, 2}), is(true));
        assertThat(Arrays.equals(Integers.findTwoIntegersSummingUpToTarget_SortingAndSimplePass(12, new int[]{1, 2, 2, 2, 5, 5, 5, 8}), NO_SOLUTION), is(true));
        assertThat(Arrays.equals(Integers.findTwoIntegersSummingUpToTarget_SortingAndSimplePass(13, new int[]{1, 2, 2, 2, 5, 5, 5, 8}), new int[] {4, 7}), is(true));
    }

    @Test
    public void findTwoIntegersSummingUpToTarget_HashMap() {
        assertThat(Arrays.equals(Integers.findTwoIntegersSummingUpToTarget_HashMap(0, new int[]{1, 2, 3}), NO_SOLUTION), is(true));
        assertThat(Arrays.equals(Integers.findTwoIntegersSummingUpToTarget_HashMap(5, new int[0]), NO_SOLUTION), is(true));
        assertThat(Arrays.equals(Integers.findTwoIntegersSummingUpToTarget_HashMap(5, new int[]{1}), NO_SOLUTION), is(true));

        assertThat(Arrays.equals(Integers.findTwoIntegersSummingUpToTarget_HashMap(5, new int[]{1, 2, 3}), new int[]{1, 2}), is(true));
        assertThat(Arrays.equals(Integers.findTwoIntegersSummingUpToTarget_HashMap(12, new int[]{1, 2, 2, 2, 5, 5, 5, 8}), NO_SOLUTION), is(true));
        assertThat(Arrays.equals(Integers.findTwoIntegersSummingUpToTarget_HashMap(13, new int[]{1, 2, 2, 2, 5, 5, 5, 8}), new int[] {4, 7}), is(true));
    }
}
