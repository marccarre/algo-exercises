package com.carmatech.algo.collections.heaps;

import org.junit.Test;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SortedFilesMergerTest {
    private final Iterable<Iterable<String>> input = initialize();
    private final StringWriter output = new StringWriter();

    private Iterable<Iterable<String>> initialize() {
        final List<Iterable<String>> lists = new LinkedList<>();
        lists.add(listFor("A", "A", "C", "E"));
        lists.add(listFor("B", "D", "F", "G", "H", "I"));
        return lists;
    }

    private List<String> listFor(final String... strings) {
        final List<String> list = new LinkedList<>();
        list.addAll(Arrays.asList(strings));
        return list;
    }

    @Test
    public void mergeLists() throws IOException {
        SortedFilesMerger merger = new SortedFilesMerger(input);
        try(BufferedWriter writer = new BufferedWriter(output)) {
            merger.writeTo(writer);
        }
        assertThat(output.toString(), is("AABCDEFGHI"));
    }
}
