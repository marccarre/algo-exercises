package com.carmatech.algo.tries;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

public class TrieTest {
    @Test
    public void benchmark() {
        final DynamicRWayTrie<Integer> rWayTrie = new DynamicRWayTrie<>();
        rWayTrie.put("existing", 42);

        final Trie<Integer> ternaryTrie = new TernaryTrie<>();
        ternaryTrie.put("existing", 42);

        final Map<String, Integer> hashMap = new HashMap<>();
        hashMap.put("existing", 42);

        final ReferenceRWayTrie<Integer> referenceRWayTrie = new ReferenceRWayTrie<>();
        referenceRWayTrie.put("existing", 42);

        final ReferenceTernaryTrie<Integer> referenceTernaryTrie = new ReferenceTernaryTrie<>();
        referenceTernaryTrie.put("existing", 42);

        final int numIterations = 1_000_000;

        System.out.println("R-way trie:");
        System.out.println(benchmark(() -> assertThat(rWayTrie.get("existing"), is(42)), numIterations));
        System.out.println(benchmark(() -> assertThat(rWayTrie.get("non-existing"), is(nullValue())), numIterations));

        System.out.println("Ternary trie:");
        System.out.println(benchmark(() -> assertThat(ternaryTrie.get("existing"), is(42)), numIterations));
        System.out.println(benchmark(() -> assertThat(ternaryTrie.get("non-existing"), is(nullValue())), numIterations));

        System.out.println("Hash-map trie:");
        System.out.println(benchmark(() -> assertThat(hashMap.get("existing"), is(42)), numIterations));
        System.out.println(benchmark(() -> assertThat(hashMap.get("non-existing"), is(nullValue())), numIterations));

        System.out.println("Princeton's reference R-way trie:");
        System.out.println(benchmark(() -> assertThat(referenceRWayTrie.get("existing"), is(42)), numIterations));
        System.out.println(benchmark(() -> assertThat(referenceRWayTrie.get("non-existing"), is(nullValue())), numIterations));

        System.out.println("Princeton's reference ternary trie:");
        System.out.println(benchmark(() -> assertThat(referenceTernaryTrie.get("existing"), is(42)), numIterations));
        System.out.println(benchmark(() -> assertThat(referenceTernaryTrie.get("non-existing"), is(nullValue())), numIterations));
    }

    private long benchmark(final Runnable lambda, final int numIterations) {
        // Warm-up:
        for (int i = 0; i < numIterations; ++i)
            lambda.run();

        // Timed run:
        final long begin = System.nanoTime();
        for (int i = 0; i < numIterations; ++i)
            lambda.run();
        return System.nanoTime() - begin;
    }
}
