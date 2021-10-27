package ru.strict.util;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CommonUtilTest {

    @Test
    void testSumWithoutVat() {
        assertEquals(BigDecimal.valueOf(100), CommonUtil.getSumWithoutVat(BigDecimal.valueOf(120), 20, 0));
        assertEquals(BigDecimal.valueOf(100.42), CommonUtil.getSumWithoutVat(BigDecimal.valueOf(120.5d), 20));
    }

    @Test
    void testVatFromSum() {
        assertEquals(BigDecimal.valueOf(20), CommonUtil.getVatFromSum(BigDecimal.valueOf(120), 20, 0));
        assertEquals(BigDecimal.valueOf(20.09), CommonUtil.getVatFromSum(BigDecimal.valueOf(120.5), 20, 2));
    }
}