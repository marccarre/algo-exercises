package com.carmatech.algo.collections;

public interface IPriorityQueue<T> {
	void add(final T item);

	T next();

	T removeNext();

	boolean isEmpty();

	int size();
}
