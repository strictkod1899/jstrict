package ru.strict.models;

import java.util.Objects;

/**
 * Именованная модель (содержит строку заголовка)
 */
public abstract class NamedModel<ID> extends BaseModel<ID> implements INamedModel<ID> {

    /**
     * Наименование записи
     */
    private String caption;

    //<editor-fold defaultState="collapsed" desc="constructors">
    private void init(String caption){
        if(caption == null) {
            throw new IllegalArgumentException("caption is NULL");
        }

        this.caption = caption;
    }

    public NamedModel() {
        super();
        caption = null;
    }

    public NamedModel(String caption) {
        super();
        init(caption);
    }

    public NamedModel(ID id, String caption) {
        super(id);
        init(caption);
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Get/Set">
    @Override
    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Base override">
    @Override
    public String toString(){
        return String.format("[%s]: %s", String.valueOf(getId()), caption);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NamedModel<ID> model = (NamedModel<ID>) o;
        return Objects.equals(caption, model.caption);
    }

    @Override
    public int hashCode() {
        return Objects.hash(caption);
    }
    //</editor-fold>
}
