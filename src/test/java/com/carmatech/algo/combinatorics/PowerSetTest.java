package com.carmatech.algo.combinatorics;

import org.junit.Test;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import static com.google.common.collect.Sets.newHashSet;
import static java.lang.Math.pow;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class PowerSetTest {
    private final HashSet<Integer> set = newHashSet(1, 2, 3);

    @Test
    public void allSubsets() {
        final Set<Set<Integer>> subsets = PowerSet.subsets(set);
        assertThat(subsets.size(), is((int) pow(2, set.size())));

        final Iterator<Set<Integer>> iterator = subsets.iterator();
        assertThat(iterator.next(), is(emptySet(Integer.class)));
        assertThat(iterator.next(), is(set(1)));
        assertThat(iterator.next(), is(set(2)));
        assertThat(iterator.next(), is(set(1, 2)));
        assertThat(iterator.next(), is(set(3)));
        assertThat(iterator.next(), is(set(1, 3)));
        assertThat(iterator.next(), is(set(2, 3)));
        assertThat(iterator.next(), is(set(1, 2, 3)));
    }

    @Test
    public void subsetsOfSizeTwo() {
        final Set<Set<Integer>> subsets = PowerSet.subsets(set, 2);
        assertThat(subsets.size(), is(3));

        final Iterator<Set<Integer>> iterator = subsets.iterator();
        assertThat(iterator.next(), is(set(1, 2)));
        assertThat(iterator.next(), is(set(1, 3)));
        assertThat(iterator.next(), is(set(2, 3)));
    }

    private static <E> Set<E> set(final E... elements) {
        return newHashSet(elements);
    }

    private static <E> Set<E> emptySet(final Class<E> e) {
        return Collections.emptySet();
    }
}
