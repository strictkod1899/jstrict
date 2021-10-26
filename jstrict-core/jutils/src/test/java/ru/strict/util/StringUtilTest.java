package ru.strict.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringUtilTest {

    @Test
    void testEscapeFileName() {
        var escapedFilename = StringUtil.escapeFileName("my*|f*i\\\\le\\: name?>");
        assertEquals("myfile_name", escapedFilename);
    }
}
