package ru.strict.domainprimitive.title;

import lombok.EqualsAndHashCode;
import ru.strict.validate.CommonValidator;

import java.util.Objects;

@EqualsAndHashCode
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
    public String toString() {
        return value;
    }
}
