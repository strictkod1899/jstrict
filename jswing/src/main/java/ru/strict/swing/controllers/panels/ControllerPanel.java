package ru.strict.swing.controllers.panels;

import ru.strict.swing.controllers.ControllerViewBase;
import ru.strict.swing.models.panels.ModelPanel;
import ru.strict.swing.views.components.PanelBase;

/**
 * Контроллер графической панели
 */
public class ControllerPanel<O extends PanelBase, M extends ModelPanel>
        extends ControllerViewBase<O, M> {

    public ControllerPanel(O object, M model){
        super(object, model);
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
