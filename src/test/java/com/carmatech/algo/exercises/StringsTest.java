package com.carmatech.algo.exercises;

import static org.hamcrest.MatcherAssert.assertThat;
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
		assertThat(Strings.containsDuplicateBestButAsciiOnly("java"), is(true));
		assertThat(Strings.containsDuplicateBestButAsciiOnly("single"), is(false));
	}
}
