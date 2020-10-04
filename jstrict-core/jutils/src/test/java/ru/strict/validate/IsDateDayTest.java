package ru.strict.validate;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class IsDateDayTest {

    private String value;
    private char splitSymbol;
    private boolean expectedResult;

    public IsDateDayTest(String value, char splitSymbol, boolean expectedResult) {
        this.value = value;
        this.splitSymbol = splitSymbol;
        this.expectedResult = expectedResult;
    }

    @Parameterized.Parameters
    public static Collection setUp(){
        return Arrays.asList(new Object[][]{
                {"01-01-2000", '-', true},
                {"31/12/0000", '/', true},
                {"-01-01-0001", '-', false},
                {"01-13-2000", '-', false},
                {"32-12-2000", '-', false},
                {"", '-', false},
                {null, '-', false}
        });
    }

    @Test
    public void test(){
        Assert.assertEquals(CommonValidate.isDateStartDay(value, splitSymbol), expectedResult);
    }
}
