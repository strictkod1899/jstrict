package ru.strict.db.core.dto;

import ru.strict.utils.StrictUtilHashCode;

/**
 * Именованный entity (содержит строку заголовка)
 */
public class StrictDtoNamed<ID> extends StrictDtoBase<ID> {

    /**
     * Наименование записи
     */
    private String caption;

    //<editor-fold defaultState="collapsed" desc="constructors">
    public StrictDtoNamed() {
        super();
        caption = null;
    }

    public StrictDtoNamed(String caption) {
        super();
        this.caption = caption;
    }

    public StrictDtoNamed(ID id, String caption) {
        super(id);
        this.caption = caption;
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
        return String.format("dto named [%s]: %s", String.valueOf(getId()), caption);
    }

    @Override
    public boolean equals(Object obj){
        if(obj!=null && obj instanceof StrictDtoNamed) {
            StrictDtoNamed object = (StrictDtoNamed) obj;
            return super.equals(object) && caption.equals(object.getCaption());
        }else
            return false;
    }

    @Override
    public int hashCode(){
        int superHashCode = super.hashCode();
        return StrictUtilHashCode.createSubHashCode(superHashCode, caption);
    }
    //</editor-fold>
}
