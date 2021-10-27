package ru.strict.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EncryptionUtilTest {

    @Test
    void testHashMd5() {
        assertEquals("098f6bcd4621d373cade4e832627b4f6", EncryptionUtil.hashMd5("test"));
    }

    @Test
    void testHashMd5Apache() {
        assertEquals("098f6bcd4621d373cade4e832627b4f6", EncryptionUtil.hashMd5Apache("test"));
    }

    @Test
    void testHashSha1() {
        assertEquals("a94a8fe5ccb19ba61c4c0873d391e987982fbbd3", EncryptionUtil.hashSha1("test"));
    }
}
