package ru.strict.i18n;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.BigInteger;

public class I18NMoneyFormatterTest {

    private static final MoneyFormatter FORMATTER = new MoneyFormatter();

    @Test
    public void testFormat1() {
        Assertions.assertEquals("двадцать три тысячи пятьсот двадцать один рубль",
                FORMATTER.formatMoneyToWords(BigInteger.valueOf(23_521), Currency.RUB));
    }

    @Test
    public void testFormat2() {
        Assertions.assertEquals("двадцать три тысячи пятьсот двадцать рублей",
                FORMATTER.formatMoneyToWords(BigInteger.valueOf(23_520), Currency.RUB));
    }

    @Test
    public void testFormat3() {
        Assertions.assertEquals("двадцать тысяч пятьсот двадцать три рубля",
                FORMATTER.formatMoneyToWords(BigInteger.valueOf(20_523), Currency.RUB));
    }

    @Test
    public void testFormat4() {
        Assertions.assertEquals("сто двадцать три рубля 11 копеек",
                FORMATTER.formatMoneyToWords(BigDecimal.valueOf(123.11), Currency.RUB));
    }

    @Test
    public void testFormat5() {
        Assertions.assertEquals("сорок семь рублей 76 копеек",
                FORMATTER.formatMoneyToWords(BigDecimal.valueOf(47.76), Currency.RUB));
    }

    @Test
    public void testFormat6() {
        Assertions.assertEquals("сто двадцать три рубля 3 копейки",
                FORMATTER.formatMoneyToWords(BigDecimal.valueOf(123.3), Currency.RUB));
    }

    @Test
    public void testFormat7() {
        Assertions.assertEquals("ноль рублей",
                FORMATTER.formatMoneyToWords(BigDecimal.valueOf(0), Currency.RUB));
    }

    @Test
    public void testGetMoneyUnit1() {
        Assertions.assertEquals("копейка",
                FORMATTER.getMoneyUnit(BigInteger.valueOf(21), Currency.RUB, UnitType.FRACTIONAL));
    }

    @Test
    public void testGetMoneyUnit2() {
        Assertions.assertEquals("копеек",
                FORMATTER.getMoneyUnit(BigInteger.valueOf(20), Currency.RUB, UnitType.FRACTIONAL));
    }

    @Test
    public void testGetMoneyUnit3() {
        Assertions.assertEquals("копейки",
                FORMATTER.getMoneyUnit(BigInteger.valueOf(3), Currency.RUB, UnitType.FRACTIONAL));
    }

    @Test
    public void testGetMoneyUnit4() {
        Assertions.assertEquals("копеек",
                FORMATTER.getMoneyUnit(BigInteger.valueOf(11), Currency.RUB, UnitType.FRACTIONAL));
    }

    @Test
    public void testGetMoneyUnit5() {
        Assertions.assertEquals("копеек",
                FORMATTER.getMoneyUnit(BigInteger.valueOf(0), Currency.RUB, UnitType.FRACTIONAL));
    }

    @Test
    public void testGetMoneyUnit6() {
        Assertions.assertEquals("копейки",
                FORMATTER.getMoneyUnit(BigInteger.valueOf(3), Currency.RUB, UnitType.FRACTIONAL));
    }
}
