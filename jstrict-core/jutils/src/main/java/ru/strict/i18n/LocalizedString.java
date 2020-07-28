package ru.strict.i18n;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ru.strict.marshaling.LocalizedStringDeserializer;
import ru.strict.marshaling.LocalizedStringSerializer;
import ru.strict.validate.Validator;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

@JsonSerialize(using = LocalizedStringSerializer.class)
@JsonDeserialize(using = LocalizedStringDeserializer.class)
public class LocalizedString {

    private Map<String, String> strings;

    public LocalizedString() {
        this.strings = new HashMap<>();
    }

    public LocalizedString(Map<String, String> strings) {
        Validator.isNull(strings, "strings");

        setStrings(strings);
    }

    public String get(String lang) {
        return strings.get(lang);
    }

    public void set(String lang, String text) {
        if (text != null && lang != null) {
            strings.put(lang, text);
        }
    }

    public void remove(String lang) {
        strings.remove(lang);
    }

    public Set<String> getLangs() {
        return strings.keySet();
    }

    public Map<String, String> getStrings() {
        return strings;
    }

    public void setStrings(Map<String, String> strings) {
        Validator.isNull(strings, "strings");
        this.strings = strings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LocalizedString that = (LocalizedString) o;
        return Objects.equals(strings, that.strings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(strings);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Map<String, String> strings;

        private Builder() {
            this.strings = new HashMap<>();
        }

        public Builder item(String lang, String text) {
            if (lang != null && text != null) {
                strings.put(lang, text);
            }
            return this;
        }

        public LocalizedString build() {
            return new LocalizedString(strings);
        }
    }
}
