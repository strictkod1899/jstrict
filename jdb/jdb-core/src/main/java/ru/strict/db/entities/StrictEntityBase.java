package ru.strict.db.entities;

import ru.strict.db.mappers.MapTarget;
import ru.strict.utils.StrictUtilHashCode;

/**
 * Базовый entity-класс
 * @param <ID> Тип поля ID
 */
public abstract class StrictEntityBase<ID> implements MapTarget {

    /**
     * Идентификатор записи
     */
    private ID id;

    //<editor-fold defaultState="collapsed" desc="constructors">
    public StrictEntityBase() {}

    public StrictEntityBase(ID id) {
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
        if(obj!=null && obj instanceof StrictEntityBase && obj!=null) {
            StrictEntityBase object = (StrictEntityBase) obj;
            return id.equals(object.getId());
        }else
            return false;
    }

    @Override
    public int hashCode(){
        return StrictUtilHashCode.createHashCode(id);
    }
    //</editor-fold>
}
