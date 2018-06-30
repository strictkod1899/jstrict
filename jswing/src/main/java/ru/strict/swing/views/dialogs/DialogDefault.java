package ru.strict.swing.views.dialogs;

import ru.strict.swing.views.components.PanelContent;
import ru.strict.swing.views.components.PanelState;
import ru.strict.utils.UtilLogger;
import ru.strict.swing.models.dialogs.ModelDialogDefault;
import ru.strict.swing.enums.Colors;

import javax.swing.*;
import java.awt.*;

/**
 * Фрейм диалога с панелью состояния
 */
public class DialogDefault<M extends ModelDialogDefault> extends Dialog<M> {

    private PanelState panelState;

    private PanelContent panelContent;

    @Override
    public DialogDefault build(M model){
        super.build(model);
        UtilLogger.info(DialogDefault.class, "build - started");
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        setUndecorated(true);
        getContentPane().setBackground(Colors.BACKGROUND_FORM.getColor());
        setModalityType(ModalityType.APPLICATION_MODAL);

        // Создание стандартной панели и содержимого
        add(getPanelState(), BorderLayout.NORTH);
        add(getPanelContent(), BorderLayout.CENTER);
        UtilLogger.info(DialogDefault.class, "build - finished");
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
