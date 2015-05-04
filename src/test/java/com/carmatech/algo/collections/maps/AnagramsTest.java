package com.carmatech.algo.collections.maps;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.Collection;
import java.util.Iterator;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

public class AnagramsTest {
    private final Collection<String> words = Lists.newArrayList("debitcard", "elvis", "silent", "badcredit", "lives",
            "freedom", "listen", "levis");

    @Test
    public void groupAnagrams() {
        Collection<Collection<String>> anagrams = Anagrams.groupAnagrams(words);

        assertThat(anagrams, hasSize(3));

        final Iterator<Collection<String>> iterator = anagrams.iterator();
        assertThat(iterator.next(), is(Lists.newArrayList("debitcard", "badcredit")));
        assertThat(iterator.next(), is(Lists.newArrayList("elvis", "lives", "levis")));
        assertThat(iterator.next(), is(Lists.newArrayList("silent", "listen")));
        assertThat(iterator.hasNext(), is(false));
    }
}
