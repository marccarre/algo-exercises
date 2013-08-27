package com.carmatech.algo.collections;

public interface IIndexedPriorityQueue<T> {
	void add(final int index, final T item);

	void decreasePriority(final int index, final T newItem);

	boolean contains(final int index);

	int next();

	int removeNext();

	boolean isEmpty();

	int size();
}
