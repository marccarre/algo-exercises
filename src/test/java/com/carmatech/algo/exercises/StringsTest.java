package com.carmatech.algo.exercises;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

import org.junit.Test;

public class StringsTest {
	@Test
	public void javaShouldContainDuplicateCharsWhileSingleShouldNot() {
		assertThat(Strings.containsDuplicate("java"), is(true));
		assertThat(Strings.containsDuplicate("single"), is(false));
	}

	@Test
	public void javaShouldContainDuplicateCharsWhileSingleShouldNot_LessMemoryMoreTime() {
		assertThat(Strings.containsDuplicateLessMemoryMoreTime("java"), is(true));
		assertThat(Strings.containsDuplicateLessMemoryMoreTime("single"), is(false));
	}

	@Test
	public void javaShouldContainDuplicateCharsWhileSingleShouldNot_BestButAsciiOnly() {
        assertThat(Strings.containsDuplicateBestButOnlyLowerCasedAsciiLetters("java"), is(true));
		assertThat(Strings.containsDuplicateBestButOnlyLowerCasedAsciiLetters("single"), is(false));
	}

	@Test
	public void reverseCStyleString() {
		char[] oddString = new char[] { 'j', 'a', 'v', 'a', '6', '\0' };
		Strings.reverseCStyleString(oddString);
		assertThat(oddString, equalTo(new char[] { '6', 'a', 'v', 'a', 'j', '\0' }));

		char[] evenString = new char[] { 'j', 'a', 'v', 'a', '\0' };
		Strings.reverseCStyleString(evenString);
		assertThat(evenString, equalTo(new char[]{'a', 'v', 'a', 'j', '\0'}));
	}

    @Test
    public void areAnagramsUsingSorting() {
        assertThat(Strings.areAnagramsUsingSorting("algorithm", "logarithm"), is(true));
        assertThat(Strings.areAnagramsUsingSorting("algorithm", "Logarithm"), is(false));
        assertThat(Strings.areAnagramsUsingSorting("algorithm", "exponential"), is(false));
        assertThat(Strings.areAnagramsUsingSorting("algorithm", "abcdefghi"), is(false));
        assertThat(Strings.areAnagramsUsingSorting("algorithm", null), is(false));
        assertThat(Strings.areAnagramsUsingSorting(null, "algorithm"), is(false));
    }

    @Test
    public void areAnagramsUsingHashMap() {
        assertThat(Strings.areAnagramsUsingHashMap("algorithm", "logarithm"), is(true));
        assertThat(Strings.areAnagramsUsingHashMap("algorithm", "Logarithm"), is(false));
        assertThat(Strings.areAnagramsUsingHashMap("algorithm", "exponential"), is(false));
        assertThat(Strings.areAnagramsUsingHashMap("algorithm", "abcdefghi"), is(false));
        assertThat(Strings.areAnagramsUsingHashMap("algorithm", null), is(false));
        assertThat(Strings.areAnagramsUsingHashMap(null, "algorithm"), is(false));
    }

	@Test
	public void longestPalindromeBruteForce() {
		assertThat(Strings.longestPalindromeBruteForce(null), is(nullValue()));
		assertThat(Strings.longestPalindromeBruteForce(""), is(""));
		assertThat(Strings.longestPalindromeBruteForce("a"), is("a"));
		assertThat(Strings.longestPalindromeBruteForce("abcde"), is("a"));
		assertThat(Strings.longestPalindromeBruteForce("cabbad"), is("abba"));
		assertThat(Strings.longestPalindromeBruteForce("cbababd"), is("babab"));
		assertThat(Strings.longestPalindromeBruteForce("ebabcacd"), is("bab"));
		assertThat(Strings.longestPalindromeBruteForce("abcdedcba"), is("abcdedcba"));
		assertThat(Strings.longestPalindromeBruteForce("abcdeedcba"), is("abcdeedcba"));
	}

	@Test
	public void longestPalindromeExpandAroundCenter() {
		assertThat(Strings.longestPalindromeExpandAroundCenter(null), is(nullValue()));
		assertThat(Strings.longestPalindromeExpandAroundCenter(""), is(""));
		assertThat(Strings.longestPalindromeExpandAroundCenter("a"), is("a"));
		assertThat(Strings.longestPalindromeExpandAroundCenter("abcde"), is("a"));
		assertThat(Strings.longestPalindromeExpandAroundCenter("cabbad"), is("abba"));
		assertThat(Strings.longestPalindromeExpandAroundCenter("cbababd"), is("babab"));
		assertThat(Strings.longestPalindromeExpandAroundCenter("ebabcacd"), is("bab"));
		assertThat(Strings.longestPalindromeExpandAroundCenter("abcdedcba"), is("abcdedcba"));
		assertThat(Strings.longestPalindromeExpandAroundCenter("abcdeedcba"), is("abcdeedcba"));
	}

	@Test
	public void reverseVowels() {
		assertThat(Strings.reverseVowels(null), is(nullValue()));
		assertThat(Strings.reverseVowels(""), is(""));
		assertThat(Strings.reverseVowels("bcd"), is("bcd"));
		assertThat(Strings.reverseVowels("aei"), is("iea"));
		assertThat(Strings.reverseVowels("abeiae"), is("ebaiea"));
		assertThat(Strings.reverseVowels("abc"), is("abc"));
	}
}
