package com.carmatech.algo.utilities;

import java.util.Arrays;
import java.util.Comparator;

public class ArrayMinComparator implements Comparator<int[]> {
	@Override
	public int compare(final int[] x, final int[] y) {
		final int[] xSorted = x.clone();
		final int[] ySorted = y.clone();
		Arrays.sort(xSorted);
		Arrays.sort(ySorted);
		return xSorted[0] - ySorted[0];
	}
}
