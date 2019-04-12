package ru.strict.db.hibernate.entities;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Objects;

/**
 * Именованный entity (содержит строку заголовка)
 */
@MappedSuperclass
public abstract class EntityNamed<ID> extends EntityBase<ID> {

    /**
     * Наименование записи
     */
    @Column(name = "caption", nullable = false)
    private String caption;

    //<editor-fold defaultState="collapsed" desc="constructors">
    private void initialize(String caption){
        if(caption == null) {
            throw new IllegalArgumentException("caption is NULL");
        }

        this.caption = caption;
    }

    public EntityNamed() {
        super();
        caption = null;
    }

    public EntityNamed(String caption) {
        super();
        initialize(caption);
    }

    public EntityNamed(ID id, String caption) {
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
        return String.format("entity named [%s]: %s", String.valueOf(getId()), caption);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        EntityNamed<ID> that = (EntityNamed<ID>) o;
        return Objects.equals(caption, that.caption);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), caption);
    }
    //</editor-fold>
}
