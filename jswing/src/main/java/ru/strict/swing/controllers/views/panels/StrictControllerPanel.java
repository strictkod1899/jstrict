package ru.strict.swing.controllers.views.panels;

import ru.strict.swing.controllers.views.StrictControllerViewBase;
import ru.strict.swing.models.panels.StrictModelPanel;
import ru.strict.swing.views.components.StrictPanel;

/**
 * Контроллер графической панели
 */
public class StrictControllerPanel<O extends StrictPanel, M extends StrictModelPanel>
        extends StrictControllerViewBase<O, M> {

    public StrictControllerPanel(O object, M model){
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
