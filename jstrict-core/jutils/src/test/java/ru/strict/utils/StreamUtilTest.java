package ru.strict.utils;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.UUID;

import static ru.strict.utils.StreamUtil.*;

@RunWith(JUnit4.class)
public class StreamUtilTest {

    @Test
    public void testIsNull_bySupplier() {
        TestData model = new TestData();

        Assert.assertTrue(isNull(model::getId));
    }

    @Test
    public void testNot_bySupplier() {
        TestData testData = new TestData();
        testData.setSuccess(true);

        Assert.assertFalse(not(testData::isSuccess));
    }

    @Test
    public void testNot() {
        TestData model = new TestData();

        Assert.assertFalse(not(isNull(model::getId)));
    }

    @Test
    public void testIsNull() {
        TestData model = new TestData();

        Assert.assertTrue(isNull(model.getId()));
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
