package ru.strict.models;

import ru.strict.utils.CommonUtil;

import java.util.Objects;

/**
 * Базовый класс модели
 * @param <ID> Тип поля ID
 */
public abstract class ModelBase<ID> implements IModel<ID>,
        Comparable<ModelBase<ID>>,
        Cloneable
{
    /**
     * Идентификатор записи
     */
    private ID id;

    //<editor-fold defaultState="collapsed" desc="constructors">
    public ModelBase() {
        id = null;
    }

    public ModelBase(ID id) {
        this.id = id;
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Get/Set">
    @Override
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
    public int compareTo(ModelBase<ID> object){
        return CommonUtil.compareTo(getId(), object.getId());
    }
    //</editor-fold>
}
