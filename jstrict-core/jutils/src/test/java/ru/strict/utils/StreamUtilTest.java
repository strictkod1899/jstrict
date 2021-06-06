package ru.strict.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static ru.strict.utils.StreamUtil.*;

public class StreamUtilTest {

    @Test
    public void testIsNull_bySupplier() {
        TestData model = new TestData();

        Assertions.assertTrue(isNull(model::getId));
    }

    @Test
    public void testNot_bySupplier() {
        TestData testData = new TestData();
        testData.setSuccess(true);

        Assertions.assertFalse(not(testData::isSuccess));
    }

    @Test
    public void testNot() {
        TestData model = new TestData();

        Assertions.assertFalse(not(isNull(model::getId)));
    }

    @Test
    public void testIsNull() {
        TestData model = new TestData();

        Assertions.assertTrue(isNull(model.getId()));
    }

    private static class TestData {
        private UUID id;
        private boolean success;

        public UUID getId() {
            return id;
        }

        public void setId(UUID id) {
            this.id = id;
        }

        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }
    }
}
