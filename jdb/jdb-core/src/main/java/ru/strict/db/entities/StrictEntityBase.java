package ru.strict.db.entities;

import ru.strict.db.mappers.MapTarget;

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
        if(obj instanceof StrictEntityBase) {
            StrictEntityBase object = (StrictEntityBase) obj;
            return object.id.equals(id);
        }else
            return false;
    }
    //</editor-fold>
}
