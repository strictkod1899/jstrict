package ru.strict.db.core.dto;

import ru.strict.utils.UtilHashCode;

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
            throw new NullPointerException("caption is NULL");
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
        if(caption == null) {
            throw new NullPointerException("caption is NULL");
        }

        this.caption = caption;
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Base override">
    @Override
    public String toString(){
        return String.format("dto named [%s]: %s", String.valueOf(getId()), caption);
    }

    @Override
    public boolean equals(Object obj){
        if(obj!=null && obj instanceof DtoNamed) {
            DtoNamed object = (DtoNamed) obj;
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
