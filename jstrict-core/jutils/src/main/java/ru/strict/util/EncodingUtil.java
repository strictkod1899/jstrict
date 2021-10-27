package ru.strict.util;

import lombok.experimental.UtilityClass;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static java.nio.charset.StandardCharsets.ISO_8859_1;
import static java.nio.charset.StandardCharsets.UTF_8;

@UtilityClass
public class EncodingUtil {

    public static final Charset CP866_CHARSET = Charset.forName("Cp866");
    public static final Charset CP1251_CHARSET = Charset.forName("Cp1251");
    public static final Charset CP1252_CHARSET = Charset.forName("Cp1252");

    public String fromCp866(String text) {
        return new String(text.getBytes(CP866_CHARSET)).intern();
    }

    public List<String> fromStandardEncodings(String text) {
        return List.of(text,
                new String(text.getBytes(UTF_8)).intern(),
                fromCp866(text),
                new String(text.getBytes(ISO_8859_1)).intern(),
                new String(text.getBytes(CP1251_CHARSET)).intern(),
                new String(text.getBytes(CP1252_CHARSET)).intern()
        );
    }
}
