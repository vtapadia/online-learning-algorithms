package com.github.vtapadia.algorithms.online.linear;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.atomic.AtomicLong;

import com.github.vtapadia.algorithms.online.LinearModel;

public class BayesianUpdating implements LinearModel {
    private AtomicLong positive, negative;
    
    /**
     * Bayesian Updating with Beta Distribution as prior.
     *
     * @param alpha - alpha value for the Beta(α,β) distribution
     * @param beta - beta value for the Beta(α,β) distribution
     */
    public BayesianUpdating(int alpha, int beta) {
        this.positive = new AtomicLong((long) alpha);
        this.negative = new AtomicLong((long) beta);
    }
    
    @Override
    public void observe(boolean result, int... params) {
        if (result) {
            positive.incrementAndGet();
        } else {
            negative.incrementAndGet();
        }
    }
    
    @Override
    public double expected(int... params) {
        long p = positive.get();
        long n = negative.get();
        float result = (float) p / (p + n);
        return new BigDecimal(result).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
}
