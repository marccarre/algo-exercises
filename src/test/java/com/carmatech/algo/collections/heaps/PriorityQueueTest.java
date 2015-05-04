package com.carmatech.algo.collections.heaps;

import org.junit.Test;

import java.util.Arrays;
import java.util.PriorityQueue;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class PriorityQueueTest {
    @Test
    public void minPriorityQueue() {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.addAll(Arrays.asList(1, 2, 3));
        assertThat(pq.peek(), is(1));
    }

    @Test
    public void maxPriorityQueue() {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> { return Integer.compare(b, a); });
        pq.addAll(Arrays.asList(1, 2, 3));
        assertThat(pq.peek(), is(3));
    }
}
