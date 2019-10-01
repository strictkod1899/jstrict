package ru.strict.models;

import java.util.Objects;

/**
 * Именованная модель (содержит строку заголовка)
 */
public abstract class ModelNamed<ID> extends ModelBase<ID> implements INamedModel<ID>, IFillNamedModel<ID> {

    /**
     * Наименование записи
     */
    private String caption;

    //<editor-fold defaultState="collapsed" desc="constructors">
    private void initialize(String caption){
        if(caption == null) {
            throw new IllegalArgumentException("caption is NULL");
        }

        this.caption = caption;
    }

    public ModelNamed() {
        super();
        caption = null;
    }

    public ModelNamed(String caption) {
        super();
        initialize(caption);
    }

    public ModelNamed(ID id, String caption) {
        super(id);
        initialize(caption);
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Get/Set">
    @Override
    public String getCaption() {
        return caption;
    }

    @Override
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
        if (!super.equals(o)) return false;
        ModelNamed<ID> model = (ModelNamed<ID>) o;
        return Objects.equals(caption, model.caption);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), caption);
    }
    //</editor-fold>
}
