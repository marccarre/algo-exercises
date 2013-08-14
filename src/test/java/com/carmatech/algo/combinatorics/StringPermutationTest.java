package com.carmatech.algo.combinatorics;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class StringPermutationTest {
	@Test
	public void permuteString() {
		List<String> permutations = StringPermutation.permute("abc");
		List<String> expected = Arrays.asList("abc", "acb", "bac", "bca", "cab", "cba");
		print(permutations);

		assertThat(permutations, equalTo(expected));
	}

	private void print(final List<String> strings) {
		for (final String string : strings)
			System.out.println(string);
	}
}
