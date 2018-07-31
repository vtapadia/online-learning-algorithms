package com.github.vtapadia.algorithms.online.linear;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicLong;

import com.github.vtapadia.algorithms.online.LinearModel;
import com.google.common.primitives.Booleans;

public class BayesianUpdatingFixedWindow implements LinearModel {
    private boolean[] recentData;
    private AtomicLong count;
    private int recentCount;
    
    /**
     * Bayesian Updating with Beta Distribution as prior.
     *
     * @param alpha - alpha value for the Beta(α,β) distribution
     * @param beta - beta value for the Beta(α,β) distribution
     * @param recentCount - total last entries to consider
     */
    public BayesianUpdatingFixedWindow(int alpha, int beta, int recentCount) {
        
        this.count = new AtomicLong(0L);
        this.recentCount = recentCount;
        this.recentData = new boolean[recentCount];
        int toIndex = (alpha * recentCount) / (alpha + beta);
        Arrays.fill(recentData, 0, toIndex, true);
    }
    
    @Override
    public void observe(boolean result, int... params) {
        int index = (int) this.count.incrementAndGet() % recentCount;
        recentData[index] = result;
    }
    
    @Override
    public double expected(int... params) {
        long count = Booleans.asList(recentData).stream().filter(b -> b).count(); //Success count
        
        float result = (float) count / (recentCount);
        return new BigDecimal(result).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

}
