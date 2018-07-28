package com.github.vtapadia.algorithms.online.linear;

import org.junit.Assert;
import org.junit.Test;

public class SGDClassifierTest {
    @Test
    public void test() {
        SGDClassifier sgdClassifier = new SGDClassifier(2);
        sgdClassifier.observe(false, 0, 0);
        sgdClassifier.observe(false, 0, 0);
        sgdClassifier.observe(false, 1, 1);
        sgdClassifier.observe(true, 0, 0);
        Assert.assertEquals(0.36, sgdClassifier.expected(0,0), 0.01);
    }
}
