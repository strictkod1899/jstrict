package ru.strict.swing.views.dialogs;

import ru.strict.utils.StrictUtilLogger;
import ru.strict.swing.models.dialogs.StrictModelDialogDefault;
import ru.strict.swing.enums.StrictEnumColors;
import ru.strict.swing.views.components.StrictPanelContent;
import ru.strict.swing.views.components.StrictPanelState;

import javax.swing.*;
import java.awt.*;

/**
 * Фрейм диалога с панелью состояния
 */
public class StrictDialogDefault<M extends StrictModelDialogDefault> extends StrictDialog<M> {

    private StrictPanelState panelState;

    private StrictPanelContent panelContent;

    @Override
    public StrictDialogDefault build(M model){
        super.build(model);
        StrictUtilLogger.info(StrictDialogDefault.class, "build - started");
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        setUndecorated(true);
        getContentPane().setBackground(StrictEnumColors.BACKGROUND_FORM.getColor());
        setModalityType(ModalityType.APPLICATION_MODAL);

        // Создание стандартной панели и содержимого
        add(getPanelState(), BorderLayout.NORTH);
        add(getPanelContent(), BorderLayout.CENTER);
        StrictUtilLogger.info(StrictDialogDefault.class, "build - finished");
        return this;
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

    @Override
    public void setModel(M model) {
        super.setModel(model);
    }

    @Override
    public M getModel() {
        return super.getModel();
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
