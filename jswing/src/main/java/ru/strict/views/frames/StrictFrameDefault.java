package ru.strict.views.frames;

import ru.strict.utils.StrictUtilLogger;
import ru.strict.models.frames.StrictModelFrameDefault;
import ru.strict.enums.StrictEnumColors;
import ru.strict.views.components.StrictPanelContent;
import ru.strict.views.components.StrictPanelState;

import javax.swing.*;
import java.awt.*;

/**
 * Класс определяет окно с панелью состояния
 */
public class StrictFrameDefault<M extends StrictModelFrameDefault> extends StrictFrame<M> {

    private StrictPanelState panelState;

    private StrictPanelContent panelContent;

    @Override
    public StrictFrame build(M model){
        super.build(model);
        StrictUtilLogger.info(StrictFrameDefault.class, "build - started");
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        setUndecorated(true);
        getContentPane().setBackground(StrictEnumColors.BACKGROUND_FORM.getColor());

        // Создание стандартной панели и содержимого
        add(panelState, BorderLayout.NORTH);
        add(panelContent, BorderLayout.CENTER);
        StrictUtilLogger.info(StrictFrameDefault.class, "build - finished");
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
    public StrictPanelState getPanelState(){
        return panelState;
    }

    /**
     * Установить панель состояния
     */
    public void setPanelState(StrictPanelState panelState){
        this.panelState = panelState;
    }

    /**
     * Получить панель содержимого
     */
    public StrictPanelContent getPanelContent(){
        return panelContent;
    }

    /**
     * Установить панель содержимого
     */
    public void setPanelContent(StrictPanelContent panelContent){
        this.panelContent = panelContent;
    }
}
