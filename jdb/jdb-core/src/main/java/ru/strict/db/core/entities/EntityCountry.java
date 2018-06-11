package ru.strict.db.core.entities;

/**
 * Страна
 */
public class EntityCountry<ID> extends EntityNamed<ID> {

    public EntityCountry() {
        super();
    }

    public EntityCountry(String caption) {
        super(caption);
    }

    public EntityCountry(ID id, String caption) {
        super(id, caption);
    }
}
