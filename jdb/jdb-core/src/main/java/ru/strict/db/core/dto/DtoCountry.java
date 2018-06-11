package ru.strict.db.core.dto;

import java.util.Collection;

/**
 * Страна
 */
public class DtoCountry<ID> extends DtoNamed<ID> {

    /**
     * Пользователи свзяанные с ролью
     */
    private Collection<DtoCity> cities;

    public DtoCountry() {
        super();
    }

    public DtoCountry(String caption) {
        super(caption);
    }

    public DtoCountry(ID id, String caption) {
        super(id, caption);
    }

    public Collection<DtoCity> getCities() {
        return cities;
    }

    public void setCities(Collection<DtoCity> cities) {
        this.cities = cities;
    }
}
