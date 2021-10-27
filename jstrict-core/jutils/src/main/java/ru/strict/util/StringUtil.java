package ru.strict.util;

import ru.strict.exception.ValidateException;
import ru.strict.validate.CommonValidate;
import ru.strict.validate.Validator;

import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public final class StringUtil {

    private static final Escaper FILE_NAME_ESCAPER = Escaper.builder()
            .addEscape("", '*', '|', '\\', ':', '"', '<', '>', '?', '/')
            .addEscape("_", ' ')
            .build();

    private StringUtil() {
    }

    public static String convertToUTF8(String value) {
        return convertToEncode(value, null, "UTF-8");
    }

    public static String convertToUTF8(String value, String defaultEncode) {
        return convertToEncode(value, defaultEncode, "UTF-8");
    }

    public static String convertToEncode(String text, String defaultEncoding, String outputEncoding) {
        Validator.isNull(text, "text");

        String convertedString;
        try {
            if (CommonValidate.isNullOrEmpty(outputEncoding)) {
                convertedString = text;
            } else {
                if (!CommonValidate.isNullOrEmpty(defaultEncoding)) {
                    convertedString = new String(text.getBytes(defaultEncoding), outputEncoding);
                } else {
                    convertedString = new String(text.getBytes(), outputEncoding);
                }
            }
        } catch (UnsupportedEncodingException ex) {
            throw new ValidateException(ex);
        }

        return convertedString;
    }

    /**
     * Объединить строки с использованием общего разделителя.
     * Пустые или нулевые строки будут пропущены
     * strings может быть null
     */
    public static String safeJoin(String separator, Object... strings) {
        return join(separator, Optional.ofNullable(strings).orElse(new String[0]));
    }

    /**
     * Объединить строки с использованием общего разделителя.
     * Пустые или нулевые строки будут пропущены
     * strings может быть null
     */
    public static String safeJoin(String separator, Collection<Object> strings) {
        return join(separator, strings == null ? List.of() : strings);
    }

    /**
     * Объединить строки с использованием общего разделителя.
     * Пустые или нулевые строки будут пропущены
     */
    public static String join(String separator, Collection<Object> strings) {
        Validator.isNull(separator, "separator");
        Validator.isNull(strings, "strings elements for join");

        var joinedString = new StringBuilder();
        strings.forEach(item -> {
            if (item != null && item.toString().length() > 0) {
                if (joinedString.length() > 0) {
                    joinedString.append(separator);
                }

                joinedString.append(item);
            }
        });

        return joinedString.toString();
    }

    /**
     * Объединить строки с использованием общего разделителя.
     * Пустые или нулевые строки будут пропущены
     */
    public static String join(String separator, Object... strings) {
        Validator.isNull(separator, "separator");
        Validator.isNull(strings, "strings elements for join");

        var joinedString = new StringBuilder();
        for (var item : strings) {
            if (item != null && item.toString().length() > 0) {
                if (joinedString.length() > 0) {
                    joinedString.append(separator);
                }

                joinedString.append(item);
            }
        }

        return joinedString.toString();
    }

    public static boolean isNullOrEmpty(String str) {
        return CommonValidate.isNullOrEmpty(str);
    }

    /**
     * Если строка равна null, тогда вернется пустая строка
     */
    public static String nullToEmpty(String str) {
        return str == null ? "" : str;
    }

    /**
     * Если строка пустая, тогда вернется null
     */
    public static String emptyToNull(String str) {
        return CommonValidate.isNullOrEmpty(str) ? null : str;
    }

    /**
     * Сделать первый символ строки заглавным
     */
    public static String toUpperFirstSymbol(String str) {
        if (str == null) {
            return null;
        }

        if (str.length() == 0) {
            return "";
        }

        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    /**
     * Сделать первый символ строки прописным
     */
    public static String toLowerFirstSymbol(String str) {
        if (str == null) {
            return null;
        }

        if (str.length() == 0) {
            return "";
        }

        return str.substring(0, 1).toLowerCase() + str.substring(1);
    }

    /**
     * Привести имя файла к виду для записи в файловой системе (заменить запрещенные символы)
     */
    public static String escapeFileName(String filename) {
        return FILE_NAME_ESCAPER.escape(filename);
    }
}
