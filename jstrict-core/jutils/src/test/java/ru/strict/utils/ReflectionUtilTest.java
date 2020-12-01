package ru.strict.utils;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class ReflectionUtilTest {

    @Test
    public void testSetField() {
        TestObject testObject = new TestObject("123");

        ReflectionUtil.setField(testObject, "field", "000");

        Assert.assertEquals("000", testObject.getField());
    }

    private static class TestObject {
        private final String field;

        public TestObject(String field) {
            this.field = field;
        }

        public String getField() {
            return field;
        }
    }
}
