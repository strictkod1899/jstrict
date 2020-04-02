package ru.strict.validate;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class TestIsTime {

    private String value;
    private char splitSymbol;
    private boolean expectedResult;

    public TestIsTime(String value, char splitSymbol, boolean expectedResult) {
        this.value = value;
        this.splitSymbol = splitSymbol;
        this.expectedResult = expectedResult;
    }

    @Parameterized.Parameters
    public static Collection setUp(){
        return Arrays.asList(new Object[][]{
                {"00:00:00", ':', true},
                {"23-59-59", '-', true},
                {"-01:01:01", ':', false},
                {"24:00:00", ':', false},
                {"23:60:00", ':', false},
                {"23:00:60", ':', false},
                {"", '-', false},
                {null, '-', false}
        });
    }

    @Test
    public void test(){
        Assert.assertEquals(ValidateBaseValue.isTime(value, splitSymbol), expectedResult);
    }
}
