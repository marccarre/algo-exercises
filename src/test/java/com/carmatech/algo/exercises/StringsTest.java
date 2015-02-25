package com.carmatech.algo.exercises;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

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
		assertThat(evenString, equalTo(new char[] { 'a', 'v', 'a', 'j', '\0' }));
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
}
