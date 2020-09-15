package ru.strict.validate;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class IsEmptyOrNullTest {

    private String value;
    private boolean expectedResult;

    public IsEmptyOrNullTest(String value, boolean expectedResult) {
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
        Assert.assertEquals(CommonValidate.isEmptyOrNull(value), expectedResult);
    }
}
