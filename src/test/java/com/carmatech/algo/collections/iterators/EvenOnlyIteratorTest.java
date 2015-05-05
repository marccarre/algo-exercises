package com.carmatech.algo.collections.iterators;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

public class EvenOnlyIteratorTest {

    @Test
    public void empty() {
        Iterator<Integer> iterator = new EvenOnlyIterator(Lists.<Integer>newArrayList().iterator());
        assertThat(iterator.hasNext(), is(false));
        assertThat(iterator.next(), is(nullValue()));
    }

    @Test
    public void singletonNull() {
        Iterator<Integer> iterator = new EvenOnlyIterator(Lists.newArrayList((Integer) null).iterator());
        assertThat(iterator.hasNext(), is(false));
        assertThat(iterator.next(), is(nullValue()));
    }

    @Test
    public void singletonOdd() {
        Iterator<Integer> iterator = new EvenOnlyIterator(Lists.newArrayList(1).iterator());
        assertThat(iterator.hasNext(), is(false));
        assertThat(iterator.next(), is(nullValue()));
    }

    @Test
    public void singletonEven() {
        Iterator<Integer> iterator = new EvenOnlyIterator(Lists.newArrayList(2).iterator());
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(2));
        assertThat(iterator.hasNext(), is(false));
        assertThat(iterator.next(), is(nullValue()));
    }

    private Iterator<Integer> iterator = new EvenOnlyIterator(Lists.newArrayList(1, null, 2, null, 3, 4, null, 5, 6).iterator());

    @Test
    public void hasNextOnlyPullsNextValueOnce() {
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(2));
    }

    @Test
    public void nextAutomaticallyPullsNextValue() {
        assertThat(iterator.next(), is(2));
        assertThat(iterator.next(), is(4));
        assertThat(iterator.next(), is(6));
        assertThat(iterator.next(), is(nullValue()));
    }

    @Test
    public void alternatingHasNextAndNextPullsNextValueUntilExhausted() {
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(2));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(4));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(6));
        assertThat(iterator.hasNext(), is(false));
        assertThat(iterator.next(), is(nullValue()));
    }
}
