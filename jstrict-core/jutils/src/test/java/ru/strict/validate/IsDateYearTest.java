package ru.strict.validate;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class IsDateYearTest {

    private String value;
    private char splitSymbol;
    private boolean expectedResult;

    public IsDateYearTest(String value, char splitSymbol, boolean expectedResult) {
        this.value = value;
        this.splitSymbol = splitSymbol;
        this.expectedResult = expectedResult;
    }

    @Parameterized.Parameters
    public static Collection setUp(){
        return Arrays.asList(new Object[][]{
                {"2000-01-01", '-', true},
                {"0000/12/31", '/', true},
                {"-0001-01-01", '-', false},
                {"2000-13-01", '-', false},
                {"2000-12-32", '-', false},
                {"", '-', false},
                {null, '-', false}
        });
    }

    @Test
    public void test(){
        Assert.assertEquals(BaseValidate.isDateStartYear(value, splitSymbol), expectedResult);
    }
}
