package ru.strict.db.dto;

import ru.strict.db.mappers.MapTarget;
import ru.strict.utils.StrictUtilHashCode;

/**
 * Базовый entity-класс
 * @param <ID> Тип поля ID
 */
public abstract class StrictDtoBase<ID>  implements MapTarget {

    /**
     * Идентификатор записи
     */
    private ID id;

    //<editor-fold defaultState="collapsed" desc="constructors">
    public StrictDtoBase() {
        id = null;
    }

    public StrictDtoBase(ID id) {
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
        return String.format("dto base [%s]", String.valueOf(id));
    }

    @Override
    public boolean equals(Object obj){
        if(obj!=null && obj instanceof StrictDtoBase) {
            StrictDtoBase object = (StrictDtoBase) obj;
            return object.getId().equals(id);
        }else
            return false;
    }

    @Override
    public int hashCode(){
        return StrictUtilHashCode.createHashCode(id);
    }
    //</editor-fold>
}
