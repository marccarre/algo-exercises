package com.carmatech.algo.exercises;

import java.util.HashSet;
import java.util.Set;

public final class Strings {
	private Strings() {
		// Pure utility class, do NOT instantiate.
	}

	/**
	 * Time complexity:  O(n), n being the length of the provided string.
     * Space complexity: O(n), as we keep track of all visited {@code char}s.
	 * 
	 * @param string
	 *            String to check for duplicates.
	 * @return true if the same character has been seen twice, false otherwise.
	 */
	public static boolean containsDuplicate(final String string) {
		final Set<Character> characters = new HashSet<Character>();
		final int length = string.length();
		for (int i = 0; i < length; ++i) {
			final char currentChar = string.charAt(i);
			if (characters.contains(currentChar)) {
                return true;
            }
			characters.add(currentChar);
		}
		return false;
	}

	/**
	 * Time complexity:  O(n^2), n being the length of the provided string.
     * Space complexity: O(1), as we just keep two pointers.
	 * 
	 * @param string
	 *            String to check for duplicates.
	 * @return true if the same character has been seen twice, false otherwise.
	 */
	public static boolean containsDuplicateLessMemoryMoreTime(final String string) {
        for (int i = 0; i < string.length(); ++i)
			for (int j = i + 1; j < string.length(); ++j)
				if (string.charAt(i) == string.charAt(j))
					return true;
		return false;
	}

	/**
	 * Time complexity:  O(n), n being the length of the provided string.
     * Space complexity: O(1), as we use an integer as a bit-set to keep track of the characters already seen.
     *
     * WARNING: Provided string MUST ONLY CONTAIN [a-z] CHARACTERS, as the bit-set has a capacity of 32 distinct characters.
	 * 
	 * @param string
	 *            String to check for duplicates
	 * @return true if the same character has been seen twice, false otherwise.
	 */
	public static boolean containsDuplicateBestButOnlyLowerCasedAsciiLetters(final String string) {
        int charactersBitSet = 0;
		for (int i = 0; i < string.length(); ++i) {
            // Current char - 'a' gives us a short int with a value between 0 and 26 (assuming contract is respected), which is less than 32.
            // We then use this to find the position in the bit-set as: position == 1 << char == 1 * 2^char
            final int position = 1 << (string.charAt(i) - 'a');
			if ((charactersBitSet & position) > 0) {
                return true;
            }
			charactersBitSet |= position;
		}
		return false;
	}

	public static void reverseCStyleString(final char[] string) {
		final int length = string.length - 1; // Ignore the last '\0' char.
		final int mid = (length / 2);
		for (int i = 0, j = length - 1; i < mid; ++i, --j)
			swap(string, i, j);
	}

	private static void swap(final char[] string, final int i, final int j) {
		final char temp = string[i];
		string[i] = string[j];
		string[j] = temp;
	}
}
