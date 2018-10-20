package ru.strict.validates;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class TestMinLength {

    private String value;
    private int minLength;
    private boolean expectedResult;

    public TestMinLength(String value, int minLength, boolean expectedResult) {
        this.value = value;
        this.minLength = minLength;
        this.expectedResult = expectedResult;
    }

    @Parameterized.Parameters
    public static Collection setUp(){
        return Arrays.asList(new Object[][]{
                {"123456", 6, true},
                {"", 0, true},
                {" ", 1, true},
                {"123456", 7, false},
                {null, 0, false}
        });
    }

    @Test
    public void test(){
        Assert.assertEquals(ValidateBaseValue.isMinLength(value, minLength), expectedResult);
    }
}
