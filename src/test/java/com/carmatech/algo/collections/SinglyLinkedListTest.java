package com.carmatech.algo.collections;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SinglyLinkedListTest {
    @Test
    public void merge() {
        SinglyLinkedList<Integer> a = new SinglyLinkedList<Integer>().add(1).add(3);
        assertThat(a.toString(), is("[1, 3]"));
        SinglyLinkedList<Integer> b = new SinglyLinkedList<Integer>().add(2).add(4).add(5);
        assertThat(b.toString(), is("[2, 4, 5]"));

        assertThat(a.merge(b).toString(), is("[1, 2, 3, 4, 5]"));
    }
}
