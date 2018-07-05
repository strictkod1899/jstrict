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

    public TestValidateBaseValue(String trueText, String trueNumberInt, String trueNumberDouble,
                                 String trueDateYear, String trueDateDay, char splitDateSymbol, String trueTime,
                                 String falseText, String falseNumberInt, String falseNumberDouble) {
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
    }

    @Parameters
    public static Collection setUp(){
        return Arrays.asList(new Object[][]{
                {"Hello World", "22", "22.8", "1994/06/18", "06/08/1994", '/', "00:00:00", "   ", "2,2", "22,8"},
                {",", "-22", "+22.8", "1994-06-18", "06-08-1994", '-', "23:59:59", "", "+2.2", "+22,8"},
                {" A", "+22", "-22.8", "1994 06 18", "06 08 1994", ' ', "07:15:05", null, "-2.2", "-22,8"},
        });
    }

    @Test
    public void testTrueIsValidateString(){
        Assert.assertTrue(ValidateBaseValue.isNotEmptyOrNull(trueText));
    }

    @Test
    public void testTrueIsValidateInteger(){
        Assert.assertTrue(ValidateBaseValue.isValidateInteger(trueNumberInt));
    }

    @Test
    public void testTrueIsValidateDouble(){
        Assert.assertTrue(ValidateBaseValue.isValidateDouble(trueNumberDouble));
    }

    @Test
    public void testTrueIsValidateDateYear(){
        Assert.assertTrue(ValidateBaseValue.isValidateDateYear(trueDateYear, splitDateSymbol));
    }

    @Test
    public void testTrueIsValidateDateDay(){
        Assert.assertTrue(ValidateBaseValue.isValidateDateDay(trueDateDay, splitDateSymbol));
    }

    @Test
    public void testTrueIsValidateTime(){
        Assert.assertTrue(ValidateBaseValue.isValidateTime(trueTime));
    }

    @Test
    public void testFalseIsValidateString(){
        Assert.assertFalse(ValidateBaseValue.isNotEmptyOrNull(falseText));
    }

    @Test
    public void testFalseIsValidateInteger(){
        Assert.assertFalse(ValidateBaseValue.isValidateInteger(falseNumberInt));
    }

    @Test()
    public void testFalseIsValidateDouble(){
        Assert.assertFalse(ValidateBaseValue.isValidateDouble(falseNumberDouble));
    }
}
