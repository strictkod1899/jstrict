package ru.strict.i18n;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

class MoneyFormatterTest {

    private static final MoneyFormatter FORMATTER = new MoneyFormatter();

    @Test
    void testFormat_case1_success() {
        assertEquals("двадцать три тысячи пятьсот двадцать один рубль",
                FORMATTER.formatMoneyToWords(BigInteger.valueOf(23_521), Currency.RUB));
    }

    @Test
    void testFormat_case2_success() {
        assertEquals("двадцать три тысячи пятьсот двадцать рублей",
                FORMATTER.formatMoneyToWords(BigInteger.valueOf(23_520), Currency.RUB));
    }

    @Test
    void testFormat_case3_success() {
        assertEquals("двадцать тысяч пятьсот двадцать три рубля",
                FORMATTER.formatMoneyToWords(BigInteger.valueOf(20_523), Currency.RUB));
    }

    @Test
    void testFormat_case4_success() {
        assertEquals("сто двадцать три рубля 11 копеек",
                FORMATTER.formatMoneyToWords(BigDecimal.valueOf(123.11), Currency.RUB));
    }

    @Test
    void testFormat_case5_success() {
        assertEquals("сорок семь рублей 76 копеек",
                FORMATTER.formatMoneyToWords(BigDecimal.valueOf(47.76), Currency.RUB));
    }

    @Test
    void testFormat_case6_success() {
        assertEquals("сто двадцать три рубля 3 копейки",
                FORMATTER.formatMoneyToWords(BigDecimal.valueOf(123.3), Currency.RUB));
    }

    @Test
    void testFormat_case7_success() {
        assertEquals("ноль рублей",
                FORMATTER.formatMoneyToWords(BigDecimal.valueOf(0), Currency.RUB));
    }

    @Test
    void testGetMoneyUnit_case1_success() {
        assertEquals("копейка",
                FORMATTER.getMoneyUnit(BigInteger.valueOf(21), Currency.RUB, UnitType.FRACTIONAL));
    }

    @Test
    void testGetMoneyUnit_case2_success() {
        assertEquals("копеек",
                FORMATTER.getMoneyUnit(BigInteger.valueOf(20), Currency.RUB, UnitType.FRACTIONAL));
    }

    @Test
    void testGetMoneyUnit_case3_success() {
        assertEquals("копейки",
                FORMATTER.getMoneyUnit(BigInteger.valueOf(3), Currency.RUB, UnitType.FRACTIONAL));
    }

    @Test
    void testGetMoneyUnit_cas4_success() {
        assertEquals("копеек",
                FORMATTER.getMoneyUnit(BigInteger.valueOf(11), Currency.RUB, UnitType.FRACTIONAL));
    }

    @Test
    void testGetMoneyUnit_case5_success() {
        assertEquals("копеек",
                FORMATTER.getMoneyUnit(BigInteger.valueOf(0), Currency.RUB, UnitType.FRACTIONAL));
    }

    @Test
    void testGetMoneyUnit_case6_success() {
        assertEquals("копейки",
                FORMATTER.getMoneyUnit(BigInteger.valueOf(3), Currency.RUB, UnitType.FRACTIONAL));
    }
}
