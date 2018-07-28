package com.github.vtapadia.algorithms.online.core;

import java.util.concurrent.atomic.AtomicLong;

/**
 * N-dimensional array
 * Currently type is set to AtomicLong.
 *
 * //TODO, to support generic class in the future
 */
public class NDArray {
    private AtomicLong[] array;
    private int dimension;
    
    public NDArray(int dimensions) {
        dimension = dimensions;
        int totalSize = (int) Math.pow(2, dimensions);
        array = new AtomicLong[totalSize];
        for (int i=0; i< totalSize; i++) {
            array[i] = new AtomicLong(0L);
        }
    }
    
    public AtomicLong get(int... indexes) {
        return array[index(indexes)];
    }
    
    public long count(int param, int value) {
        int p = (int) Math.pow(2, dimension-param-1);
        long retValue = 0L;
        for (int i = 0; i < array.length; i++) {
            if ((value == 1 && (p & i) > 0) || (value == 0 && (p & ~i) > 0)) {
                retValue += array[i].get();
            }
        }
        return retValue;
    }
    
    public long count() {
        long value = 0;
        for (AtomicLong a : array) {
            value += a.get();
        }
        return value;
    }
    
    
    int index(int... indexes) {
        int i = 0;
        for (int in: indexes) {
            i = i << 1;
            i = i | in;
        }
        return i;
    }
    
}
