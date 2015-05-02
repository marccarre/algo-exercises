package com.carmatech.algo;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PrimitivesTest {
    @Test
    public void byteOperations() {
        byte b = 0;
        assertEquals(++b,   1);
        assertEquals(b + 1, 2);
        assertEquals(b - 1, 0);
        assertEquals(b++,   1);
        assertEquals(b,     2);
        assertEquals(b % 2, 0);
    }
}
