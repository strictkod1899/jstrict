package ru.strict.views.components;

import ru.strict.models.panels.StrictModelPanelContent;

/**
 * Графическая панель содержимого
 */
public class StrictPanelContent<M extends StrictModelPanelContent> extends StrictPanel<M> {

    @Override
    public StrictPanel build(M model) {
        return super.build(model);
    }

    @Override
    public void setModel(M model) {
        super.setModel(model);
    }

    @Override
    public M getModel() {
        return super.getModel();
    }

}
