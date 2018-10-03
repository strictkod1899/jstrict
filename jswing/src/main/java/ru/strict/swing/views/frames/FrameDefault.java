package ru.strict.swing.views.frames;

import ru.strict.swing.views.components.PanelBase;
import ru.strict.swing.views.components.PanelState;
import ru.strict.swing.enums.Colors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Класс определяет окно с панелью состояния
 */
public class FrameDefault<M> extends FrameBase<M> {

    private PanelState panelState;
    private PanelBase panelContent;

    public FrameDefault(M model) {
        super(model);
        panelState = new PanelState(this);
        panelContent = new PanelBase();
    }

    @Override
    public FrameDefault<M> build(){
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
        getContentPane().setBackground(Colors.BACKGROUND_FORM.getColor());

        setLayout(new FlowLayout(FlowLayout.CENTER, 1, 1));

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
    public void setBackground(Color color) {
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
    public Color getBackground() {
        Color result = null;
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
        panelState.setVisibleChangeSize(isVisible);
    }

    protected void setVisibleTurn(boolean isVisible){
        panelState.setVisibleTurn(isVisible);
    }

    protected void setVisibleExit(boolean isVisible){
        panelState.setVisibleExit(isVisible);
    }

    protected void setMoveForm(boolean isMove){
        panelState.setMoveForm(isMove);
    }

    protected void setVisiblePanelState(boolean isVisible){
        panelState.setVisible(isVisible);
    }

    protected void setActionExit(ActionListener action){
        panelState.setActionExit(action);
    }
}
