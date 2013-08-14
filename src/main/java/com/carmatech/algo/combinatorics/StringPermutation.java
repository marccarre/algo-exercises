package com.carmatech.algo.combinatorics;

import java.util.LinkedList;
import java.util.List;

public final class StringPermutation {
	private StringPermutation() {
		// Pure utility class, do NOT instantiate.
	}

	public static List<String> permute(final String string) {
		final List<String> permutations = new LinkedList<String>();
		permute(permutations, "", string);
		return permutations;
	}

	private static void permute(final List<String> permutations, final String beginning, final String remaining) {
		if (remaining.length() == 0) {
			permutations.add(beginning);
			return;
		}

		for (int i = 0; i < remaining.length(); i++) {
			// Remove i-th char...
			final String newRemaining = remaining.substring(0, i) + remaining.substring(i + 1);
			// ... and add it to the "fixed", beginning part:
			final String newBeginning = beginning + remaining.charAt(i);

			permute(permutations, newBeginning, newRemaining);
		}
	}
}
