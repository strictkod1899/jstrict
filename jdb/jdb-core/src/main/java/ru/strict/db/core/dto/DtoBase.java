package ru.strict.db.core.dto;

import ru.strict.db.core.mappers.MapTarget;
import ru.strict.utils.UtilHashCode;

/**
 * Базовый entity-класс
 * @param <ID> Тип поля ID
 */
public abstract class DtoBase<ID>  implements MapTarget {

    /**
     * Идентификатор записи
     */
    private ID id;

    //<editor-fold defaultState="collapsed" desc="constructors">
    public DtoBase() {
        id = null;
    }

    public DtoBase(ID id) {
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
        return String.format("dto base [%s]", String.valueOf(id));
    }

    @Override
    public boolean equals(Object obj){
        if(obj!=null && obj instanceof DtoBase) {
            DtoBase object = (DtoBase) obj;
            return object.getId().equals(id);
        }else
            return false;
    }

    @Override
    public int hashCode(){
        return UtilHashCode.createHashCode(id);
    }
    //</editor-fold>
}
