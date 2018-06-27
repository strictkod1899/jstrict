package ru.strict.swing.views.components;

import ru.strict.swing.models.panels.ModelPanelContent;

/**
 * Графическая панель содержимого
 */
public class PanelContent<M extends ModelPanelContent> extends Panel<M> {

    @Override
    public Panel build(M model) {
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
