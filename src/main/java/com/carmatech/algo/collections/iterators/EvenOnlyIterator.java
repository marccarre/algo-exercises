package com.carmatech.algo.collections.iterators;

import java.util.Iterator;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Decorate the provided iterator and iterate over only even numbers.
 */
public class EvenOnlyIterator implements Iterator<Integer> {
    private final Iterator<Integer> iterator;
    private boolean hasNext = false;
    private Integer next;

    EvenOnlyIterator(final Iterator<Integer> iterator) {
        this.iterator = checkNotNull(iterator, "Decoratee iterator must NOT be null.");
    }

    public boolean hasNext() {
        if (hasNext) {
            return true;
        }

        hasNext = false; // Reset flag.
        while (iterator.hasNext()) {
            next = iterator.next();
            if ((next != null) && (next % 2 == 0)) {
                hasNext = true;
                return true;
            }
        }
        return false;
    }

    public Integer next() {
        if (hasNext || hasNext()) {
            // Reset flag to force-pull next element, and return cached even number:
            hasNext = false;
            return next;
        } else {
            return null;
        }
    }

    public void remove() {
        throw new UnsupportedOperationException("Cannot remove from this iterator.");
    }
}
