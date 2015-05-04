package com.carmatech.algo.collections.heaps;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

public class SortedFilesMerger {
    private final List<Iterator<String>> iteratorsList = new ArrayList<>();

    public SortedFilesMerger(final File... files) throws IOException {
        for (final File file : files) {
            // TODO: should find a way to close the LineIterators:
            iteratorsList.add(FileUtils.lineIterator(file));
        }
    }

    public SortedFilesMerger(final Iterable<Iterable<String>> streams) throws IOException {
        for (final Iterable<String> stream : streams) {
            iteratorsList.add(stream.iterator());
        }
    }

    public void writeTo(final Writer writer) throws IOException {
        final PriorityQueue<Entry<String>> minPQ = new PriorityQueue<>();
        addFirstOfEachTo(minPQ);
        while (!minPQ.isEmpty()) {
            // Write next smallest entry:
            final Entry<String> next = minPQ.poll();
            writer.write(next.value);

            // Feed priority queue with next item from iterator we just consumed from:
            final Iterator<String> iterator = next.iterator;
            if (iterator.hasNext()) {
                minPQ.add(new Entry<>(iterator.next(), iterator));
            }
        }
    }

    private void addFirstOfEachTo(final PriorityQueue<Entry<String>> minPQ) {
        for (final Iterator<Iterator<String>> iterators = iteratorsList.iterator(); iterators.hasNext(); ) {
            final Iterator<String> iterator = iterators.next();
            if (!iterator.hasNext()) {
                iterators.remove();
                continue;
            }

            minPQ.add(new Entry<>(iterator.next(), iterator));
        }
    }

    private class Entry<T extends Comparable<T>> implements Comparable<Entry<T>> {
        private Iterator<T> iterator;
        private T value;

        public Entry(final T value, final Iterator<T> iterator) {
            this.value = value;
            this.iterator = iterator;
        }

        @Override
        public int compareTo(final Entry<T> that) {
            return this.value.compareTo(that.value);
        }
    }
}
