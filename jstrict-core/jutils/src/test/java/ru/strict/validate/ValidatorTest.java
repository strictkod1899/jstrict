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
    public void testOr001() {
        Object value1 = "";
        Object value2 = null;

        Validator.isNull(value1, "value1")
                .isNull(value2, "value2")
                .onThrow();
    }

    @Test(expected = ValidateException.class)
    public void testOr002() {
        Object value1 = null;
        Object value2 = "";

        Validator.isNull(value1, "value1")
                .isNull(value2, "value2")
                .onThrow();
    }

    @Test
    public void testOr003() {
        Object value1 = "";
        Object value2 = "";

        Validator.isNull(value1, "value1")
                .isNull(value2, "value2")
                .onThrow();
    }

    @Test(expected = ValidateException.class)
    public void testOr004() {
        Object value1 = null;
        Object value2 = null;

        Validator.isNull(value1, "value1")
                .isNull(value2, "value2")
                .onThrow();
    }

    @Test
    public void testAnd001() {
        Object value1 = "";
        Object value2 = null;

        Validator.isNull(value1, "value1")
                .and()
                .isNull(value2, "value2")
                .onThrow();
    }

    @Test
    public void testAnd002() {
        Object value1 = null;
        Object value2 = "";

        Validator.isNull(value1, "value1")
                .and()
                .isNull(value2, "value2")
                .onThrow();
    }

    @Test
    public void testAnd003() {
        Object value1 = "";
        Object value2 = "";

        Validator.isNull(value1, "value1")
                .and()
                .isNull(value2, "value2")
                .onThrow();
    }

    @Test(expected = ValidateException.class)
    public void testAnd004() {
        Object value1 = null;
        Object value2 = null;

        Validator.isNull(value1, "value1")
                .and()
                .isNull(value2, "value2")
                .onThrow();
    }

    @Test(expected = ValidateException.class)
    public void testAnd005() {
        Object value1 = null;
        Object value2 = null;
        Object value3 = null;

        Validator.isNull(value1, "value1")
                .and()
                .isNull(value2, "value2")
                .and()
                .isNull(value3, "value3")
                .onThrow();
    }

    @Test
    public void testAnd006() {
        Object value1 = "";
        Object value2 = null;
        Object value3 = null;

        Validator.isNull(value1, "value1")
                .and()
                .isNull(value2, "value2")
                .and()
                .isNull(value3, "value3")
                .onThrow();
    }

    @Test
    public void testAnd007() {
        Object value1 = null;
        Object value2 = "";
        Object value3 = null;

        Validator.isNull(value1, "value1")
                .and()
                .isNull(value2, "value2")
                .and()
                .isNull(value3, "value3")
                .and()
                .onThrow();
    }

    @Test
    public void testAnd008() {
        Object value1 = null;
        Object value2 = null;
        Object value3 = "";

        Validator.isNull(value1, "value1")
                .and()
                .isNull(value2, "value2")
                .and()
                .isNull(value3, "value3")
                .onThrow();
    }

    @Test
    public void test001() {
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
            Assert.assertEquals(expectedReason, ex.getReason());
            Assert.assertEquals(expectedDetails, ex.getDetails());
        }
    }

    @Test
    public void test002() {
        Object value1 = null;
        Object value2 = null;
        Object value3 = null;

        String expectedDetails = "details3";

        try {
            Validator.isNull(value1, "value1")
                    .details(expectedDetails)
                    .and()
                    .isNull(value2, "value2")
                    .details("details2")
                    .and()
                    .isNull(value3, "value3")
                    .details("details3")
                    .and()
                    .onThrow();
        } catch (ValidateException ex) {
            Assert.assertEquals(expectedDetails, ex.getDetails());
        }
    }

    @Test
    public void test003() {
        Object value1 = "";
        Object value2 = "";
        Object value3 = null;

        Validator.isNull(value1, "value1")
                .isNull(value2, "value2")
                .and()
                .isNull(value3, "value3")
                .and()
                .onThrow();
    }

    @Test(expected = ValidateException.class)
    public void test004() {
        Object value1 = "";
        Object value2 = null;
        Object value3 = null;

        Validator.isNull(value1, "value1")
                .isNull(value2, "value2")
                .and()
                .isNull(value3, "value3")
                .and()
                .onThrow();
    }

    @Test(expected = ValidateException.class)
    public void test005() {
        Object value1 = null;
        Object value2 = "";
        Object value3 = null;

        Validator.isNull(value1, "value1")
                .isNull(value2, "value2")
                .and()
                .isNull(value3, "value3")
                .and()
                .onThrow();
    }
}
