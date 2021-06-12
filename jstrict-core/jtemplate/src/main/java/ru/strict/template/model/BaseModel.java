package ru.strict.template.model;

public class BaseModel<ID> implements IModel<ID> {
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
    //</editor-fold>
}
