package com.carmatech.algo.collections.stacks;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

public class MaxAwareListStackTest {
    private final MaxAwareListStack<Integer> stack = new MaxAwareListStack<>();

    @Test
    public void isEmpty() {
        assertThat(stack.isEmpty(), is(true));
    }

    @Test
    public void keepTrackOfCurrentMax() {
        stack.push(1);
        assertThat(stack.isEmpty(), is(false));
        assertThat(stack.max(), is(1));
        stack.push(2);
        assertThat(stack.max(), is(2));
        stack.push(0);
        assertThat(stack.max(), is(2));
        stack.push(1);
        assertThat(stack.max(), is(2));

        assertThat(stack.peek(), is(1));
        assertThat(stack.pop(), is(1));
        assertThat(stack.max(), is(2));

        assertThat(stack.pop(), is(0));
        assertThat(stack.max(), is(2));

        assertThat(stack.pop(), is(2));
        assertThat(stack.max(), is(1));

        assertThat(stack.pop(), is(1));
        assertThat(stack.isEmpty(), is(true));
        assertThat(stack.max(), is(nullValue()));
    }
}
