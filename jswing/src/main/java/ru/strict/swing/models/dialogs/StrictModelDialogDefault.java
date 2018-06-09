package ru.strict.swing.models.dialogs;

import ru.strict.swing.views.components.StrictPanelContent;
import ru.strict.swing.views.components.StrictPanelState;

import javax.swing.*;
import java.awt.*;

/**
 * Модель стандартной формы диалога
 */
public class StrictModelDialogDefault extends StrictModelDialog {

    private boolean visibleTurn;

    private boolean visibleChangeSize;

    private boolean visibleExit;

    /**
     * Инициализация панели состояния и содержимого
     */
    private void initDefault(){
        visibleTurn = true;
        visibleChangeSize = true;
        visibleExit = true;
    }

    public StrictModelDialogDefault() {
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
