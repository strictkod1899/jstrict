package ru.strict.swing.views.components;

import ru.strict.swing.models.panels.ModelPanel;
import ru.strict.swing.views.IView;

import javax.swing.*;

/**
 * Графическая панель
 */
public class PanelBase<M extends ModelPanel> extends JPanel implements IView<M> {

    private M model;
    private boolean isBuilt;

    @Override
    public PanelBase build(M model) {
        this.model = model;
        setBackground(model.getBackground());

        isBuilt = true;

        return this;
    }

    @Override
    public boolean isBuilt() {
        return isBuilt;
    }

    @Override
    public void setModel(M model) {
        this.model = model;
    }

    @Override
    public M getModel() {
        return model;
    }
}
