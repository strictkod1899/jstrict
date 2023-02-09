package ru.strict.domainprimitive.title;

import ru.strict.validate.CommonValidator;

import java.util.Objects;

public final class Title {
    private static final int TITLE_MAX_LENGTH = 140;

    private final String value;

    public static Title from(String value) {
        return new Title(value);
    }

    private Title(String value) {
        if (CommonValidator.isNullOrEmpty(value)) {
            throw TitleError.errTitleIsEmpty();
        }
        if (value.length() > TITLE_MAX_LENGTH) {
            throw TitleError.errTitleTooLong();
        }

        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        var other = (Title) o;
        return Objects.equals(this.value, other.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value;
    }
}
