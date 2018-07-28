package com.github.vtapadia.algorithms.online.linear;

import org.junit.Assert;
import org.junit.Test;

public class BayesianUpdatingTest {
    @Test
    public void test() {
        BayesianUpdating bu = new BayesianUpdating(1,1);
        bu.observe(true);
        Assert.assertEquals(0.67, bu.expected(), 0.01);
    }
}
