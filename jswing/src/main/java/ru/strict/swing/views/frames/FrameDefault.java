package ru.strict.swing.views.frames;

import ru.strict.swing.views.components.PanelContent;
import ru.strict.swing.views.components.PanelState;
import ru.strict.utils.UtilLogger;
import ru.strict.swing.models.frames.ModelFrameDefault;
import ru.strict.swing.enums.Colors;

import javax.swing.*;
import java.awt.*;

/**
 * Класс определяет окно с панелью состояния
 */
public class FrameDefault<M extends ModelFrameDefault> extends Frame<M> {

    private PanelState panelState;

    private PanelContent panelContent;

    @Override
    public Frame build(M model){
        super.build(model);
        UtilLogger.info(FrameDefault.class, "build - started");
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        setUndecorated(true);
        getContentPane().setBackground(Colors.BACKGROUND_FORM.getColor());

        // Создание стандартной панели и содержимого
        add(panelState, BorderLayout.NORTH);
        add(panelContent, BorderLayout.CENTER);
        UtilLogger.info(FrameDefault.class, "build - finished");
        return this;
    }

    @Override
    public void setModel(M model) {
        super.setModel(model);
    }

    @Override
    public M getModel() {
        return super.getModel();
    }

    @Override
    public void setLayout(LayoutManager manager) {
        try {
            getGeneralMethods().setLayout(manager);
        }catch(java.lang.NullPointerException ex) {
            super.setLayout(manager);
        }
    }

    @Override
    public Component add(Component comp) {
        return getGeneralMethods().add(comp);
    }

    @Override
    public void setBackground(Color bgColor) {
        try {
            getGeneralMethods().setBackground(bgColor);
        }catch(java.lang.NullPointerException ex) {
            super.setBackground(bgColor);
        }
    }

    /**
     * Получить панель состояния
     */
    public PanelState getPanelState(){
        return panelState;
    }

    /**
     * Установить панель состояния
     */
    public void setPanelState(PanelState panelState){
        this.panelState = panelState;
    }

    /**
     * Получить панель содержимого
     */
    public PanelContent getPanelContent(){
        return panelContent;
    }

    /**
     * Установить панель содержимого
     */
    public void setPanelContent(PanelContent panelContent){
        this.panelContent = panelContent;
    }
}
