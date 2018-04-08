package ru.strict.views.components;

import ru.strict.models.panels.StrictModelPanel;
import ru.strict.views.StrictViewBase;

import javax.swing.*;

/**
 * Графическая панель
 */
public class StrictPanel<M extends StrictModelPanel> extends JPanel implements StrictViewBase<M> {

    private M model;

    @Override
    public StrictPanel build(M model) {
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
