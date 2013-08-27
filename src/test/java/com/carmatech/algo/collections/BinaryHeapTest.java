package com.carmatech.algo.collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

public class BinaryHeapTest {
	@Test
	public void binaryHeapShouldGiveNextLargeItemToClient() {
		IPriorityQueue<Integer> heap = new BinaryHeap<Integer>(5);
		heap.add(4);
		heap.add(1);
		heap.add(3);
		heap.add(5);
		heap.add(2);

		assertThat(heap.toString(), is("[null, 5, 4, 3, 1, 2]"));

		assertThat(heap.removeNext(), is(5));
		assertThat(heap.removeNext(), is(4));
		assertThat(heap.removeNext(), is(3));
		assertThat(heap.removeNext(), is(2));
		assertThat(heap.removeNext(), is(1));

		assertThat(heap.toString(), is("[null, null, null, null, null, null]"));
	}
}
