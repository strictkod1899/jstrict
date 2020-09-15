package ru.strict.patterns.model;

import java.util.Objects;

/**
 * Именованная модель (содержит строку заголовка)
 */
public abstract class NamedModel<ID> extends BaseModel<ID> implements INamedModel<ID> {

    /**
     * Наименование записи
     */
    private String name;

    //<editor-fold defaultState="collapsed" desc="constructors">
    public NamedModel() {
        super();
        name = null;
    }

    public NamedModel(String name) {
        super();
        this.name = name;
    }

    public NamedModel(ID id, String name) {
        super(id);
        this.name = name;
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Get/Set">
    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Base override">
    @Override
    public String toString() {
        return String.format("[%s]: %s", String.valueOf(getId()), name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        NamedModel<ID> model = (NamedModel<ID>) o;
        return Objects.equals(name, model.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
    //</editor-fold>
}
