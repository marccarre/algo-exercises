package com.carmatech.algo.collections;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.EmptyStackException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ListStackTest {
    @Rule
    public final ExpectedException exception = ExpectedException.none();
    private final ListStack<Integer> stack = new ListStack<>();

    @Test
    public void stackIsEmptyByDefault(){
        assertThat(stack.isEmpty(), is(true));
    }

    @Test
    public void poppingEmptyStackThrowsEmptyStackException(){
        exception.expect(EmptyStackException.class);
        stack.pop();
    }

    @Test
    public void pushOntoStackAndPopFromIt() {
        stack.push(1);
        assertThat(stack.isEmpty(), is(false));
        stack.push(2);
        assertThat(stack.isEmpty(), is(false));
        assertThat(stack.pop(), is(2));
        assertThat(stack.pop(), is(1));
        assertThat(stack.isEmpty(), is(true));
    }
}
