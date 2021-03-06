package ru.strict.validate;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class RangeLengthTest {

    private String value;
    private int minLength;
    private int maxLength;
    private boolean expectedResult;

    public RangeLengthTest(String value, int minLength, int maxLength, boolean expectedResult) {
        this.value = value;
        this.minLength = minLength;
        this.maxLength = maxLength;
        this.expectedResult = expectedResult;
    }

    @Parameterized.Parameters
    public static Collection setUp(){
        return Arrays.asList(new Object[][]{
                {"123456", 6, 6, true},
                {"", 0, 1, true},
                {" ", 0, 2, true},
                {"123456", 0, 5, false},
                {null, 0, 2, false}
        });
    }

    @Test
    public void test(){
        Assert.assertEquals(CommonValidate.isRangeLength(value, minLength, maxLength), expectedResult);
    }
}
