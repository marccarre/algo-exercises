package com.carmatech.algo.combinatorics;

import java.util.Deque;
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

	public static List<String> permuteIteratively(final String string) {
		final LinkedList<String> permutations = new LinkedList<String>();

		final Deque<String> stack = new LinkedList<String>();
		stack.push("");
		stack.push(string);

		while (!stack.isEmpty()) {
			final String remaining = stack.pop();
			final String beginning = stack.pop();

			if (remaining.length() == 0) {
				permutations.addFirst(beginning);
			} else {
				for (int i = 0; i < remaining.length(); i++) {
					// Remove i-th char and add it to the "fixed", beginning part:
					stack.push(beginning + remaining.charAt(i));
					stack.push(remaining.substring(0, i) + remaining.substring(i + 1));
				}
			}
		}

		return permutations;
	}
}
