package ru.strict.db.entities;

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
    /**
     * Получить наименование записи
     * @return
     */
    public String getCaption() {
        return caption;
    }

    /**
     * Установить новое наименование записи
     * @param caption Новое наименование записи
     */
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
        if(obj instanceof StrictEntityNamed) {
            StrictEntityNamed entity = (StrictEntityNamed) obj;
            return super.equals(entity) && caption.equals(entity.getCaption());
        }else
            return false;
    }
    //</editor-fold>
}
