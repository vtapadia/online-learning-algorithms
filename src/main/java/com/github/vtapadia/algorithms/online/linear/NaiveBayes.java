package com.github.vtapadia.algorithms.online.linear;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.github.vtapadia.algorithms.online.LinearModel;
import com.github.vtapadia.algorithms.online.core.NDArray;

public class NaiveBayes implements LinearModel {
    
    NDArray clicks;
    NDArray noClicks;
    
    public NaiveBayes(int params) {
        clicks = new NDArray(params);
        noClicks = new NDArray(params);
    }
    
    @Override
    public void observe(boolean result, int... params) {
        if (result) {
            clicks.get(params).incrementAndGet();
        } else {
            noClicks.get(params).incrementAndGet();
        }
    }
    
    @Override
    public double expected(int... params) {
        long totalClicks = clicks.count();
        long totalNoClicks = noClicks.count();
        long totalViews = totalClicks + totalNoClicks;
        float cParamProb = (float) totalClicks / totalViews;
        float ncParamProb = (float) totalNoClicks / totalViews;
        
        //Prob for params
        for (int i=0; i< params.length; i++) {
            cParamProb *= (float) clicks.count(i, params[i]) / totalClicks;
            ncParamProb *= (float) noClicks.count(i, params[i]) / totalNoClicks;
        }
        
        float result = cParamProb / (cParamProb + ncParamProb);
        
        return new BigDecimal(result).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
    
}
