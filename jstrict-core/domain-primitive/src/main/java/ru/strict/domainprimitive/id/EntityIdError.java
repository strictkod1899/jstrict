package ru.strict.domainprimitive.id;

import lombok.experimental.UtilityClass;
import ru.strict.exception.CodeableException;

@UtilityClass
public class EntityIdError {
    public final String idIsEmptyErrorCode = "4d828435-001";
    public final String invalidIdFormatErrorCode = "4d828435-002";

    public CodeableException errIdIsEmpty() {
        return new CodeableException(idIsEmptyErrorCode, "EntityId is empty");
    }

    public CodeableException errInvalidIdFormat(Exception cause) {
        return new CodeableException(invalidIdFormatErrorCode, String.format("Invalid format for EntityId. Cause: '%s'", cause.getMessage()), cause);
    }
}
