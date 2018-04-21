package ru.strict.db.entities;

import ru.strict.db.mappers.MapSource;
import ru.strict.db.mappers.MapTarget;

/**
 * Класс определяет базовый функционал entity
 * @param <ID> Тип поля ID
 */
public abstract class StrictEntityBase<ID> implements MapSource, MapTarget {

    /**
     * id записи
     */
    private ID id;

    //<editor-fold defaultState="collapsed" desc="constructors">
    public StrictEntityBase() {}

    public StrictEntityBase(ID id) {
        this.id = id;
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Get/Set">
    /**
     * Получить id записи
     * @return
     */
    public ID getId() {
        return id;
    }

    /**
     * Установить новый id записи
     * @param id id записи
     */
    public void setId(ID id) {
        this.id = id;
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Base override">
    @Override
    public String toString(){
        return String.format("entity[%s]", String.valueOf(id));
    }

    @Override
    public boolean equals(Object obj){
        if(obj instanceof StrictEntityBase) {
            StrictEntityBase entity = (StrictEntityBase) obj;
            return entity.id.equals(id);
        }else
            return false;
    }
    //</editor-fold>
}
