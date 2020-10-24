package ru.strict.utils;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class StringUtilTest {

    @Test
    public void testEscapeFileName() {
        String escapedFilename = StringUtil.escapeFileName("my*|f*i\\\\le\\: name?>");
        Assert.assertEquals("myfile_name", escapedFilename);
    }
}
