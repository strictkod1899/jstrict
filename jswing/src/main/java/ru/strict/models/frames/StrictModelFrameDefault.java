package ru.strict.models.frames;

import javax.swing.*;

/**
 * Модель стандартной формы
 */
public class StrictModelFrameDefault extends StrictModelFrame {

    private boolean visibleTurn;

    private boolean visibleChangeSize;

    private boolean visibleExit;

    private void initDefault(){
        visibleTurn = true;
        visibleChangeSize = true;
        visibleExit = true;
    }

    public StrictModelFrameDefault() {
        super();
        initDefault();
    }

    /**
     * Отображение кнопки "Свернуть"
     */
    public boolean isVisibleTurn() {
        return visibleTurn;
    }

    /**
     * Отображение кнопки "Свернуть"
     */
    public void setVisibleTurn(boolean visibleTurn) {
        this.visibleTurn = visibleTurn;
    }

    /**
     * Отображение кнопки "Изменить размер"
     */
    public boolean isVisibleChangeSize() {
        return visibleChangeSize;
    }

    /**
     * Отображение кнопки "Изменить размер"
     */
    public void setVisibleChangeSize(boolean visibleChangeSize) {
        this.visibleChangeSize = visibleChangeSize;
    }

    /**
     * Отображение кнопки "Закрыть"
     */
    public boolean isVisibleExit() {
        return visibleExit;
    }

    /**
     * Отображение кнопки "Закрыть"
     */
    public void setVisibleExit(boolean visibleExit) {
        this.visibleExit = visibleExit;
    }
}
