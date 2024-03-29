package ru.strict.domainprimitive.title;

import lombok.experimental.UtilityClass;
import ru.strict.exception.CodeableException;

@UtilityClass
public class TitleError {
    public final String titleIsEmptyErrorCode = "55420974-001";
    public final String titleTooLongErrorCode = "55420974-002";

    public CodeableException errTitleIsEmpty() {
        return new CodeableException(titleIsEmptyErrorCode, "Title is empty");
    }

    public CodeableException errTitleTooLong() {
        return new CodeableException(titleTooLongErrorCode, "Title is too long");
    }
}
