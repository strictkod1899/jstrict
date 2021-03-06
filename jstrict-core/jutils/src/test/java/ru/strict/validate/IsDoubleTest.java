package ru.strict.validate;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class IsDoubleTest {

    private String value;
    private boolean expectedResult;

    public IsDoubleTest(String value, boolean expectedResult) {
        this.value = value;
        this.expectedResult = expectedResult;
    }

    @Parameterized.Parameters
    public static Collection setUp(){
        return Arrays.asList(new Object[][]{
                {"1.0", true},
                {"-1.0", true},
                {"1", true},
                {" ", false},
                {"", false},
                {null, false}
        });
    }

    @Test
    public void test(){
        Assert.assertEquals(CommonValidate.isDouble(value), expectedResult);
    }
}
