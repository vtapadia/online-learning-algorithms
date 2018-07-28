package com.github.vtapadia.algorithms.online.core;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NDArrayTest {
    @Test
    public void test() {
        NDArray nda = new NDArray(4);
        assertEquals(0, nda.index(0,0,0,0));
        assertEquals(1, nda.index(0,0,0,1));
        assertEquals(3, nda.index(0,0,1,1));
        assertEquals(7, nda.index(0,1,1,1));
        assertEquals(15, nda.index(1,1,1,1));
        assertEquals(8, nda.index(1,0,0,0));
        assertEquals(10, nda.index(1,0,1,0));
    }
    
    @Test
    public void testCount() {
        NDArray array = new NDArray(2);
        array.get(0,0).set(42);
        array.get(0,1).set(14);
        array.get(1,0).set(12);
        array.get(1,1).set(4);
        assertEquals(56, array.count(0, 0));
        assertEquals(16, array.count(0, 1));
        assertEquals(54, array.count(1, 0));
        assertEquals(18, array.count(1, 1));
    }
}
