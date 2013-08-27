package com.carmatech.algo.sorting;

import java.util.Random;

public final class SortingUtilities {
	private static final Random random = new Random();

	private SortingUtilities() {
		// Pure utility class, do NOT instantiate.
	}

	public static <T extends Comparable<T>> boolean less(final T a, final T b) {
		return a.compareTo(b) < 0;
	}

	public static <T extends Comparable<T>> void swap(final T[] array, final int i, final int j) {
		final T temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

	public static <T extends Comparable<T>> boolean isSorted(final T[] array) {
		for (int i = 1; i < array.length; i++)
			if (less(array[i], array[i - 1]))
				return false;
		return true;
	}

	public static <T extends Comparable<T>> void shuffle(final T[] array) {
		final int length = array.length;
		for (int i = 0; i < length; i++) {
			final int r = random.nextInt(i + 1);
			swap(array, i, r);
		}
	}

	public static <T extends Comparable<T>> void swim(final T[] heap, int k) {
		int parent = k / 2;
		while ((k > 1) && (less(heap[parent], heap[k]))) {
			swap(heap, parent, k);
			k = parent;
			parent = k / 2;
		}
	}

	public static <T extends Comparable<T>> void sink(final T[] heap, int k, int size) {
		int child = 2 * k;
		while (child <= size) {

			if ((child < size) && (less(heap[child - 1], heap[child])))
				++child;

			if (!less(heap[k - 1], heap[child - 1]))
				break;

			swap(heap, k - 1, child - 1);
			k = child;
			child = 2 * k;
		}
	}
}
