package ru.strict.db.dto;

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
        return String.format("dto[%s]: %s", String.valueOf(getId()), caption);
    }

    @Override
    public boolean equals(Object obj){
        if(obj instanceof StrictDtoNamed) {
            StrictDtoNamed entity = (StrictDtoNamed) obj;
            return super.equals(entity) && caption.equals(entity.getCaption());
        }else
            return false;
    }
    //</editor-fold>
}
