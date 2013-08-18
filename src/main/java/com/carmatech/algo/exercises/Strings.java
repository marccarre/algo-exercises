package com.carmatech.algo.exercises;

import java.util.HashSet;
import java.util.Set;

public final class Strings {
	private Strings() {
		// Pure utility class, do NOT instantiate.
	}

	/**
	 * Time complexity O(n), n being the length of the provided string. Space complexity O(n), as we keep track of all visited {@code char}s.
	 * 
	 * @param string
	 *            String to check for duplicates.
	 * @return true if the same character has been seen twice, false otherwise.
	 */
	public static boolean containsDuplicate(final String chars) {
		final Set<String> seenChars = new HashSet<String>();
		final int length = chars.length();

		for (int i = 0; i < length; ++i) {
			final String currentChar = chars.substring(i, i + 1);
			if (seenChars.contains(currentChar))
				return true;
			else
				seenChars.add(currentChar);
		}
		return false;
	}

	/**
	 * Time complexity O(n^2), n being the length of the provided string. Space complexity O(1), as we just keep two pointers.
	 * 
	 * @param string
	 *            String to check for duplicates.
	 * @return true if the same character has been seen twice, false otherwise.
	 */
	public static boolean containsDuplicateLessMemoryMoreTime(final String string) {
		final int length = string.length();

		for (int i = 0; i < length; ++i)
			for (int j = i + 1; j < length; ++j)
				if (string.substring(i, i + 1).equals(string.substring(j, j + 1)))
					return true;
		return false;
	}

	/**
	 * Time complexity O(n), n being the length of the provided string. Space complexity O(1), as we just keep two pointers. Trick: We use an integer to keep
	 * track of the characters already seen. WARNING: Provided string MUST ONLY CONTAIN ASCII CHARACTERS.
	 * 
	 * @param string
	 *            String to check for duplicates
	 * @return true if the same character has been seen twice, false otherwise.
	 */
	public static boolean containsDuplicateBestButAsciiOnly(final String string) {
		final int length = string.length();
		int seenChars = 0;

		for (int i = 0; i < length; ++i) {
			final int currentChar = string.charAt(i);
			final int bitPosition = (1 << currentChar); // 1 * 2^currentChar.
			if ((seenChars & bitPosition) > 0)
				return true;
			seenChars |= bitPosition;
		}
		return false;
	}
}
