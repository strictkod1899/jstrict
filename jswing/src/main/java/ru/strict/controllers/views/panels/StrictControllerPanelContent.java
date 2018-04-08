package ru.strict.controllers.views.panels;

import ru.strict.models.panels.StrictModelPanelContent;
import ru.strict.views.components.StrictPanelContent;

/**
 * Контроллер управления панелью содержимого
 */
public class StrictControllerPanelContent<O extends StrictPanelContent, M extends StrictModelPanelContent>
        extends StrictControllerPanel<O, M> {

    public StrictControllerPanelContent(O panelContent, M model){
        super(panelContent, model);
    }

    @Override
    public O build() {
        return super.build();
    }

    @Override
    public O getObject() {
        return super.getObject();
    }

    @Override
    public void setObject(O object) {
        super.setObject(object);
    }

    @Override
    public M getModel() {
        return super.getModel();
    }

    @Override
    public void setModel(M model) {
        super.setModel(model);
    }
}
