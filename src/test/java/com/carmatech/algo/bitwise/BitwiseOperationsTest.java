package com.carmatech.algo.bitwise;

import static com.carmatech.algo.bitwise.BitwiseOperations.add;
import static com.carmatech.algo.bitwise.BitwiseOperations.negate;
import static com.carmatech.algo.bitwise.BitwiseOperations.subtract;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

public class BitwiseOperationsTest {
	@Test
	public void leftShiftWithLowestBitAtZeroShouldShiftWithRightMostBitSetToZero() {
		long l = 0xFFFF000000000000L;

		println(l);
		println(l << 1);
		// -281474976710656, binary: 1111111111111111000000000000000000000000000000000000000000000000, hexadecimal: ffff000000000000
		// -562949953421312, binary: 1111111111111110000000000000000000000000000000000000000000000000, hexadecimal: fffe000000000000

		assertThat(l << 1, is(0xFFFE000000000000L));
	}

	@Test
	public void leftShiftWithLowestBitAtOneShouldShiftWithRightMostBitSetToZero() {
		long l = 0xFFFF000000000001L;

		println(l);
		println(l << 1);
		// -281474976710655, binary: 1111111111111111000000000000000000000000000000000000000000000001, hexadecimal: ffff000000000001
		// -562949953421310, binary: 1111111111111110000000000000000000000000000000000000000000000010, hexadecimal: fffe000000000002

		assertThat(l << 1, is(0xFFFE000000000002L));
	}

	@Test
	public void rightShiftWithHighestBitAtZeroShouldShiftWithLeftMostBitSetToZero() {
		long l = 0x7FFF000000000000L;

		println(l);
		println(l >> 1);
		// 9223090561878065152, binary: 0111111111111111000000000000000000000000000000000000000000000000, hexadecimal: 7fff000000000000
		// 4611545280939032576, binary: 0011111111111111100000000000000000000000000000000000000000000000, hexadecimal: 3fff800000000000

		assertThat(l >> 1, is(0x3FFF800000000000L));
	}

	@Test
	public void rightShiftWithHighestBitAtOneShouldShiftWithLeftMostBitSetToOne() {
		long l = 0xFFFF000000000000L;

		println(l);
		println(l >> 1);
		// -281474976710656, binary: 1111111111111111000000000000000000000000000000000000000000000000, hexadecimal: ffff000000000000
		// -140737488355328, binary: 1111111111111111100000000000000000000000000000000000000000000000, hexadecimal: ffff800000000000

		assertThat(l >> 1, is(0xFFFF800000000000L));
	}

	@Test
	public void rightUnsignedShiftWithHighestBitAtZeroShouldShiftWithLeftMostBitSetToZero() {
		long l = 0x7FFF000000000000L;

		println(l);
		println(l >>> 1);
		// 9223090561878065152, binary: 0111111111111111000000000000000000000000000000000000000000000000, hexadecimal: 7fff000000000000
		// 4611545280939032576, binary: 0011111111111111100000000000000000000000000000000000000000000000, hexadecimal: 3fff800000000000

		assertThat(l >>> 1, is(0x3FFF800000000000L));
	}

	@Test
	public void rightUnsignedShiftWithHighestBitAtOneShouldShiftWithLeftMostBitSetToZero() {
		long l = 0xFFFF000000000000L;

		println(l);
		println(l >>> 1);
		// -281474976710656, binary: 1111111111111111000000000000000000000000000000000000000000000000, hexadecimal: ffff000000000000
		// 9223231299366420480, binary: 0111111111111111100000000000000000000000000000000000000000000000, hexadecimal: 7fff800000000000

		assertThat(l >>> 1, is(0x7FFF800000000000L));
	}

	@Test
	public void bitCountTheNaiveWay() {
		int i = 0xFFFF0000;
		println(i);

		assertThat(BitwiseOperations.bitCount(i), is(16));
		assertThat(Integer.bitCount(i), is(16));
	}

    @Test
    public void bitCountSimple() {
        int i = 0xFFFF0000;
        println(i);

        assertThat(BitwiseOperations.bitCountSimple(i), is(16));
        assertThat(Integer.bitCount(i), is(16));
    }

	@Test
	public void bitCountUsingSwar() {
		int i = 0xFFFF0000;
		println(i);

		assertThat(BitwiseOperations.bitCountSwar(i), is(16));
		assertThat(Integer.bitCount(i), is(16));
	}

	@Test
	public void addIntegers() {
		int x = 47;
		int y = 53;
		println(x);
		println(y);
		println(x+y);
		println(add(x, y));
		assertThat(add(x, y), is(100));
	}

	@Test
	public void negateInteger() {
		int x = 47;
		println(x);
		println(-x);
		println(negate(x));
		assertThat(negate(x), is(-47));
	}

	@Test
	public void subtractIntegers() {
		int x = 47;
		int y = 53;
		println(x);
		println(y);
		println(x-y);
		println(subtract(x, y));
		assertThat(subtract(y, x), is(6));
	}

	private void println(final long l) {
		System.out.println(l + ", binary: " + String.format("%64s", Long.toBinaryString(l)).replace(' ', '0') + ", hexadecimal: " + Long.toHexString(l));
	}

	private void println(final int i) {
		System.out.println(i + ", binary: " + String.format("%32s", Integer.toBinaryString(i)).replace(' ', '0') + ", hexadecimal: " + Integer.toHexString(i));
	}
}
