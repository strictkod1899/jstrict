package ru.strict.util;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StringUtilTest {

    @Test
    void testEscapeFileName() {
        var escapedFilename = StringUtil.escapeFileName("my*|f*i\\\\le\\: name?>");
        assertEquals("myfile_name", escapedFilename);
    }

    @Test
    void testConvertStringToUTF8_success() {
        var string = "123";

        var actualString = StringUtil.convertToUTF8(string);
        assertEquals(string, actualString);
    }

    @Test
    void testJoin_array_success() {
        var expectedString = "Hello-World-Man";

        var actualString = StringUtil.join("-", "Hello", "World", "Man");
        assertEquals(expectedString, actualString);
    }

    @Test
    void testJoin_collection_success() {
        var expectedString = "Hello World Man";

        var actualString = StringUtil.join(" ", List.of("Hello", "World", "Man"));
        assertEquals(expectedString, actualString);
    }

    @Test
    void testIsEmptyOrNull_success() {
        assertTrue(StringUtil.isNullOrEmpty(""));
        assertTrue(StringUtil.isNullOrEmpty(null));
        assertFalse(StringUtil.isNullOrEmpty("123"));
    }

    @Test
    void testEmptyToNull_success() {
        assertNull(StringUtil.emptyToNull(""));
        assertNull(StringUtil.emptyToNull(null));
        assertEquals("123", StringUtil.emptyToNull("123"));
    }

    @Test
    void testNullToEmpty_success() {
        assertEquals("", StringUtil.nullToEmpty(""));
        assertEquals("", StringUtil.nullToEmpty(null));
        assertEquals("123", StringUtil.emptyToNull("123"));
    }

    @Test
    void testToUpperFirstSymbol_success() {
        assertEquals("Hello", StringUtil.toUpperFirstSymbol("hello"));
        assertEquals("Hello", StringUtil.toUpperFirstSymbol("Hello"));
    }

    @Test
    void testToLowerFirstSymbol_success() {
        assertEquals("hello", StringUtil.toLowerFirstSymbol("hello"));
        assertEquals("hello", StringUtil.toLowerFirstSymbol("Hello"));
    }
}
