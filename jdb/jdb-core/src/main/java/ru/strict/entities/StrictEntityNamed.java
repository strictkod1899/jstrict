package ru.strict.entities;

/**
 * Класс описывает именованную entity
 */
public class StrictEntityNamed extends StrictEntityBase<Long> {

    /**
     * Наименование записи
     */
    private String caption;

    public StrictEntityNamed() {
        super();
        caption = "";
    }

    public StrictEntityNamed(String caption) {
        super();
        this.caption = caption;
    }

    public StrictEntityNamed(Long id, String caption) {
        super(id);
        this.caption = caption;
    }

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

    @Override
    public String toString(){
        return caption;
    }
}
