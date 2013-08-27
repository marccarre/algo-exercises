package com.carmatech.algo.collections;

import static com.carmatech.algo.sorting.SortingUtilities.less;
import static com.carmatech.algo.sorting.SortingUtilities.swap;

import java.util.Arrays;

public class BinaryHeap<T extends Comparable<T>> implements IPriorityQueue<T> {
	private final T[] heap;
	private int size = 0;

	@SuppressWarnings("unchecked")
	public BinaryHeap(final int size) {
		heap = (T[]) new Comparable<?>[size + 1];
	}

	@Override
	public void add(final T item) {
		heap[++size] = item;
		swim(heap, size);
	}

	@Override
	public T next() {
		return heap[1];
	}

	@Override
	public T removeNext() {
		final T max = heap[1];
		swap(heap, 1, size--);
		sink(heap, 1, size);
		heap[size + 1] = null;
		return max;
	}

	@Override
	public boolean isEmpty() {
		return (size == 0);
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public String toString() {
		return Arrays.toString(heap);
	}

	private static <T extends Comparable<T>> void swim(final T[] heap, int k) {
		int parent = k / 2;
		while ((k > 1) && (less(heap[parent], heap[k]))) {
			swap(heap, parent, k);
			k = parent;
			parent = k / 2;
		}
	}

	private static <T extends Comparable<T>> void sink(final T[] heap, int k, int size) {
		int child = 2 * k;
		while (child <= size) {

			if ((child < size) && (less(heap[child], heap[child + 1])))
				++child;

			if (!less(heap[k], heap[child]))
				break;

			swap(heap, k, child);
			k = child;
			child = 2 * k;
		}
	}
}
