package ru.strict.db.core.dto;

import ru.strict.patterns.MapTarget;
import ru.strict.utils.UtilData;

import java.util.Objects;

/**
 * Базовый entity-класс
 * @param <ID> Тип поля ID
 */
public abstract class DtoBase<ID> implements MapTarget, Comparable<DtoBase<ID>>, Cloneable {

    /**
     * Идентификатор записи
     */
    private ID id;

    //<editor-fold defaultState="collapsed" desc="constructors">
    public DtoBase() {
        id = null;
    }

    public DtoBase(ID id) {
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
        DtoBase<ID> dtoBase = (DtoBase<ID>) o;
        return Objects.equals(id, dtoBase.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public int compareTo(DtoBase<ID> object){
        return UtilData.compareTo(getId(), object.getId());
    }
    //</editor-fold>
}
