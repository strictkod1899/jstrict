package ru.strict.domainprimitive.title;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;
import ru.strict.validate.CommonValidator;

@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public final class Title {
    public static final int MAX_TITLE_LENGTH = 240;

    String value;

    public static Title from(String value) {
        return new Title(value);
    }

    private Title(String value) {
        if (CommonValidator.isNullOrEmpty(value)) {
            throw TitleError.errTitleIsEmpty();
        }
        if (value.length() > MAX_TITLE_LENGTH) {
            throw TitleError.errTitleTooLong();
        }

        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
