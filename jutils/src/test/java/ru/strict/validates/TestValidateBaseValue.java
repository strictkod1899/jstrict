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
    private String falseText;
    private String falseNumberInt;
    private String falseNumberDouble;

    public TestValidateBaseValue(String trueText, String trueNumberInt, String trueNumberDouble,
                                 String falseText, String falseNumberInt, String falseNumberDouble) {
        this.trueText = trueText;
        this.trueNumberInt = trueNumberInt;
        this.trueNumberDouble = trueNumberDouble;
        this.falseText = falseText;
        this.falseNumberInt = falseNumberInt;
        this.falseNumberDouble = falseNumberDouble;
    }

    @Parameters
    public static Collection setUp(){
        return Arrays.asList(new Object[][]{
                {"Hello World", "22", "22.8", "   ", "2s2", "22,8"},
                {",", "22", " 22.8 ", null, " 22 ", "22.5.8"},
                {" A", "22", " 22.8 ", "", " 2.2 ", "22,5"},
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
