package com.carmatech.algo.tries;

public interface Trie<V> {
    V put(String key, V value);

    V get(String key);
}
