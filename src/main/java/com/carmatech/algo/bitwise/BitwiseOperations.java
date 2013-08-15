package com.carmatech.algo.bitwise;

public final class BitwiseOperations {
	private BitwiseOperations() {
		// Pure utility class, do NOT instantiate.
	}

	public static int bitCount(int i) {
		int numOfBitsSetToOne = 0;
		while (i != 0) {
			i &= (i - 1);
			++numOfBitsSetToOne;
		}
		return numOfBitsSetToOne;
	}

	public static int bitCountSwar(int i) {
		i = i - ((i >> 1) & 0x55555555);
		i = (i & 0x33333333) + ((i >> 2) & 0x33333333);
		return (((i + (i >> 4)) & 0x0F0F0F0F) * 0x01010101) >> 24;
	}
}
