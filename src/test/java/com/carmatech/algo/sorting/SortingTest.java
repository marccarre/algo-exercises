package com.carmatech.algo.sorting;

import static com.carmatech.algo.sorting.SortingUtilities.isSorted;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Before;
import org.junit.Test;

public class SortingTest {
	final Integer[] array = new Integer[] { 3, 2, 1 };

	@Before
	public void preConditions() {
		assertThat(isSorted(array), is(false));
	}

	@Test
	public void selectSortShouldSortProvidedArray() {
		Sorting.selectionSort(array);
		assertThat(isSorted(array), is(true));
	}

	@Test
	public void insertionSortShouldSortProvidedArray() {
		Sorting.insertionSort(array);
		assertThat(isSorted(array), is(true));
	}

	@Test
	public void shellSortShouldSortProvidedArray() {
		Sorting.shellSort(array);
		assertThat(isSorted(array), is(true));
	}

	@Test
	public void mergeSortShouldSortProvidedArray() {
		Sorting.mergeSort(array);
		assertThat(isSorted(array), is(true));
	}

	@Test
	public void quickSortShouldSortProvidedArray() {
		Sorting.quickSort(array);
		assertThat(isSorted(array), is(true));
	}

	@Test
	public void heapSortShouldSortProvidedArray() {
		Sorting.heapSort(array);
		assertThat(isSorted(array), is(true));
	}
}
