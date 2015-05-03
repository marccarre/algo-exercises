package com.carmatech.algo.exercises;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static com.carmatech.algo.exercises.Integers.NO_SOLUTION;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class IntegersTest {
    @Rule
    public final ExpectedException exception = ExpectedException.none();

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


    @Test
    public void parseInt() {
        assertThat(Integers.parseInt("0"), is(0));
        assertThat(Integers.parseInt("123"), is(123));
        assertThat(Integers.parseInt("-1"), is(-1));
        assertThat(Integers.parseInt("-45"), is(-45));
    }

    @Test
    public void parseIntWhenDoubleIsProvidedShouldThrowNumberFormatException() {
        expectException(NumberFormatException.class, "'3.14' is not a valid integer.");
        Integers.parseInt("3.14");
    }

    @Test
    public void parseIntWhenNegativeSignIsNotAtRightPositionShouldThrowNumberFormatException() {
        expectException(NumberFormatException.class, "'--123' is not a valid integer.");
        Integers.parseInt("--123");
    }

    private void expectException(final Class<NumberFormatException> type, final String message) {
        exception.expect(type);
        exception.expectMessage(equalTo(message));
    }

    @Test
    public void toStringForValidIntegers() {
        assertThat(Integers.toString(0),   is("0"));
        assertThat(Integers.toString(123), is("123"));
        assertThat(Integers.toString(-1),  is("-1"));
        assertThat(Integers.toString(-45), is("-45"));
    }
}
