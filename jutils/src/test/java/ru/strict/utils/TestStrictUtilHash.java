package ru.strict.utils;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import ru.strict.utils.StrictUtilHash;

@RunWith(JUnit4.class)
public class TestStrictUtilHash {

    @Test
    public void testHashMd5(){
        Assert.assertEquals("098f6bcd4621d373cade4e832627b4f6", StrictUtilHash.hashMd5("test"));
    }

    @Test
    public void testHashMd5Apache(){
        Assert.assertEquals("098f6bcd4621d373cade4e832627b4f6", StrictUtilHash.hashMd5Apache("test"));
    }

    @Test
    public void testHashSha1(){
        Assert.assertEquals("a94a8fe5ccb19ba61c4c0873d391e987982fbbd3", StrictUtilHash.hashSha1("test"));

    }
}
