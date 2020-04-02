package ru.strict.i18n;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.math.BigDecimal;
import java.math.BigInteger;

@RunWith(JUnit4.class)
public class I18NMoneyFormatterTest {

    private static final MoneyFormatter FORMATTER = new MoneyFormatter();

    @Test
    public void testFormat1() {
        Assert.assertEquals("двадцать три тысячи пятьсот двадцать один рубль",
                FORMATTER.formatMoneyToWords(BigInteger.valueOf(23_521), Currency.RUB));
    }

    @Test
    public void testFormat2() {
        Assert.assertEquals("двадцать три тысячи пятьсот двадцать рублей",
                FORMATTER.formatMoneyToWords(BigInteger.valueOf(23_520), Currency.RUB));
    }

    @Test
    public void testFormat3() {
        Assert.assertEquals("двадцать тысяч пятьсот двадцать три рубля",
                FORMATTER.formatMoneyToWords(BigInteger.valueOf(20_523), Currency.RUB));
    }

    @Test
    public void testFormat4() {
        Assert.assertEquals("сто двадцать три рубля 11 копеек",
                FORMATTER.formatMoneyToWords(BigDecimal.valueOf(123.11), Currency.RUB));
    }

    @Test
    public void testFormat5() {
        Assert.assertEquals("сорок семь рублей 76 копеек",
                FORMATTER.formatMoneyToWords(BigDecimal.valueOf(47.76), Currency.RUB));
    }

    @Test
    public void testFormat6() {
        Assert.assertEquals("сто двадцать три рубля 3 копейки",
                FORMATTER.formatMoneyToWords(BigDecimal.valueOf(123.3), Currency.RUB));
    }

    @Test
    public void testFormat7() {
        Assert.assertEquals("ноль рублей",
                FORMATTER.formatMoneyToWords(BigDecimal.valueOf(0), Currency.RUB));
    }

    @Test
    public void testGetMoneyUnit1() {
        Assert.assertEquals("копейка",
                FORMATTER.getMoneyUnit(BigInteger.valueOf(21), Currency.RUB, UnitType.FRACTIONAL));
    }

    @Test
    public void testGetMoneyUnit2() {
        Assert.assertEquals("копеек",
                FORMATTER.getMoneyUnit(BigInteger.valueOf(20), Currency.RUB, UnitType.FRACTIONAL));
    }

    @Test
    public void testGetMoneyUnit3() {
        Assert.assertEquals("копейки",
                FORMATTER.getMoneyUnit(BigInteger.valueOf(3), Currency.RUB, UnitType.FRACTIONAL));
    }

    @Test
    public void testGetMoneyUnit4() {
        Assert.assertEquals("копеек",
                FORMATTER.getMoneyUnit(BigInteger.valueOf(11), Currency.RUB, UnitType.FRACTIONAL));
    }

    @Test
    public void testGetMoneyUnit5() {
        Assert.assertEquals("копеек",
                FORMATTER.getMoneyUnit(BigInteger.valueOf(0), Currency.RUB, UnitType.FRACTIONAL));
    }

    @Test
    public void testGetMoneyUnit6() {
        Assert.assertEquals("копейки",
                FORMATTER.getMoneyUnit(BigInteger.valueOf(3), Currency.RUB, UnitType.FRACTIONAL));
    }
}
