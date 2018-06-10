package ru.strict.swing.controllers.views.panels;

import ru.strict.swing.models.panels.ModelPanelContent;
import ru.strict.swing.views.components.PanelContent;

/**
 * Контроллер управления панелью содержимого
 */
public class ControllerPanelContent<O extends PanelContent, M extends ModelPanelContent>
        extends ControllerPanel<O, M> {

    public ControllerPanelContent(O panelContent, M model){
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
