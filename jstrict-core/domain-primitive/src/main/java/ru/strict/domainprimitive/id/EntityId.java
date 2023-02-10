package ru.strict.domainprimitive.id;

import lombok.EqualsAndHashCode;
import ru.strict.validate.CommonValidator;

import java.util.Objects;
import java.util.UUID;

@EqualsAndHashCode
public class EntityId {
    private final UUID id;

    public EntityId() {
        this.id = UUID.randomUUID();
    }

    public EntityId(String idStr) {
        if (CommonValidator.isNullOrEmpty(idStr)) {
            throw EntityIdError.errIdIsEmpty();
        }

        try {
            this.id = UUID.fromString(idStr);
        } catch (Exception e) {
            throw EntityIdError.errInvalidIdFormat(e);
        }
    }

    @Override
    public String toString() {
        return id.toString();
    }
}
