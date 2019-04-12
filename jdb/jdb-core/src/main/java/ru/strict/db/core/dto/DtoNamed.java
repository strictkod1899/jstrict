package ru.strict.db.core.dto;

import java.util.Objects;

/**
 * Именованный entity (содержит строку заголовка)
 */
public abstract class DtoNamed<ID> extends DtoBase<ID> {

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

    public DtoNamed() {
        super();
        caption = null;
    }

    public DtoNamed(String caption) {
        super();
        initialize(caption);
    }

    public DtoNamed(ID id, String caption) {
        super(id);
        initialize(caption);
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Get/Set">
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
        if (!super.equals(o)) return false;
        DtoNamed<ID> dtoNamed = (DtoNamed<ID>) o;
        return Objects.equals(caption, dtoNamed.caption);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), caption);
    }
    //</editor-fold>
}
