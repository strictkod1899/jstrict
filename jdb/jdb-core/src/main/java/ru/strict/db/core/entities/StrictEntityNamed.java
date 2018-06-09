package ru.strict.db.core.entities;

import ru.strict.utils.StrictUtilHashCode;

/**
 * Именованный entity (содержит строку заголовка)
 */
public class StrictEntityNamed<ID> extends StrictEntityBase<ID> {

    /**
     * Наименование записи
     */
    private String caption;

    //<editor-fold defaultState="collapsed" desc="constructors">
    public StrictEntityNamed() {
        super();
        caption = null;
    }

    public StrictEntityNamed(String caption) {
        super();
        this.caption = caption;
    }

    public StrictEntityNamed(ID id, String caption) {
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
        return String.format("entity named [%s]: %s", String.valueOf(getId()), caption);
    }

    @Override
    public boolean equals(Object obj){
        if(obj!=null && obj instanceof StrictEntityNamed) {
            StrictEntityNamed object = (StrictEntityNamed) obj;
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
