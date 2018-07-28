package com.github.vtapadia.algorithms.online.linear;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.logging.Logger;

import com.github.vtapadia.algorithms.online.LinearModel;

public class SGDClassifier implements LinearModel {
    
    private static Logger log = Logger.getLogger(SGDClassifier.class.getName());
    
    private double[][] coefficients;
    private LearningRate learningRate = LearningRate.INV_SCALING;
    private double learningRateMinimum;
    private long count = 0L;
    
    /**
     * params are having 2 possible values for now
     */
    public SGDClassifier(int params) {
        this(params, LearningRate.CONSTANT_MINIMUM);
    }
    
    public SGDClassifier(int params, double minimumLearningRate) {
        coefficients = new double[params][2];
        learningRateMinimum = minimumLearningRate;
    }
    
    @Override
    public void observe(boolean result, int... params) {
        count++;
        int yt = (result) ? 1 : 0;
        double pt = expected(params);
        double nt = getLearningRate();
        log.finer("Values nt " + nt + " pt " + pt + " yt "+ yt);
        for (int i=0; i<params.length; i++) {
            coefficients[i][params[i]] = coefficients[i][params[i]] - (nt * (pt - yt));
        }
    }
    
    @Override
    public double expected(int... params) {
        float sumWeights = 0f;
        for (int i=0;i<params.length; i++) {
            //Get weights of only ones where param is either 0 or 1
            double wt = coefficients[i][params[i]];
            sumWeights += wt;
        }
        double result = Math.pow(1 + Math.exp(-1 * sumWeights), -1);
        return new BigDecimal(result).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
    
    private double getLearningRate() {
        double value = 1;
        switch (learningRate) {
            case CONSTANT:
                value = learningRateMinimum;
                break;
            case INV_SCALING:
                value = Double.max(learningRateMinimum, Math.pow(Math.sqrt(count), -1));
                break;
        }
        return value;
    }
    
    public enum LearningRate {
        CONSTANT,
        INV_SCALING;
        
        public static final double CONSTANT_MINIMUM = 0.01;
    }
}
