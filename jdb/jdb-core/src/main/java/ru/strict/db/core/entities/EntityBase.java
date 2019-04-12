package ru.strict.db.core.entities;

import ru.strict.patterns.MapTarget;
import ru.strict.utils.UtilData;

import java.util.Objects;


/**
 * Базовый entity-класс
 * @param <ID> Тип поля ID
 */
public abstract class EntityBase<ID> implements MapTarget, Comparable<EntityBase<ID>> {

    /**
     * Идентификатор записи
     */
    private ID id;

    //<editor-fold defaultState="collapsed" desc="constructors">
    public EntityBase() {
        id = null;
    }

    public EntityBase(ID id) {
        this.id = id;
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Get/Set">
    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Base override">
    @Override
    public String toString(){
        return id.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntityBase<ID> object = (EntityBase<ID>) o;
        return Objects.equals(id, object.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public int compareTo(EntityBase<ID> object){
        return UtilData.compareTo(getId(), object.getId());
    }
    //</editor-fold>
}
