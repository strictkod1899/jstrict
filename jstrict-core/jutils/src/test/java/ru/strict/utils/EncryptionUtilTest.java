package ru.strict.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EncryptionUtilTest {

    @Test
    public void testHashMd5(){
        Assertions.assertEquals("098f6bcd4621d373cade4e832627b4f6", EncryptionUtil.hashMd5("test"));
    }

    @Test
    public void testHashMd5Apache(){
        Assertions.assertEquals("098f6bcd4621d373cade4e832627b4f6", EncryptionUtil.hashMd5Apache("test"));
    }

    @Test
    public void testHashSha1(){
        Assertions.assertEquals("a94a8fe5ccb19ba61c4c0873d391e987982fbbd3", EncryptionUtil.hashSha1("test"));

    }
}
