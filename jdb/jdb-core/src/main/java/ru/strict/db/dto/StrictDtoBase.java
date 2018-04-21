package ru.strict.db.dto;

import ru.strict.db.mappers.MapSource;
import ru.strict.db.mappers.MapTarget;

/**
 * Класс определяет базовый функционал dto
 * @param <ID> Тип поля ID
 */
public abstract class StrictDtoBase<ID>  implements MapSource, MapTarget {

    /**
     * id записи
     */
    private ID id;

    //<editor-fold defaultState="collapsed" desc="constructors">
    public StrictDtoBase() {}

    public StrictDtoBase(ID id) {
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
        return String.format("dto[%s]", String.valueOf(id));
    }

    @Override
    public boolean equals(Object obj){
        if(obj instanceof StrictDtoBase) {
            StrictDtoBase entity = (StrictDtoBase) obj;
            return entity.id.equals(id);
        }else
            return false;
    }
    //</editor-fold>
}
