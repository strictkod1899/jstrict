package ru.strict.swing.views.components;

import ru.strict.swing.models.panels.ModelPanelContent;

/**
 * Графическая панель содержимого
 */
public class PanelContent<M extends ModelPanelContent> extends PanelBase<M> {

    @Override
    public PanelBase build(M model) {
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
