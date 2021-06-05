package ru.strict.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StringUtilTest {

    @Test
    public void testEscapeFileName() {
        String escapedFilename = StringUtil.escapeFileName("my*|f*i\\\\le\\: name?>");
        Assertions.assertEquals("myfile_name", escapedFilename);
    }
}
