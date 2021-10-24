package ru.strict.validate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.strict.exception.ValidateException;

import java.util.ArrayList;
import java.util.List;

public class ValidatorTest {

    @Test
    public void testIsNull_null_ex() {
        Object value = null;

        Assertions.assertThrows(ValidateException.class, () -> Validator.isNull(value, "value"));
    }

    @Test
    public void testIsNull_empty_success() {
        Object value = "";

        Validator.isNull(value, "value");
    }

    @Test
    public void testIsEmptyOrNull_null_ex() {
        String value = null;

        Assertions.assertThrows(ValidateException.class, () -> Validator.isEmptyOrNull(value, "value"));
    }

    @Test
    public void testIsEmptyOrNull_empty_ex() {
        String value = "";

        Assertions.assertThrows(ValidateException.class, () -> Validator.isEmptyOrNull(value, "value"));
    }

    @Test
    public void testIsEmptyOrNull_notEmpty_success() {
        String value = "123";

        Validator.isEmptyOrNull(value, "value");
    }

    @Test
    public void testIsEmptySpaceOrNull_null_ex() {
        String value = null;

        Assertions.assertThrows(ValidateException.class, () -> Validator.isEmptySpaceOrNull(value, "value"));
    }

    @Test
    public void testIsEmptySpaceOrNull_empty_success() {
        String value = "";

        Assertions.assertThrows(ValidateException.class, () -> Validator.isEmptySpaceOrNull(value, "value"));
    }

    @Test
    public void testIsEmptySpaceOrNull_space_ex() {
        String value = "     ";

        Assertions.assertThrows(ValidateException.class, () -> Validator.isEmptySpaceOrNull(value, "value"));
    }

    @Test
    public void testIsEmptySpaceOrNull_notEmpty_success() {
        String value = "123";

        Validator.isEmptySpaceOrNull(value, "value");
    }

    @Test
    public void testIsEmptyOrNullCollection_null_ex() {
        List<?> collection = null;

        Assertions.assertThrows(ValidateException.class, () -> Validator.isEmptyOrNull(collection, "value"));
    }

    @Test
    public void testIsEmptyOrNullCollection_empty_ex() {
        List<?> collection = new ArrayList<>(1);

        Assertions.assertThrows(ValidateException.class, () -> Validator.isEmptyOrNull(collection, "value"));
    }

    @Test
    public void testIsEmptyOrNullCollection_notEmpty_success() {
        List<Object> collection = new ArrayList<>(1);
        collection.add("123");

        Validator.isEmptyOrNull(collection, "value");
    }

    @Test
    public void testIsEmptyOrNullArray_null_ex() {
        Object[] array = null;

        Assertions.assertThrows(ValidateException.class, () -> Validator.isEmptyOrNull(array, "value"));
    }

    @Test
    public void testIsEmptyOrNullArray_empty_ex() {
        Object[] array = new Object[1];

        Assertions.assertThrows(ValidateException.class, () -> Validator.isEmptyOrNull(array, "value"));
    }

    @Test
    public void testIsEmptyOrNullArray_notEmpty_success() {
        Object[] array = new Object[1];
        array [0] = "123";

        Validator.isEmptyOrNull(array, "value");
    }

    @Test
    public void testByDetails_common() {
        String details = "Details message";
        try {
            Validator.byDetails(details).isNull(null, "value");
        } catch (ValidateException ex) {
            Assertions.assertEquals(details, ex.getDetails());
        }
    }

    @Test
    public void testByDetails_withArgs() {
        String details = "Details %s";
        Object[] args = new Object[] {
                "message"
        };
        String expectedDetails = String.format(details, args);

        try {
            Validator.byDetails(details, args).isNull(null, "value");
        } catch (ValidateException ex) {
            Assertions.assertEquals(expectedDetails, ex.getDetails());
        }
    }
}
