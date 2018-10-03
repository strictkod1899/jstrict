package ru.strict.swing.views.frames;

import ru.strict.swing.views.components.PanelBase;
import ru.strict.swing.views.components.PanelState;
import ru.strict.swing.enums.Colors;

import javax.swing.*;
import java.awt.*;

/**
 * Класс определяет окно с панелью состояния
 */
public class FrameDefault<M> extends FrameBase<M> {

    private PanelState panelState;
    private JPanel panelContent;

    public FrameDefault(M model) {
        super(model);
        panelState = new PanelState().build();
        panelContent = new PanelBase().build();
    }

    @Override
    public FrameDefault<M> build(){
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

    protected PanelState getPanelState() {
        return panelState;
    }

    protected JPanel getPanelContent() {
        return panelContent;
    }
}
