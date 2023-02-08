package ru.strict.domainprimitive.id;

import ru.strict.validate.CommonValidator;

import java.util.Objects;
import java.util.UUID;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        var other = (EntityId) o;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return id.toString();
    }
}
