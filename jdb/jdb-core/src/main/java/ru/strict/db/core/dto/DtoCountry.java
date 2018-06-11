package ru.strict.db.core.dto;

import ru.strict.db.core.entities.EntityNamed;

/**
 * Страна
 */
public class DtoCountry<ID> extends EntityNamed<ID> {

    public DtoCountry() {
        super();
    }

    public DtoCountry(String caption) {
        super(caption);
    }

    public DtoCountry(ID id, String caption) {
        super(id, caption);
    }
}
