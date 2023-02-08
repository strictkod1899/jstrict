package ru.strict.validate;

import java.util.regex.Pattern;

public class RegexValidator {

    public static boolean isEmail(String str) {
        if (str == null) {
            return false;
        }

        var pattern = Pattern.compile("^[-\\w.]+@([A-z0-9][-A-z0-9]+\\.)+[A-z]{2,4}$");
        var match = pattern.matcher(str);
        return match.matches();
    }

    public static boolean isUUID(String str) {
        if (str == null) {
            return false;
        }

        var pattern = Pattern.compile(
                "^[0-9A-Fa-f]{8}\\-[0-9A-Fa-f]{4}\\-[0-9A-Fa-f]{4}\\-[0-9A-Fa-f]{4}\\-[0-9A-Fa-f]{12}$");
        var match = pattern.matcher(str);
        return match.matches();
    }

    public static boolean isMACAddress(String str) {
        if (str == null) {
            return false;
        }

        var pattern = Pattern.compile("([0-9a-fA-F]{2}([:-]|$)){6}$|([0-9a-fA-F]{4}([.]|$)){3}");
        var match = pattern.matcher(str);
        return match.matches();
    }

    public static boolean isIPv4(String str) {
        if (str == null) {
            return false;
        }

        var pattern = Pattern.compile("((25[0-5]|2[0-4]\\d|[01]?\\d\\d?)\\.){3}(25[0-5]|2[0-4]\\d|[01]?\\d\\d?)");
        var match = pattern.matcher(str);
        return match.matches();
    }

    public static boolean isIPv6(String str) {
        if (str == null) {
            return false;
        }

        var pattern = Pattern.compile("((^|:)([0-9a-fA-F]{0,4})){1,8}$");
        var match = pattern.matcher(str);
        return match.matches();
    }

    public static boolean isHostDomain(String str) {
        if (str == null) {
            return false;
        }

        var pattern = Pattern.compile("^([a-zA-Z0-9]([a-zA-Z0-9\\-]{0,61}[a-zA-Z0-9])?\\.)+[a-zA-Z]{2,6}$");
        var match = pattern.matcher(str);
        return match.matches();
    }
}
