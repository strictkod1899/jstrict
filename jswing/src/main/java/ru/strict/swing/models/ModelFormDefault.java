package ru.strict.swing.models;

public class ModelFormDefault extends ModelFormBase {

    private boolean visibleTurn;

    private boolean visibleChangeSize;

    private boolean visibleExit;

    private boolean moveForm;

    private int hGap;

    private int vGap;

    private void initialize(){
        hGap = 1;
        vGap = 1;
        visibleTurn = true;
        visibleChangeSize = true;
        visibleExit = true;
        moveForm = true;
    }

    public ModelFormDefault() {
        super();
        initialize();
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

    public boolean isMoveForm() {
        return moveForm;
    }

    public void setMoveForm(boolean moveForm) {
        this.moveForm = moveForm;
    }

    /**
     * Интервал между компонентами
     */
    public int gethGap() {
        return hGap;
    }

    /**
     * Интервал между компонентами
     */
    public void sethGap(int hGap) {
        this.hGap = hGap;
    }

    public int getvGap() {
        return vGap;
    }

    public void setvGap(int vGap) {
        this.vGap = vGap;
    }
}
