package ru.strict.db.core.entities;

import ru.strict.db.core.mappers.MapTarget;
import ru.strict.utils.UtilData;
import ru.strict.utils.UtilHashCode;

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
        if(id == null) {
            throw new NullPointerException("id is NULL");
        }

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
        return String.format("entity base [%s]", String.valueOf(id));
    }

    @Override
    public boolean equals(Object obj){
        if(obj!=null && obj instanceof EntityBase && obj!=null) {
            EntityBase object = (EntityBase) obj;
            return id.equals(object.getId());
        }else
            return false;
    }

    @Override
    public int hashCode(){
        return UtilHashCode.createHashCode(id);
    }

    @Override
    public int compareTo(EntityBase<ID> object){
        return UtilData.compareTo(getId(), object.getId());
    }
    //</editor-fold>
}
