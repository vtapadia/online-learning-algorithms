package com.github.vtapadia.algorithms.online.linear;

import org.junit.Assert;
import org.junit.Test;

public class NaiveBayesTest {
    
    @Test
    public void test() {
        NaiveBayes nb = new NaiveBayes(2);
        nb.clicks.get(0,0).set(100);
        nb.clicks.get(0,1).set(200);
        nb.clicks.get(1,0).set(300);
        nb.clicks.get(1,1).set(400);
        nb.noClicks.get(0,0).set(20);
        nb.noClicks.get(0,1).set(30);
        nb.noClicks.get(1,0).set(40);
        nb.noClicks.get(1,1).set(50);
        Assert.assertEquals(0.89, nb.expected(1, 1), 0.01);
    }
}
