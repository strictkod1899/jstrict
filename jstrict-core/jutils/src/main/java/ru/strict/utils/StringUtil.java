package ru.strict.utils;

import ru.strict.validate.CommonValidate;
import ru.strict.validate.Validator;

import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

public final class StringUtil {

    private static final Escaper FILE_NAME_ESCAPER = Escaper.builder()
            .addEscape("", '*', '|', '\\', ':', '"', '<', '>', '?', '/')
            .addEscape("_", ' ')
            .build();

    private StringUtil() {
    }

    public static String convertStringToUTF8(String value) {
        return convertStringToEncode(value, null, "UTF-8");
    }

    public static String convertStringFromISOToUTF8(String value) {
        return convertStringToEncode(value, "iso-8859-1", "UTF-8");
    }

    public static String convertStringFromEncodeToUTF8(String value, String defaultEncode) {
        return convertStringToEncode(value, defaultEncode, "UTF-8");
    }

    public static String convertStringToEncode(String value, String defaultEncoding, String encodingOutput) {
        Validator.isNull(value, "value");

        String result;
        try {
            if (CommonValidate.isEmptyOrNull(encodingOutput)) {
                result = value;
            } else {
                if (!CommonValidate.isEmptyOrNull(defaultEncoding)) {
                    result = new String(value.getBytes(defaultEncoding), encodingOutput);
                } else {
                    result = new String(value.getBytes(), encodingOutput);
                }
            }
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex);
        }

        return result;
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
        return join(separator, Optional.ofNullable(strings).orElse(Collections.EMPTY_LIST));
    }

    /**
     * Объединить строки с использованием общего разделителя.
     * Пустые или нулевые строки будут пропущены
     */
    public static String join(String separator, Collection<Object> strings) {
        Validator.isNull(separator, "separator");
        Validator.isNull(strings, "strings elements for join");

        StringBuilder result = new StringBuilder();
        strings.forEach(item -> {
            if (item != null && item.toString().length() > 0) {
                if (result.length() > 0) {
                    result.append(separator);
                }

                result.append(item);
            }
        });

        return result.toString();
    }

    /**
     * Объединить строки с использованием общего разделителя.
     * Пустые или нулевые строки будут пропущены
     */
    public static String join(String separator, Object... strings) {
        Validator.isNull(separator, "separator");
        Validator.isNull(strings, "strings elements for join");

        StringBuilder result = new StringBuilder();
        for (Object item : strings) {
            if (item != null && item.toString().length() > 0) {
                if (result.length() > 0) {
                    result.append(separator);
                }

                result.append(item);
            }
        }

        return result.toString();
    }

    public static boolean isEmptyOrNull(String str) {
        return CommonValidate.isEmptyOrNull(str);
    }

    /**
     * Если строка равна null, тогда вернется пустая строка
     *
     * @param str
     * @return
     */
    public static String nullToEmpty(String str) {
        return str == null ? "" : str;
    }

    /**
     * Если строка пустая, тогда вернется null
     *
     * @param str
     * @return
     */
    public static String emptyToNull(String str) {
        return CommonValidate.isEmptyOrNull(str) ? null : str;
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
