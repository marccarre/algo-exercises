package com.carmatech.algo.sorting;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class QuickSortTest {
    @Test
    public void dutchPartition() {
        assertThat(QuickSort.dutchPartition(new Integer[] { 1, 2, 3 }),       is(new Integer[] { 1, 2, 3 }));
        assertThat(QuickSort.dutchPartition(new Integer[] { 2, 2, 1 }),       is(new Integer[] { 1, 2, 2 }));
        assertThat(QuickSort.dutchPartition(new Integer[] { 3, 2, 1 }),       is(new Integer[] { 2, 1, 3 }));
        assertThat(QuickSort.dutchPartition(new Integer[] { 3, 2, 4 }),       is(new Integer[] { 2, 3, 4 }));
        assertThat(QuickSort.dutchPartition(new Integer[] { 3, 2, 4, 3, 3 }), is(new Integer[] { 2, 3, 3, 3, 4 }));
    }
}
