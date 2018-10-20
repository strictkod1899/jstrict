package ru.strict.validates;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class TestIsEmptyOrNull {

    private String value;
    private boolean expectedResult;

    public TestIsEmptyOrNull(String value, boolean expectedResult) {
        this.value = value;
        this.expectedResult = expectedResult;
    }

    @Parameterized.Parameters
    public static Collection setUp(){
        return Arrays.asList(new Object[][]{
                {"", true},
                {null, true},
                {" ", false},
                {"not empty", false},
        });
    }

    @Test
    public void test(){
        Assert.assertEquals(ValidateBaseValue.isEmptyOrNull(value), expectedResult);
    }
}
