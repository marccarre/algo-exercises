package com.carmatech.algo.exercises;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
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

    /**
     * Time complexity:  O(3n+2n.log(n)) = O(n.log(n))
     * Space complexity: O(2n) = O(n)
     */
    public static boolean areAnagramsUsingSorting(final String s1, final String s2) {
        if ((s1 == null) || (s2 == null) || (s1.length() != s2.length()))
            return false;
        final char[] a1 = s1.toCharArray();
        final char[] a2 = s2.toCharArray();
        Arrays.sort(a1);
        Arrays.sort(a2);
        return Arrays.equals(a1, a2);
    }

    /**
     * Time complexity:  O(3n+6) = O(n)
     * Space complexity: O(n)
     */
    public static boolean areAnagramsUsingHashMap(final String s1, final String s2) {
        if ((s1 == null) || (s2 == null) || (s1.length() != s2.length()))
            return false;
        final Map<Character, Integer> occurrences = new HashMap<>();
        countCharOccurrences(s1, occurrences);
        countCharOccurrences(s2, occurrences);
        for (final int count : occurrences.values())
            if (count != 2)
                return false;
        return true;
    }

    private static void countCharOccurrences(final String string, final Map<Character, Integer> occurrences) {
        for (int i = 0; i < string.length(); ++i) {
            final char currentChar = string.charAt(i);
            occurrences.put(currentChar, occurrences.containsKey(currentChar) ? occurrences.get(currentChar) + 1 : 1);
        }
    }

	public static String longestPalindromeBruteForce(final String s) {
		if (s == null || s.length() <= 1)
			return s;

		int maxLength = 0;
		int begin = 0;
		int end = 0;

		for (int i = 0; i < s.length(); ++i) {
			for (int j = i + 1; j < s.length(); ++j) {
				int length = j - i + 1;
				if ((length > maxLength) && isPalindrome(s, i, j)) {
					maxLength = length;
					begin = i;
					end = j;
				}
			}
		}

		return s.substring(begin, end + 1);
	}

	private static boolean isPalindrome(final String s, int i, int j) {
		while (i < j) {
			if (s.charAt(i) != s.charAt(j))
				return false;
			++i;
			--j;
		}
		return true;
	}

	public static String longestPalindromeExpandAroundCenter(final String s) {
		if (s == null || s.length() <= 1)
			return s;
		String longest = s.substring(0, 1);
		String current;
		for (int i = 0; i < s.length()-1; ++i) {
			current = expandFromCenter(s, i, i);
			if (current.length() > longest.length()) {
				longest = current;
				// System.out.println("Longest: " + longest + " (" + i + "," + i + ")");
			}
			current = expandFromCenter(s, i, i+1);
			if (current.length() > longest.length()) {
				longest = current;
				// System.out.println("Longest: " + longest + " (" + i + "," + (i+1) + ")");
			}
		}
		return longest;
	}

	private static String expandFromCenter(final String s, int i, int j) {
		// System.out.println("B: i=" + i + ", j=" + j);
		while ((i >= 0) && (j < s.length()) && (s.charAt(i) == s.charAt(j))) {
			--i;
			++j;
		}

		// System.out.println("E: i=" + i + ", j=" + j);
		i++;
		j--;
		return (i > j) ? "" : s.substring(i, j+1);
	}
}
