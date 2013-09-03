package com.carmatech.algo.collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.util.Arrays;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class LinkedListTest {
	@Rule
	public final ExpectedException exception = ExpectedException.none();

	@Test
	public void createEmptyList() {
		LinkedList<String> list = new LinkedList<String>();

		assertThat(list.isEmpty(), is(true));
		assertThat(list.size(), is(0));
	}

	@Test
	public void createList() {
		LinkedList<String> list = new LinkedList<String>();
		list.add("a");
		list.add("b");
		list.add("c");

		assertThat(list.toString(), is("[a, b, c]"));
		assertThat(list.isEmpty(), is(false));
		assertThat(list.size(), is(3));
	}

	@Test
	public void insertElementAtBeginningOfEmptyList() {
		LinkedList<String> list = new LinkedList<String>();
		list.add(0, "a");
		assertThat(list.toString(), is("[a]"));
	}

	@Test
	public void insertElementAtBeginningOfList() {
		LinkedList<String> list = new LinkedList<String>();
		list.add("b");
		list.add("c");
		list.add("d");
		list.add(0, "a");
		assertThat(list.toString(), is("[a, b, c, d]"));
	}

	@Test
	public void insertElementInMiddleOfList() {
		LinkedList<String> list = new LinkedList<String>();
		list.add("a");
		list.add("b");
		list.add("d");
		list.add(2, "c");
		assertThat(list.toString(), is("[a, b, c, d]"));
	}

	@Test
	public void insertElementAtTheEndOfList() {
		LinkedList<String> list = new LinkedList<String>();
		list.add("a");
		list.add("b");
		list.add("c");
		list.add(3, "d");
		assertThat(list.toString(), is("[a, b, c, d]"));
	}

	@Test
	public void insertElementAfterEndOfListShouldThrowException() {
		exception.expect(IndexOutOfBoundsException.class);
		exception.expectMessage(equalTo("Trying to access item #4 while this list is of size 3"));

		LinkedList<String> list = new LinkedList<String>();
		list.add("a");
		list.add("b");
		list.add("c");
		list.add(4, "d");
	}

	@Test
	public void addMultipleItemsInOneGo() {
		LinkedList<String> list = new LinkedList<String>();
		list.addAll(Arrays.asList("a", "b", "c"));
		assertThat(list.toString(), is("[a, b, c]"));
	}

	@Test
	public void containsListSizeOne() {
		LinkedList<String> list = new LinkedList<String>();
		list.add("a");

		assertThat(list.contains("a"), is(true));
		assertThat(list.indexOf("a"), is(0));

		assertThat(list.contains("b"), is(false));
		assertThat(list.indexOf("b"), is(-1));
	}

	@Test
	public void containsListLargerSize() {
		LinkedList<String> list = new LinkedList<String>();
		list.addAll(Arrays.asList("a", "b", "c"));

		assertThat(list.contains("a"), is(true));
		assertThat(list.indexOf("a"), is(0));

		assertThat(list.contains("b"), is(true));
		assertThat(list.indexOf("b"), is(1));

		assertThat(list.contains("c"), is(true));
		assertThat(list.indexOf("c"), is(2));

		assertThat(list.contains("d"), is(false));
		assertThat(list.indexOf("d"), is(-1));
	}

	@Test
	public void clearList() {
		LinkedList<String> list = new LinkedList<String>();
		list.addAll(Arrays.asList("a", "b", "c"));
		assertThat(list.toString(), is("[a, b, c]"));
		assertThat(list.isEmpty(), is(false));
		list.clear();
		assertThat(list.toString(), is("[]"));
		assertThat(list.isEmpty(), is(true));
	}
}
