package ru.strict.utils;

import org.junit.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class TestStrictUtilConfigLog4J {

    @Test()
    public void testInit(){
        Assert.assertEquals(1, new UtilConfigLog4j("logs/report.log").init());
    }
}
