package com.carmatech.algo.collections.maps;

import java.util.Arrays;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class Anagrams {
    public static Collection<Collection<String>> groupAnagrams(final Collection<String> words) {
        final Map<String, Collection<String>> anagrams = new LinkedHashMap<>();
        for (final String word : words) {
            getOrCreateGroupFor(sort(word), anagrams).add(word);
        }
        removeSingletons(anagrams);
        return anagrams.values();
    }

    private static void removeSingletons(final Map<String, Collection<String>> anagrams) {
        for (final Iterator<Map.Entry<String, Collection<String>>> iterator = anagrams.entrySet().iterator(); iterator.hasNext(); ) {
            final Map.Entry<String, Collection<String>> entry = iterator.next();
            if (entry.getValue().size() < 2) {
                iterator.remove();
            }
        }
    }

    private static String sort(final String string) {
        final char[] array = string.toCharArray();
        Arrays.sort(array);
        return new String(array);
    }

    private static <T> Collection<T> getOrCreateGroupFor(final T key, final Map<T, Collection<T>> map) {
        Collection<T> bucket = map.get(key);
        if (bucket == null) {
            bucket = new ArrayList<>();
            map.put(key, bucket);
        }
        return bucket;
    }
}
