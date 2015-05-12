package com.carmatech.algo.combinatorics;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class RectanglesTilingTest {
    @Test
    public void greedyCountNonOverlappingSquaresToFill() {
        assertThat(RectanglesTiling.greedyCountSquaresToFill(5, 5), is(1));
        assertThat(RectanglesTiling.greedyCountSquaresToFill(4, 6), is(3));
        assertThat(RectanglesTiling.greedyCountSquaresToFill(13, 21), is(7)); // Fibonacci's kitchen.
        assertThat(RectanglesTiling.greedyCountSquaresToFill(5, 6), is(6)); // NOT the minimum!
    }

    @Test
    public void linearSplitCountNonOverlappingSquaresToFill() {
        assertThat(RectanglesTiling.linearSplitCountSquaresToFill(5, 5), is(1));
        assertThat(RectanglesTiling.linearSplitCountSquaresToFill(4, 6), is(3));
        assertThat(RectanglesTiling.linearSplitCountSquaresToFill(13, 21), is(7)); // Fibonacci's kitchen.
        assertThat(RectanglesTiling.linearSplitCountSquaresToFill(5, 6), is(5)); // Minimum found!
    }
}
