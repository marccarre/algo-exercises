package com.carmatech.algo.utilities;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

public class ArrayMinComparatorTest {
	private static final ArrayMinComparator minComparator = new ArrayMinComparator();

	@Test
	public void compareArraysShouldReturnArrayWithMinimumElementAsSmallerArray() {
		int[] x = new int[] { 1, 2, 3 };
		int[] y = new int[] { 4, 3, 2 };
		assertThat(minComparator.compare(x, y), is(-1));

		x = new int[] { 1, 2, 3 };
		y = new int[] { 3, 1, 4 };
		assertThat(minComparator.compare(x, y), is(0));

		x = new int[] { 2, 2, 3 };
		y = new int[] { 3, 1, 4 };
		assertThat(minComparator.compare(x, y), is(1));
	}

	@Test
	public void sortListOfArraysByTheirMinimum() {
		int[] x = new int[] { 1, 2, 3 };
		int[] y = new int[] { 4, 3, 2 };
		int[] z = new int[] { 4, 5, 3 };

		List<int[]> arrays = Arrays.asList(z, x, y);
		Collections.sort(arrays, minComparator);

		List<int[]> expectedArrays = Arrays.asList(x, y, z);
		assertThat(arrays, equalTo(expectedArrays));
	}
}
