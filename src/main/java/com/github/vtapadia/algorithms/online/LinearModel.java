package com.github.vtapadia.algorithms.online;

/**
 * Linear Model interface
 */
public interface LinearModel {
    /**
     * Records an observation
     * @param result - success of failure
     * @param params - params value, possible only 0 and 1
     */
    void observe(boolean result, int... params);
    
    /**
     * Returns the expected value
     * @param params - based on parameters provided, values 0 and 1 only
     * @return expected value for success
     */
    double expected(int... params);
}
