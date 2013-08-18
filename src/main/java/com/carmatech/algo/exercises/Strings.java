package com.carmatech.algo.exercises;

import java.util.HashSet;
import java.util.Set;

public final class Strings {
	private Strings() {
		// Pure utility class, do NOT instantiate.
	}

	public static boolean containsDuplicate(final String string) {
		final Set<Character> seenChars = new HashSet<Character>();
		final char[] chars = string.toLowerCase().toCharArray();
		final int length = chars.length;

		for (int i = 0; i < length; ++i) {
			final char currentChar = chars[i];
			if (seenChars.contains(currentChar))
				return true;
			else
				seenChars.add(currentChar);
		}
		return false;
	}
}
