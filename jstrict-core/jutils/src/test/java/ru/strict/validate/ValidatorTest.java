package ru.strict.validate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import ru.strict.exceptions.ValidateException;

import java.util.ArrayList;
import java.util.List;

@RunWith(JUnit4.class)
public class ValidatorTest {

    @Test(expected = ValidateException.class)
    public void test001_isNull() {
        Object value = null;

        Validator.isNull(value, "value");
    }

    @Test
    public void test002_isNull() {
        Object value = "";

        Validator.isNull(value, "value");
    }

    @Test(expected = ValidateException.class)
    public void test003_isEmptyOrNull() {
        String value = null;

        Validator.isEmptyOrNull(value, "value");
    }

    @Test(expected = ValidateException.class)
    public void test004_isEmptyOrNull() {
        String value = "";

        Validator.isEmptyOrNull(value, "value");
    }

    @Test
    public void test005_isEmptyOrNull() {
        String value = "123";

        Validator.isEmptyOrNull(value, "value");
    }

    @Test(expected = ValidateException.class)
    public void test006_isEmptySpaceOrNull() {
        String value = null;

        Validator.isEmptySpaceOrNull(value, "value");
    }

    @Test(expected = ValidateException.class)
    public void test007_isEmptySpaceOrNull() {
        String value = "";

        Validator.isEmptySpaceOrNull(value, "value");
    }

    @Test(expected = ValidateException.class)
    public void test008_isEmptySpaceOrNull() {
        String value = "     ";

        Validator.isEmptySpaceOrNull(value, "value");
    }

    @Test
    public void test009_isEmptySpaceOrNull() {
        String value = "123";

        Validator.isEmptySpaceOrNull(value, "value");
    }

    @Test(expected = ValidateException.class)
    public void test010_isEmptyOrNullCollection() {
        List<?> collection = null;

        Validator.isEmptyOrNull(collection, "value");
    }

    @Test(expected = ValidateException.class)
    public void test011_isEmptyOrNullCollection() {
        List<?> collection = new ArrayList<>(1);

        Validator.isEmptyOrNull(collection, "value");
    }

    @Test
    public void test012_isEmptyOrNullCollection() {
        List<Object> collection = new ArrayList<>(1);
        collection.add("123");

        Validator.isEmptyOrNull(collection, "value");
    }

    @Test(expected = ValidateException.class)
    public void test013_isEmptyOrNullArray() {
        Object[] array = null;

        Validator.isEmptyOrNull(array, "value");
    }

    @Test(expected = ValidateException.class)
    public void test014_isEmptyOrNullArray() {
        Object[] array = new Object[1];

        Validator.isEmptyOrNull(array, "value");
    }

    @Test
    public void test015_isEmptyOrNullArray() {
        Object[] array = new Object[1];
        array [0] = "123";

        Validator.isEmptyOrNull(array, "value");
    }
}
