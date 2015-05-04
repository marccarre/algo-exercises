package com.carmatech.algo.exercises;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ArraysTest {
    @Test
    public void rotate90DegreesMatrixOfSize4() {
        int[][] image = new int[][] {
                new int[] {1,  2,  3,  4},
                new int[] {5,  6,  7,  8},
                new int[] {9,  10, 11, 12},
                new int[] {13, 14, 15, 16}
        };
        int[][] rotated = new int[][] {
                new int[] {13, 9,  5, 1},
                new int[] {14, 10, 6, 2},
                new int[] {15, 11, 7, 3},
                new int[] {16, 12, 8, 4}
        };
        assertThat(Arrays.rotate90Degrees(image), is(rotated));
    }

    @Test
    public void rotate90DegreesMatrixOfSize5() {
        int[][] image = new int[][] {
                new int[] {1,  2,  3,  4,  5},
                new int[] {6,  7,  8,  9,  10},
                new int[] {11, 12, 13, 14, 15},
                new int[] {16, 17, 18, 19, 20},
                new int[] {21, 22, 23, 24, 25}
        };
        int[][] rotated = new int[][] {
                new int[] {21, 16, 11, 6,  1},
                new int[] {22, 17, 12, 7,  2},
                new int[] {23, 18, 13, 8,  3},
                new int[] {24, 19, 14, 9,  4},
                new int[] {25, 20, 15, 10, 5}
        };
        assertThat(Arrays.rotate90Degrees(image), is(rotated));
    }

    @Test
    public void spiralOfSize4() {
        int[][] expected = new int[][] {
                new int[] {1,  2,  3,  4},
                new int[] {12, 13, 14, 5},
                new int[] {11, 16, 15, 6},
                new int[] {10, 9,  8,  7}
        };
        assertThat(Arrays.spiral(4), is(expected));
    }

    @Test
    public void spiralOfSize5() {
        int[][] expected = new int[][] {
                new int[] {1,  2,  3,  4,  5},
                new int[] {16, 17, 18, 19, 6},
                new int[] {15, 24, 25, 20, 7},
                new int[] {14, 23, 22, 21, 8},
                new int[] {13, 12, 11, 10, 9}
        };
        assertThat(Arrays.spiral(5), is(expected));
    }

    @Test
    public void countOccurrences() {
        assertThat(Arrays.count(new int[]{1, 1, 2, 2, 3, 3, 4, 4, 7, 7, 7, 7, 9, 10, 12, 100}, 7), is(4));
    }

    @Test
    public void binarySearch() {
        assertThat(Arrays.binarySearch(6, new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 }), is(5));

        assertThat(Arrays.binarySearch(11, new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 }), is(-1));
        assertThat(Arrays.binarySearch(0, new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 }), is(-1));
        assertThat(Arrays.binarySearch(6, new Integer[] { 1, 2, 3, 4, 5, 7, 8, 9, 10 }), is(-1));

        assertThat(Arrays.binarySearch(null, new Integer[] { 1, 2, 3, 4, 5, 7, 8, 9, 10 }), is(-1));
        assertThat(Arrays.binarySearch(6, null), is(-1));
        assertThat(Arrays.binarySearch(6, new Integer[0]), is(-1));
        assertThat(Arrays.binarySearch(6, new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, null), is(-1));
    }

    @Test
    public void intersection() {
        assertThat(
            Arrays.intersection(
                new int[] { 2, 3, 3, 5, 5, 6, 7, 7, 8, 12 },
                new int[] { 5, 5, 6, 8, 8, 9, 10, 10 }),
            is(new int[] { 5, 6, 8 }));

        assertThat(
            Arrays.intersection(
                new int[] { 2, 3, 5, 7, 11 },
                new int[] { 3, 7, 15, 31 }),
            is(new int[] { 3, 7 }));
    }
}
