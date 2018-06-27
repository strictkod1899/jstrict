package ru.strict.swing.views.components;

import ru.strict.swing.models.panels.ModelPanel;
import ru.strict.swing.views.ViewBase;

import javax.swing.*;

/**
 * Графическая панель
 */
public class Panel<M extends ModelPanel> extends JPanel implements ViewBase<M> {

    private M model;

    @Override
    public Panel build(M model) {
        setModel(model);
        setBackground(model.getBackground());
        return this;
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
