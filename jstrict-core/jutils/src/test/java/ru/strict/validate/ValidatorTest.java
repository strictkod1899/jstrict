package ru.strict.validate;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorTest {

    @Test
    void testIsNull_null_ex() {
        Object value = null;

        assertThrows(ItemValidateException.class, () -> Validator.isNull(value, "value"));
    }

    @Test
    void testIsNull_empty_success() {
        Object value = "";

        Validator.isNull(value, "value");
    }

    @Test
    void testIsEmptyOrNull_null_ex() {
        String value = null;

        assertThrows(ItemValidateException.class, () -> Validator.isNullOrEmpty(value, "value"));
    }

    @Test
    void testIsEmptyOrNull_empty_ex() {
        String value = "";

        assertThrows(ItemValidateException.class, () -> Validator.isNullOrEmpty(value, "value"));
    }

    @Test
    void testIsEmptyOrNull_notEmpty_success() {
        String value = "123";

        Validator.isNullOrEmpty(value, "value");
    }

    @Test
    void testIsEmptySpaceOrNull_null_ex() {
        String value = null;

        assertThrows(ItemValidateException.class, () -> Validator.isEmptySpaceOrNull(value, "value"));
    }

    @Test
    void testIsEmptySpaceOrNull_empty_success() {
        String value = "";

        assertThrows(ItemValidateException.class, () -> Validator.isEmptySpaceOrNull(value, "value"));
    }

    @Test
    void testIsEmptySpaceOrNull_space_ex() {
        String value = "     ";

        assertThrows(ItemValidateException.class, () -> Validator.isEmptySpaceOrNull(value, "value"));
    }

    @Test
    void testIsEmptySpaceOrNull_notEmpty_success() {
        String value = "123";

        Validator.isEmptySpaceOrNull(value, "value");
    }

    @Test
    void testIsEmptyOrNullCollection_null_ex() {
        List<?> collection = null;

        assertThrows(ItemValidateException.class, () -> Validator.isNullOrEmpty(collection, "value"));
    }

    @Test
    void testIsEmptyOrNullCollection_empty_ex() {
        List<?> collection = new ArrayList<>(1);

        assertThrows(ItemValidateException.class, () -> Validator.isNullOrEmpty(collection, "value"));
    }

    @Test
    void testIsEmptyOrNullCollection_notEmpty_success() {
        List<Object> collection = new ArrayList<>(1);
        collection.add("123");

        Validator.isNullOrEmpty(collection, "value");
    }

    @Test
    void testIsEmptyOrNullArray_null_ex() {
        Object[] array = null;

        assertThrows(ItemValidateException.class, () -> Validator.isNullOrEmpty(array, "value"));
    }

    @Test
    void testIsEmptyOrNullArray_empty_ex() {
        Object[] array = new Object[1];

        assertThrows(ItemValidateException.class, () -> Validator.isNullOrEmpty(array, "value"));
    }

    @Test
    void testIsEmptyOrNullArray_notEmpty_success() {
        Object[] array = new Object[1];
        array [0] = "123";

        Validator.isNullOrEmpty(array, "value");
    }

    @Test
    void testByDetails_common() {
        String details = "Details message";
        try {
            Validator.byDetails(details).isNull(null, "value");
        } catch (ItemValidateException ex) {
            assertEquals(details, ex.getDetails());
        }
    }

    @Test
    void testByDetails_withArgs() {
        String details = "Details %s";
        Object[] args = new Object[] {
                "message"
        };
        String expectedDetails = String.format(details, args);

        try {
            Validator.byDetails(details, args).isNull(null, "value");
        } catch (ItemValidateException ex) {
            assertEquals(expectedDetails, ex.getDetails());
        }
    }
}
