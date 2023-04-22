package ru.strict.domainprimitive.title;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;
import ru.strict.validate.CommonValidator;

import java.util.Objects;

@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public final class Title {
    private static final int TITLE_MAX_LENGTH = 140;

    String value;

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
