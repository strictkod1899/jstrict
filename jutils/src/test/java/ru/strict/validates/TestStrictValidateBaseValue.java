package ru.strict.validates;

import org.junit.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Collection;
import java.util.Arrays;

@RunWith(Parameterized.class)
public class TestStrictValidateBaseValue {

    private String trueText;
    private String trueNumberInt;
    private String trueNumberDouble;
    private String falseText;
    private String falseNumberInt;
    private String falseNumberDouble;

    public TestStrictValidateBaseValue(String trueText, String trueNumberInt, String trueNumberDouble,
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
                {"Hello World", "22", "22.8", " Hello World", "2s2", "22,8"},
                {",", "22", " 22.8 ", null, " 22 ", "22.5.8"}
        });
    }

    @Test
    public void testTrueIsValidateString(){
        Assert.assertTrue(StrictValidateBaseValue.isValidateString(trueText));
    }

    @Test
    public void testTrueIsValidateInteger(){
        Assert.assertTrue(StrictValidateBaseValue.isValidateInteger(trueNumberInt));
    }

    @Test
    public void testTrueIsValidateDouble(){
        Assert.assertTrue(StrictValidateBaseValue.isValidateDouble(trueNumberDouble));
    }

    @Test
    public void testFalseIsValidateString(){
        Assert.assertFalse(StrictValidateBaseValue.isValidateString(falseText));
    }

    @Test
    public void testFalseIsValidateInteger(){
        Assert.assertFalse(StrictValidateBaseValue.isValidateInteger(falseNumberInt));
    }

    @Test()
    public void testFalseIsValidateDouble(){
        Assert.assertFalse(StrictValidateBaseValue.isValidateDouble(falseNumberDouble));
    }
}
