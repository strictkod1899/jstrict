package ru.strict.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Escaper {

    /**
     * Key - что заменить
     * Values - на что заменить
     */
    private final Map<String, String> escapeStrings;

    private Escaper(Map<String, String> escapeStrings) {
        this.escapeStrings = escapeStrings;
    }

    public String escape(String string) {
        Set<String> keys = escapeStrings.keySet();
        StringBuilder stringBuilder = new StringBuilder(string);

        for (String replaceFrom : keys) {
            String replaceTo = escapeStrings.get(replaceFrom);
            replace(stringBuilder, replaceFrom, replaceTo);
        }

        return stringBuilder.toString();
    }

    private void replace(StringBuilder string, String replaceFrom, String replaceTo) {
        int indexOf = string.indexOf(replaceFrom);
        while (indexOf != -1) {
            string.replace(indexOf, indexOf + 1, replaceTo);
            indexOf = string.indexOf(replaceFrom);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final Map<String, String> escapeStrings;

        public Builder() {
            escapeStrings = new HashMap<>();
        }

        public Builder addEscape(String replaceTo, char replaceFrom, char...replaceFromArray) {
            escapeStrings.put(String.valueOf(replaceFrom), replaceTo);
            if (replaceFromArray != null) {
                for (Character replaceFromItem : replaceFromArray) {
                    escapeStrings.put(String.valueOf(replaceFromItem), replaceTo);
                }
            }

            return this;
        }

        public Escaper build() {
            return new Escaper(escapeStrings);
        }
    }
}
