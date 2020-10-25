package ru.strict.utils;

import java.util.function.Supplier;

public class StreamUtil {

    private StreamUtil() { }

    public static boolean isNull(Object object) {
        return object == null;
    }

    public static boolean isNull(Supplier<Object> baseValdiate) {
        return baseValdiate.get() == null;
    }

    public static boolean not(Supplier<Boolean> baseValdiate) {
        return !baseValdiate.get();
    }

    public static boolean not(boolean bool) {
        return !bool;
    }
}
