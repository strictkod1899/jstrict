package ru.strict.swing;

import ru.strict.swing.components.PanelBase;
import ru.strict.swing.components.PanelState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Фрейм диалога с панелью состояния
 */
public class DialogDefault<M> extends DialogBase<M> {

    private PanelState panelState;
    private PanelBase panelContent;

    private int vGap;
    private int hGap;

    public DialogDefault(M model) {
        super(model);
        panelState = new PanelState(this);
        panelContent = new PanelBase();
        vGap = 1;
        hGap = 1;
    }

    @Override
    public DialogDefault<M> build(){
        super.build();

        if(panelState.getActionExit() == null){
            panelState.setActionExit(this::actionExit);
        }

        panelState.build();
        panelContent.build();

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        setUndecorated(true);
        getContentPane().setBackground(Color.BACKGROUND_FORM.getColor());
        setModalityType(ModalityType.APPLICATION_MODAL);

        setLayout(new FlowLayout(FlowLayout.CENTER, hGap, vGap));

        // Создание стандартной панели и содержимого
        add(panelState, BorderLayout.NORTH);
        add(panelContent, BorderLayout.CENTER);

        return this;
    }

    @Override
    public void setLayout(LayoutManager layout) {
        if(panelContent != null && layout != null) {
            panelContent.setLayout(layout);
        }else{
            super.setLayout(layout);
        }
    }

    @Override
    public void setBackground(java.awt.Color color) {
        if(panelContent != null && color != null) {
            panelContent.setBackground(color);
        }else{
            super.setBackground(color);
        }
    }

    @Override
    public Component add(Component comp) {
        Component result = null;

        if(panelContent != null && comp != null){
            result = panelContent.add(comp);
        }else{
            result = super.add(comp);
        }

        return result;
    }

    @Override
    public java.awt.Color getBackground() {
        java.awt.Color result = null;
        if(panelContent != null) {
            result = panelContent.getBackground();
        }else{
            result = super.getBackground();
        }

        return result;
    }

    @Override
    public void destroy() {
        panelState = null;
        panelContent = null;
        super.destroy();
    }

    private void actionExit(ActionEvent event){
        destroy();
    }

    protected PanelState getPanelState() {
        return panelState;
    }

    protected JPanel getPanelContent() {
        return panelContent;
    }

    protected void setVisibleChangeSize(boolean isVisible){
        getPanelState().setVisibleChangeSize(isVisible);
    }

    protected void setVisibleTurn(boolean isVisible){
        getPanelState().setVisibleTurn(isVisible);
    }

    protected void setVisibleExit(boolean isVisible){
        getPanelState().setVisibleExit(isVisible);
    }

    protected void setMoveForm(boolean isMove){
        getPanelState().setMoveForm(isMove);
    }

    protected void setVisiblePanelState(boolean isVisible){
        panelState.setVisible(isVisible);
    }

    protected void setActionExit(ActionListener action){
        panelState.setActionExit(action);
    }

    protected int getvGap() {
        return vGap;
    }

    protected void setvGap(int vGap) {
        this.vGap = vGap;
    }

    protected int gethGap() {
        return hGap;
    }

    protected void sethGap(int hGap) {
        this.hGap = hGap;
    }
}
