package ru.strict.validate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Проверка валидности базовых конструкций (пароль, email и .д.)
 */
public class RegexValidate {

    /**
     * Проверка адреса электронной почты
     */
    public static boolean isEmail(String str) {
        boolean result = false;

        if (str != null) {
            Pattern pattern = Pattern.compile("^[-\\w.]+@([A-z0-9][-A-z0-9]+\\.)+[A-z]{2,4}$");
            Matcher match = pattern.matcher(str);
            result = match.matches();
        }

        return result;
    }

    /**
     * Проверка UUID
     */
    public static boolean isUUID(String str) {
        boolean result = false;

        if (str != null) {
            Pattern pattern = Pattern.compile(
                    "^[0-9A-Fa-f]{8}\\-[0-9A-Fa-f]{4}\\-[0-9A-Fa-f]{4}\\-[0-9A-Fa-f]{4}\\-[0-9A-Fa-f]{12}$");
            Matcher match = pattern.matcher(str);
            result = match.matches();
        }

        return result;
    }

    /**
     * Проверка MAC-адреса
     */
    public static boolean isMACAddress(String str) {
        boolean result = false;

        if (str != null) {
            Pattern pattern = Pattern.compile("([0-9a-fA-F]{2}([:-]|$)){6}$|([0-9a-fA-F]{4}([.]|$)){3}");
            Matcher match = pattern.matcher(str);
            result = match.matches();
        }

        return result;
    }

    /**
     * Проверка пароля (Строчные и прописные латинские буквы, цифры, спецсимволы. Минимум 8 символов)
     */
    public static boolean isPassword(String str) {
        boolean result = false;

        if (str != null) {
            Pattern pattern = Pattern.compile("(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$");
            Matcher match = pattern.matcher(str);
            result = match.matches();
        }

        return result;
    }

    /**
     * Проверка имени пользователя (с ограничением 2-20 символов, которыми могут быть буквы и цифры, первый символ
     * обязательно буква)
     */
    public static boolean isUsername(String str) {
        boolean result = false;

        if (str != null) {
            Pattern pattern = Pattern.compile("^[a-zA-Z][a-zA-Z0-9-_\\.]{1,20}$");
            Matcher match = pattern.matcher(str);
            result = match.matches();
        }

        return result;
    }

    /**
     * Проверка IPv4
     */
    public static boolean isIPv4(String str) {
        boolean result = false;

        if (str != null) {
            Pattern pattern =
                    Pattern.compile("((25[0-5]|2[0-4]\\d|[01]?\\d\\d?)\\.){3}(25[0-5]|2[0-4]\\d|[01]?\\d\\d?)");
            Matcher match = pattern.matcher(str);
            result = match.matches();
        }

        return result;
    }

    /**
     * Проверка IPv6
     */
    public static boolean isIPv6(String str) {
        boolean result = false;

        if (str != null) {
            Pattern pattern = Pattern.compile("((^|:)([0-9a-fA-F]{0,4})){1,8}$");
            Matcher match = pattern.matcher(str);
            result = match.matches();
        }

        return result;
    }

    /**
     * Проверка домена (например abcd.com)
     */
    public static boolean isDomain(String str) {
        boolean result = false;

        if (str != null) {
            Pattern pattern = Pattern.compile("^([a-zA-Z0-9]([a-zA-Z0-9\\-]{0,61}[a-zA-Z0-9])?\\.)+[a-zA-Z]{2,6}$");
            Matcher match = pattern.matcher(str);
            result = match.matches();
        }

        return result;
    }

    /**
     * Проверка набора из букв и цифр (латиница + кириллица)
     */
    public static boolean isStringNumberEngRus(String str) {
        boolean result = false;

        if (str != null) {
            Pattern pattern = Pattern.compile("^[а-яА-ЯёЁa-zA-Z0-9]+$");
            Matcher match = pattern.matcher(str);
            result = match.matches();
        }

        return result;
    }

    /**
     * Проверка набора из букв и цифр, а также символов _ и -
     */
    public static boolean isCaption(String str) {
        boolean result = false;

        if (str != null) {
            Pattern pattern = Pattern.compile("^[\\w-]+");
            Matcher match = pattern.matcher(str);
            result = match.matches();
        }

        return result;
    }


    /**
     * Проверка набора из букв и цифр (латиница)
     */
    public static boolean isStringNumberEng(String str) {
        boolean result = false;

        if (str != null) {
            Pattern pattern = Pattern.compile("^[a-zA-Z0-9]+$");
            Matcher match = pattern.matcher(str);
            result = match.matches();
        }

        return result;
    }

    /**
     * Проверка номера крединой карты
     */
    public static boolean isCreditCardNumber(String str) {
        boolean result = false;

        if (str != null) {
            Pattern pattern = Pattern.compile("[0-9]{13,16}");
            Matcher match = pattern.matcher(str);
            result = match.matches();
        }

        return result;
    }
}
