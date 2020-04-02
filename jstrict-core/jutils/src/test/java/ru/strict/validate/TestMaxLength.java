package ru.strict.validate;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class TestMaxLength {

    private String value;
    private int maxLength;
    private boolean expectedResult;

    public TestMaxLength(String value, int maxLength, boolean expectedResult) {
        this.value = value;
        this.maxLength = maxLength;
        this.expectedResult = expectedResult;
    }

    @Parameterized.Parameters
    public static Collection setUp(){
        return Arrays.asList(new Object[][]{
                {"123456", 6, true},
                {"123456", 7, true},
                {"", 0, true},
                {" ", 1, true},
                {"123456", 2, false},
                {null, 0, false}
        });
    }

    @Test
    public void test(){
        Assert.assertEquals(ValidateBaseValue.isMaxLength(value, maxLength), expectedResult);
    }
}
