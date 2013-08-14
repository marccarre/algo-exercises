package com.carmatech.algo.sorting;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

public class SortingUtilitiesTest {
	@Test
	public void oneShouldBeLessThanTwo() {
		assertThat(SortingUtilities.less(1, 2), is(true));
	}

	@Test
	public void oneShouldNotBeLessThanItself() {
		assertThat(SortingUtilities.less(1, 1), is(false));
	}

	@Test
	public void twoShouldNotBeLessThanOne() {
		assertThat(SortingUtilities.less(2, 1), is(false));
	}

	@Test
	public void swapShouldExchangeItemsPositionInProvidedArray() {
		final String[] array = new String[] { "foo", "bar" };

		SortingUtilities.swap(array, 0, 1);
		assertThat(array, equalTo(new String[] { "bar", "foo" }));
	}

	@Test
	public void isSortedShouldReturnTrueWhenPassedCollectionIsSortedAndFalseOtherwise() {
		assertThat(SortingUtilities.isSorted(new Integer[] { 1, 2, 3 }), is(true));
		assertThat(SortingUtilities.isSorted(new Integer[] { 1, 3, 2 }), is(false));
	}
}
