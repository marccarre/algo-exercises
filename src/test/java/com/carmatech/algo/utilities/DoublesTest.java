package com.carmatech.algo.utilities;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class DoublesTest {
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void invalidAddCases() {
        final double d = Double.MAX_VALUE;
        System.out.println(d);
        System.out.println(d + 1);
        assertThat(d + 1, is(d)); // Absorption error.

        final double x = d + d;
        System.out.println(x);
        assertThat(Double.isInfinite(x), is(true));
        assertThat(Double.isNaN(x), is(false));
    }

    @Test
    public void checkedAddShouldRaiseArithmeticExceptionInCaseOfAbsorptionErrorOverflow() {
        exception.expect(ArithmeticException.class);
        exception.expectMessage(equalTo("Failed to add 1.7976931348623157E308 and 1.0. Absorption error: 1.7976931348623157E308."));
        Doubles.checkedAdd(Double.MAX_VALUE, 1);
    }

    @Test
    public void checkedAddShouldRaiseArithmeticExceptionInCaseOfAbsorptionError() {
        exception.expect(ArithmeticException.class);
        exception.expectMessage(equalTo("Failed to add 1.7976931348623157E308 and 4.9E-324. Absorption error: 1.7976931348623157E308."));
        Doubles.checkedAdd(Double.MAX_VALUE, Double.MIN_VALUE);
    }

    @Test
    public void checkedAddShouldRaiseArithmeticExceptionInCaseOfOverflow() {
        exception.expect(ArithmeticException.class);
        exception.expectMessage(equalTo("Failed to add 1.7976931348623157E308 and 1.7976931348623157E308. Overflow: Infinity."));
        Doubles.checkedAdd(Double.MAX_VALUE, Double.MAX_VALUE);
    }
}
