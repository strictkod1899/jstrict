package ru.strict.swing.models.panels;

import ru.strict.swing.models.StrictModelViewBase;

import java.awt.*;

/**
 * Модель базовой панели
 */
public class StrictModelPanel extends StrictModelViewBase {

    private boolean scroll;

    private void initDefault(){
        this.scroll = true;
    }

    public StrictModelPanel() {
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
