package ru.strict.db.core.dto;

/**
 * Страна
 */
public class DtoCountry<ID> extends DtoNamed<ID> {

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
