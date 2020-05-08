package ru.strict.validate;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import ru.strict.exceptions.ValidateException;

import java.util.Collections;

@RunWith(JUnit4.class)
public class ValidatorTest {

    @Test(expected = ValidateException.class)
    public void testOr1() {
        Object value1 = "";
        Object value2 = null;

        Validator.isNull(value1, "value1")
                .isNull(value2, "value2")
                .onThrow();
    }

    @Test(expected = ValidateException.class)
    public void testOr2() {
        Object value1 = null;
        Object value2 = "";

        Validator.isNull(value1, "value1")
                .isNull(value2, "value2")
                .onThrow();
    }

    @Test
    public void testOr3() {
        Object value1 = "";
        Object value2 = "";

        Validator.isNull(value1, "value1")
                .isNull(value2, "value2")
                .onThrow();
    }

    @Test(expected = ValidateException.class)
    public void testOr4() {
        Object value1 = null;
        Object value2 = null;

        Validator.isNull(value1, "value1")
                .isNull(value2, "value2")
                .onThrow();
    }

    @Test
    public void testAnd1() {
        Object value1 = "";
        Object value2 = null;

        Validator.and()
                .isNull(value1, "value1")
                .isNull(value2, "value2")
                .onThrow();
    }

    @Test
    public void testAnd2() {
        Object value1 = null;
        Object value2 = "";

        Validator.and()
                .isNull(value1, "value1")
                .isNull(value2, "value2")
                .onThrow();
    }

    @Test
    public void testAnd3() {
        Object value1 = "";
        Object value2 = "";

        Validator.and()
                .isNull(value1, "value1")
                .isNull(value2, "value2")
                .onThrow();
    }

    @Test(expected = ValidateException.class)
    public void testAnd4() {
        Object value1 = null;
        Object value2 = null;

        Validator.and()
                .isNull(value1, "value1")
                .isNull(value2, "value2")
                .onThrow();
    }

    @Test(expected = ValidateException.class)
    public void testAnd5() {
        Object value1 = null;
        Object value2 = null;
        Object value3 = null;

        Validator.and()
                .isNull(value1, "value1")
                .isNull(value2, "value2")
                .isNull(value3, "value3")
                .onThrow();
    }

    @Test
    public void testAnd6() {
        Object value1 = "";
        Object value2 = null;
        Object value3 = null;

        Validator.and()
                .isNull(value1, "value1")
                .isNull(value2, "value2")
                .isNull(value3, "value3")
                .onThrow();
    }

    @Test
    public void testAnd7() {
        Object value1 = null;
        Object value2 = "";
        Object value3 = null;

        Validator.and()
                .isNull(value1, "value1")
                .isNull(value2, "value2")
                .isNull(value3, "value3")
                .onThrow();
    }

    @Test
    public void testAnd8() {
        Object value1 = null;
        Object value2 = null;
        Object value3 = "";

        Validator.and()
                .isNull(value1, "value1")
                .isNull(value2, "value2")
                .isNull(value3, "value3")
                .onThrow();
    }

    @Test
    public void test1() {
        Object value1 = null;
        Object value2 = null;
        Object value3 = null;

        String expectedReason = "reason1";
        String expectedDetails = "details1";

        try {
            Validator.isNull(value1, "value1")
                    .reason(expectedReason)
                    .details(expectedDetails)
                    .isNull(value2, "value2")
                    .reason("reason2")
                    .details("details2")
                    .isNull(value3, "value3")
                    .details("details3")
                    .onThrow();
        } catch (ValidateException ex) {
            Assert.assertEquals(Collections.singletonList(expectedReason), ex.getReasons());
            Assert.assertEquals(expectedDetails, ex.getDetails());
        }
    }

    @Test
    public void test2() {
        Object value1 = null;
        Object value2 = null;
        Object value3 = null;

        String expectedDetails = "details3";

        try {
            Validator.and()
                    .isNull(value1, "value1")
                    .details(expectedDetails)
                    .isNull(value2, "value2")
                    .details("details2")
                    .isNull(value3, "value3")
                    .details("details3")
                    .onThrow();
        } catch (ValidateException ex) {
            Assert.assertEquals(expectedDetails, ex.getDetails());
        }
    }
}
