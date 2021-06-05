package ru.strict.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class CommonUtilTest {

    @Test
    public void testSumWithoutVat() {
        Assertions.assertEquals(BigDecimal.valueOf(100), CommonUtil.getSumWithoutVat(BigDecimal.valueOf(120), 20, 0));
        Assertions.assertEquals(BigDecimal.valueOf(100.42), CommonUtil.getSumWithoutVat(BigDecimal.valueOf(120.5d), 20));
    }

    @Test
    public void testVatFromSum() {
        Assertions.assertEquals(BigDecimal.valueOf(20), CommonUtil.getVatFromSum(BigDecimal.valueOf(120), 20, 0));
        Assertions.assertEquals(BigDecimal.valueOf(20.09), CommonUtil.getVatFromSum(BigDecimal.valueOf(120.5), 20, 2));
    }
}