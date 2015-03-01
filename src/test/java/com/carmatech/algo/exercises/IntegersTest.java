package com.carmatech.algo.exercises;

import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class IntegersTest {
    @Test
    public void findTwoIntegersSummingUpToTarget_BruteForce() {
        final int[] noSolution = new int[0];
        assertThat(Arrays.equals(Integers.findTwoIntegersSummingUpToTarget_BruteForce(0, new int[]{1, 2, 3}), noSolution), is(true));
        assertThat(Arrays.equals(Integers.findTwoIntegersSummingUpToTarget_BruteForce(5, new int[0]), noSolution), is(true));
        assertThat(Arrays.equals(Integers.findTwoIntegersSummingUpToTarget_BruteForce(5, new int[]{1}), noSolution), is(true));

        assertThat(Arrays.equals(Integers.findTwoIntegersSummingUpToTarget_BruteForce(5, new int[]{1, 2, 3}), new int[]{1, 2}), is(true));
    }
}
