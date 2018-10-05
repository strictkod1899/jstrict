package ru.strict.db.hibernate.entities;



import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Objects;
import java.util.UUID;

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
            throw new NullPointerException("caption is NULL");
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
        if(caption == null) {
            throw new NullPointerException("caption is NULL");
        }

        this.caption = caption;
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Base override">
    @Override
    public String toString(){
        return String.format("entity named [%s]: %s", String.valueOf(getId()), caption);
    }

    @Override
    public boolean equals(Object obj){
        if(obj!=null && obj instanceof EntityNamed) {
            EntityNamed object = (EntityNamed) obj;
            return super.equals(object) && caption.equals(object.getCaption());
        }else
            return false;
    }

    @Override
    public int hashCode(){
        return Objects.hash(getId(), caption);
    }
    //</editor-fold>
}
