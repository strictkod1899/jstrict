package ru.strict.utils;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.math.BigDecimal;

@RunWith(JUnit4.class)
public class CommonUtilTest {

    @Test
    public void testSumWithoutVat() {
        Assert.assertEquals(BigDecimal.valueOf(100), CommonUtil.getSumWithoutVat(BigDecimal.valueOf(120), 20, 0));
        Assert.assertEquals(BigDecimal.valueOf(100.42), CommonUtil.getSumWithoutVat(BigDecimal.valueOf(120.5d), 20));
    }

    @Test
    public void testVatFromSum() {
        Assert.assertEquals(BigDecimal.valueOf(20), CommonUtil.getVatFromSum(BigDecimal.valueOf(120), 20, 0));
        Assert.assertEquals(BigDecimal.valueOf(20.09), CommonUtil.getVatFromSum(BigDecimal.valueOf(120.5), 20, 2));
    }
}