package ru.strict.swing.views.dialogs;

import ru.strict.swing.models.ModelFormDefault;
import ru.strict.swing.views.utils.CommonViewMethods;
import ru.strict.swing.views.components.PanelContent;
import ru.strict.swing.views.components.PanelState;
import ru.strict.utils.UtilLogger;
import ru.strict.swing.enums.Colors;

import javax.swing.*;
import java.awt.*;

/**
 * Фрейм диалога с панелью состояния
 */
public class DialogDefault<M extends ModelFormDefault> extends DialogBase<M> {

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

        setLayout(new FlowLayout(FlowLayout.CENTER, model.gethGap(), model.getvGap()));

        // Создание стандартной панели и содержимого
        add(panelState, BorderLayout.NORTH);
        add(panelContent, BorderLayout.CENTER);
        UtilLogger.info(DialogDefault.class, "build - finished");
        return this;
    }

    @Override
    public void setLayout(LayoutManager layout) {
        if(panelContent != null) {
            CommonViewMethods.setLayout(panelContent, layout);
        }else{
            super.setLayout(layout);
        }
    }

    @Override
    public void setBackground(Color color) {
        if(panelContent != null) {
            CommonViewMethods.setBackground(panelContent, color);
        }else{
            super.setBackground(color);
        }
    }

    @Override
    public Component add(Component comp) {
        Component result = null;

        if(comp != null){
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

    protected PanelState getPanelState() {
        return panelState;
    }

    protected PanelContent getPanelContent() {
        return panelContent;
    }

    public void setPanelState(PanelState panelState) {
        this.panelState = panelState;
    }

    public void setPanelContent(PanelContent panelContent) {
        this.panelContent = panelContent;
    }
}
