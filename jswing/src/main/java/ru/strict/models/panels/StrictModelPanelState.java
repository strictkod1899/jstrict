package ru.strict.models.panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Модель панели состояния
 */
public class StrictModelPanelState extends StrictModelPanel {

    private JPanel panelLeft;

    private JPanel panelRight;

    private Window parent;

    private String title;

    private String pathIcon;

    private ActionListener actionExit;

    private boolean visibleTurn;

    private boolean visibleChangeSize;

    private boolean visibleExit;

    private void initDefault(){
        this.panelLeft = new JPanel();
        this.panelRight = new JPanel();
        parent = null;
        title = "";
        pathIcon = null;
        actionExit = null;
        visibleTurn = true;
        visibleChangeSize = true;
        visibleExit = true;
    }

    public StrictModelPanelState() {
        super();
        initDefault();
    }

    /**
     * Левая область панели состояния
     */
    public JPanel getPanelLeft() {
        return panelLeft;
    }

    /**
     * Левая область панели состояния
     */
    public void setPanelLeft(JPanel panelLeft) {
        this.panelLeft = panelLeft;
    }

    /**
     * Правая область панели состояния
     */
    public JPanel getPanelRight() {
        return panelRight;
    }

    /**
     * Правая область панели состояния
     */
    public void setPanelRight(JPanel panelRight) {
        this.panelRight = panelRight;
    }

    /**
     * Родительское окно, на котором расположена панель состояния
     */
    public Window getParent() {
        return parent;
    }

    /**
     * Родительское окно, на котором расположена панель состояния
     */
    public void setParent(Window parent) {
        this.parent = parent;
    }

    /**
     * Заголовок, отображаемый на панели состояния
     */
    public String getTitle() {
        return title;
    }

    /**
     * Заголовок, отображаемый на панели состояния
     */
    public void setTitle(String title) {
        this.title = title;
    }

    public String getPathIcon() {
        return pathIcon;
    }

    public void setPathIcon(String pathIcon) {
        this.pathIcon = pathIcon;
    }

    /**
     * Событие нажатия кнопки выхода
     */
    public ActionListener getActionExit() {
        return actionExit;
    }

    /**
     * Событие нажатия кнопки выхода
     */
    public void setActionExit(ActionListener actionExit) {
        this.actionExit = actionExit;
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
