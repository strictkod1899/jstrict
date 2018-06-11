package ru.strict.db.core.entities;

import java.util.Collection;

/**
 * Страна
 */
public class EntityCountry<ID> extends EntityNamed<ID> {

    /**
     * Пользователи свзяанные с ролью
     */
    private Collection<EntityCity> cities;

    public EntityCountry() {
        super();
    }

    public EntityCountry(String caption) {
        super(caption);
    }

    public EntityCountry(ID id, String caption) {
        super(id, caption);
    }

    public Collection<EntityCity> getCities() {
        return cities;
    }

    public void setCities(Collection<EntityCity> cities) {
        this.cities = cities;
    }
}
