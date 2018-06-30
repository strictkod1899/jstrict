package ru.strict.swing.models.panels;

import ru.strict.swing.models.ModelViewBase;

/**
 * Модель базовой панели
 */
public class ModelPanel extends ModelViewBase {

    private boolean scroll;

    private void initDefault(){
        this.scroll = true;
    }

    public ModelPanel() {
        super();
        initDefault();
    }

    /**
     * Использовать полосу прокрутки
     */
    public boolean isScroll() {
        return scroll;
    }

    /**
     * Использовать полосу прокрутки
     */
    public void setScroll(boolean scroll) {
        this.scroll = scroll;
    }
}
