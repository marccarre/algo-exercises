package com.carmatech.algo.collections.heaps;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import com.carmatech.algo.collections.IPriorityQueue;
import org.junit.Test;

public class UnorderedMaxPQTest {
	@Test
	public void priorityQueueShouldGiveNextLargeItemToClient() {
		IPriorityQueue<Integer> queue = new UnorderedMaxPQ<Integer>(5);
		queue.add(4);
		queue.add(1);
		queue.add(3);
		queue.add(5);
		queue.add(2);

		assertThat(queue.toString(), is("[4, 1, 3, 5, 2]"));

		assertThat(queue.removeNext(), is(5));
		assertThat(queue.removeNext(), is(4));
		assertThat(queue.removeNext(), is(3));
		assertThat(queue.removeNext(), is(2));
		assertThat(queue.removeNext(), is(1));

		assertThat(queue.toString(), is("[null, null, null, null, null]"));
	}
}
