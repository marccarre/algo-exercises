package com.carmatech.algo.collections.heaps;

import com.carmatech.algo.collections.IPriorityQueue;

import static com.carmatech.algo.sorting.SortingUtilities.less;
import static com.carmatech.algo.sorting.SortingUtilities.swap;

import java.util.Arrays;

public class UnorderedMaxPQ<T extends Comparable<T>> implements IPriorityQueue<T> {
	private final T[] queue;
	private int size = 0;

	@SuppressWarnings("unchecked")
	UnorderedMaxPQ(final int size) {
		queue = (T[]) new Comparable<?>[size];
	}

	@Override
	public void add(final T item) {
		queue[size++] = item;
	}

	@Override
	public T next() {
		sortPriorityQueue();
		return queue[size];
	}

	@Override
	public T removeNext() {
		sortPriorityQueue();
		final T max = queue[--size];
		queue[size] = null;
		return max;
	}

	private void sortPriorityQueue() {
		int max = 0;
		for (int i = 1; i < size; ++i)
			if (less(queue[max], queue[i]))
				max = i;
		swap(queue, max, size - 1);
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
		return Arrays.toString(queue);
	}
}
