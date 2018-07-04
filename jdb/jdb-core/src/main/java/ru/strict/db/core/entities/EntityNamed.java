package ru.strict.db.core.entities;

import ru.strict.utils.UtilHashCode;

/**
 * Именованный entity (содержит строку заголовка)
 */
public abstract class EntityNamed<ID> extends EntityBase<ID> {

    /**
     * Наименование записи
     */
    private String caption;

    //<editor-fold defaultState="collapsed" desc="constructors">
    public EntityNamed() {
        super();
        caption = null;
    }

    public EntityNamed(String caption) {
        super();
        this.caption = caption;
    }

    public EntityNamed(ID id, String caption) {
        super(id);
        this.caption = caption;
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Get/Set">
    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        if(caption == null) {
            throw new NullPointerException();
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
    	int superHashCode = super.hashCode();
        return UtilHashCode.createSubHashCode(superHashCode, caption);
    }
    //</editor-fold>
}
