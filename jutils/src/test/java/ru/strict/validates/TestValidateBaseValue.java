package ru.strict.validates;

import org.junit.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Collection;
import java.util.Arrays;

@RunWith(Parameterized.class)
public class TestValidateBaseValue {

    private String trueText;
    private String trueNumberInt;
    private String trueNumberDouble;
    private String trueDateYear;
    private String trueDateDay;
    private char splitDateSymbol;
    private String trueTime;

    private String falseText;
    private String falseNumberInt;
    private String falseNumberDouble;
    private String falseDateYear;
    private String falseDateDay;
    private String falseTime;

    public TestValidateBaseValue(String trueText, String trueNumberInt, String trueNumberDouble,
                                 String trueDateYear, String trueDateDay, char splitDateSymbol, String trueTime,
                                 String falseText, String falseNumberInt, String falseNumberDouble,
                                 String falseDateYear, String falseDateDay, String falseTime) {
        this.trueText = trueText;
        this.trueNumberInt = trueNumberInt;
        this.trueNumberDouble = trueNumberDouble;
        this.trueDateYear = trueDateYear;
        this.trueDateDay = trueDateDay;
        this.splitDateSymbol = splitDateSymbol;
        this.trueTime = trueTime;

        this.falseText = falseText;
        this.falseNumberInt = falseNumberInt;
        this.falseNumberDouble = falseNumberDouble;
        this.falseDateYear = falseDateYear;
        this.falseDateDay = falseDateDay;
        this.falseTime = falseTime;
    }

    @Parameters
    public static Collection setUp(){
        return Arrays.asList(new Object[][]{
                {"Hello World", "22", "22.8", "1994/06/18", "06/08/1994", '/', "00:00:00",
                        "   ", "2,2", "22,8", "18/06/1994", "1994/08/18", "24:00:00"},
                {",", "-22", "+22.8", "1994-06-18", "06-08-1994", '-', "23:59:59",
                        "", "+2.2", "+22,8", "1994-06-32", "32-08-1994", "00:61:00"},
                {" A", "+22", "-22.8", "1994 06 18", "06 08 1994", ' ', "07:15:05",
                        null, "-2.2", "-22,8", "1994 06 0", "0 08 1994", "00:00:61"},
        });
    }

    @Test
    public void testTrueIsValidateString(){
        Assert.assertTrue(ValidateBaseValue.isNotEmptyOrNull(trueText));
    }

    @Test
    public void testTrueIsValidateInteger(){
        Assert.assertTrue(ValidateBaseValue.isInteger(trueNumberInt));
    }

    @Test
    public void testTrueIsValidateDouble(){
        Assert.assertTrue(ValidateBaseValue.isDouble(trueNumberDouble));
    }

    @Test
    public void testTrueIsValidateDateYear(){
        Assert.assertTrue(ValidateBaseValue.isDateYear(trueDateYear, splitDateSymbol));
    }

    @Test
    public void testTrueIsValidateDateDay(){
        Assert.assertTrue(ValidateBaseValue.isDateDay(trueDateDay, splitDateSymbol));
    }

    @Test
    public void testTrueIsValidateTime(){
        Assert.assertTrue(ValidateBaseValue.isTime(trueTime));
    }

    @Test
    public void testFalseIsValidateString(){
        Assert.assertFalse(ValidateBaseValue.isNotEmptyOrNull(falseText));
    }

    @Test
    public void testFalseIsValidateInteger(){
        Assert.assertFalse(ValidateBaseValue.isInteger(falseNumberInt));
    }

    @Test()
    public void testFalseIsValidateDouble(){
        Assert.assertFalse(ValidateBaseValue.isDouble(falseNumberDouble));
    }

    @Test
    public void testFalseIsValidateDateYear(){
        Assert.assertFalse(ValidateBaseValue.isDateYear(falseDateYear, splitDateSymbol));
    }

    @Test
    public void testFalseIsValidateDateDay(){
        Assert.assertFalse(ValidateBaseValue.isDateDay(falseDateDay, splitDateSymbol));
    }

    @Test
    public void testFalseIsValidateTime(){
        Assert.assertFalse(ValidateBaseValue.isTime(falseTime));
    }
}
