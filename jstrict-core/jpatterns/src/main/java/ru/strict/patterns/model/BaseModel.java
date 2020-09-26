package ru.strict.patterns.model;

import ru.strict.utils.CommonUtil;

/**
 * Базовый класс модели
 *
 * @param <ID> Тип поля ID
 */
public abstract class BaseModel<ID> implements IModel<ID>,
        Comparable<BaseModel<ID>>,
        Cloneable {
    /**
     * Идентификатор записи
     */
    private ID id;

    //<editor-fold defaultState="collapsed" desc="constructors">
    public BaseModel() {
        id = null;
    }

    public BaseModel(ID id) {
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
    public String toString() {
        return "BaseModel{" +
                "id=" + id +
                '}';
    }

    @Override
    public int compareTo(BaseModel<ID> object) {
        return CommonUtil.compareTo(getId(), object.getId());
    }
    //</editor-fold>
}
