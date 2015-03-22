package com.carmatech.algo.collections;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

public class LinkedList<T> implements List<T> {
	private static class Node<T> implements Iterator<T> {
		public Node(final T value) {
			this(value, null);
		}

		public Node(final T value, final Node<T> next) {
			this.value = value;
			this.next = next;
		}

		public Node<T> next;
		public T value;

		@Override
		public boolean hasNext() {
			return (next != null);
		}

		@Override
		public T next() {
			return next.value;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException("Not implemented");
		}
	}

	private Node<T> head = null;

	@Override
	public boolean add(final T item) {
		if (head == null) {
			head = new Node<T>(item);
			return true;
		}

		final Node<T> tail = findTail();
		tail.next = new Node<T>(item);
		return true;
	}

	private Node<T> findTail() {
		Node<T> current = head;
		while (current.next != null)
			current = current.next;
		return current;
	}

	private Node<T> find(final int index) {
		int count = 0;
		Node<T> current = head;
		while ((current.next != null) && ((count + 1) != index)) {
			current = current.next;
			++count;
		}

		if ((count + 1) != index)
			throw new IndexOutOfBoundsException("Trying to access item #" + index + " while this list is of size " + (count + 1));

		return current;
	}

	@Override
	public void add(final int index, final T item) {
		if (index == 0) {
			final Node<T> newNode = new Node<T>(item, head);
			head = newNode;
		} else {
			final Node<T> previous = find(index);
			final Node<T> newNode = new Node<T>(item, previous.next);
			previous.next = newNode;
		}
	}

	@Override
	public boolean addAll(final Collection<? extends T> items) {
		boolean result = true;
		for (final T item : items)
			result &= add(item);
		return result;
	}

	@Override
	public boolean addAll(final int index, final Collection<? extends T> items) {
		throw new UnsupportedOperationException("Not implemented");
	}

	@Override
	public void clear() {
		Node<T> current = head;

		while (current != null) {
			current.value = null;
			final Node<T> next = current.next;
			current.next = null;
			current = next;
		}

		head = null;
	}

	@Override
	public boolean contains(final Object object) {
		Node<T> current = head;

		while (!current.value.equals(object) && (current.next != null))
			current = current.next;

		if (current.value.equals(object))
			return true;
		return false;
	}

	@Override
	public boolean containsAll(final Collection<?> items) {
		for (final Object item : items)
			if (!contains(item))
				return false;
		return true;
	}

	@Override
	public T get(final int index) {
		return find(index).value;
	}

	@Override
	public int indexOf(final Object object) {
		Node<T> current = head;
		int index = 0;

		while (!current.value.equals(object) && (current.next != null)) {
			current = current.next;
			++index;
		}

		if (current.value.equals(object))
			return index;
		return -1;
	}

	@Override
	public boolean isEmpty() {
		return (head == null);
	}

	@Override
	public Iterator<T> iterator() {
		return head;
	}

	@Override
	public int lastIndexOf(final Object object) {
		throw new UnsupportedOperationException("Not implemented");
	}

	@Override
	public ListIterator<T> listIterator() {
		throw new UnsupportedOperationException("Not implemented");
	}

	@Override
	public ListIterator<T> listIterator(final int startIndex) {
		throw new UnsupportedOperationException("Not implemented");
	}

	@Override
	public boolean remove(final Object object) {
		if (head.value.equals(object)) {
			head = head.next;
			return true;
		}

		Node<T> current = head;

		while (current.next != null) {
			if (current.next.value.equals(object)) {
				current.next = current.next.next;
				return true;
			}
			current = current.next;
		}

		return false;
	}

	@Override
	public T remove(final int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean removeAll(final Collection<?> objects) {
		boolean removedAll = true;
		for (final Object object : objects) {
			boolean removed = remove(object);
			removedAll &= removed;
			while (removed)
				removed = remove(object); // Remove duplicates
		}
		return removedAll;
	}

	@Override
	public boolean retainAll(final Collection<?> objects) {
		throw new UnsupportedOperationException("Not implemented");
	}

	@Override
	public T set(final int index, final T item) {
		throw new UnsupportedOperationException("Not implemented");
	}

	@Override
	public int size() {
		int size = 0;
		Node<T> current = head;

		while (current != null) {
			++size;
			current = current.next;
		}

		return size;
	}

	@Override
	public List<T> subList(final int fromIndex, final int toIndex) {
		throw new UnsupportedOperationException("Not implemented");
	}

	@Override
	public Object[] toArray() {
		throw new UnsupportedOperationException("Not implemented");
	}

	@SuppressWarnings("hiding")
	@Override
	public <T> T[] toArray(final T[] array) {
		throw new UnsupportedOperationException("Not implemented");
	}

	@Override
	public String toString() {
		if (head == null)
			return "[]";

		final StringBuilder b = new StringBuilder();
		b.append("[");
		Node<T> current = head;
		b.append(current.value.toString());

		while (current.next != null) {
			current = current.next;
			b.append(", ");
			b.append(current.value == null ? "null" : current.value.toString());
		}
		b.append("]");
		return b.toString();
	}

    public void removeDuplicatesLowMemory() {
        for (Node<T> i = head; i != null; i = i.next)
            for (Node<T> j = i; j != null; j = j.next)
                if ((j.next != null) && (i.value != null) && (i.value.equals(j.next.value)))
                    j.next = j.next.next;
    }

    public void removeDuplicates() {
        final Set<T> values = new HashSet<T>();
        for (Node<T> i = head; i != null; i = i.next) {
            values.add(i.value);
            if ((i.next != null) && values.contains(i.next.value))
                i.next = i.next.next;
        }
    }

    public T getFromEnd(final int index) {
        if (index <= 0)
            return null;
        Node<T> i = head;
        Node<T> j = advance(head, index);
        if (j == null)
            return null;
        for (;; i = i.next, j = j.next)
            if (j.next == null)
                return i.value;
    }

    private Node<T> advance(final Node<T> current, final int index) {
        if (current == null)
            return null;
        Node<T> node = current;
        for (int i = 0; i < index - 1; ++i) {
            if (node == null)
                break;
            node = node.next;
        }
        return node;
    }
}
