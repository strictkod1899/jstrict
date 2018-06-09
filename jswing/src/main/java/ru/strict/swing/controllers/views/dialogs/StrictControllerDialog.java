package ru.strict.swing.controllers.views.dialogs;

import ru.strict.swing.controllers.views.StrictControllerFormBase;
import ru.strict.swing.models.dialogs.StrictModelDialog;
import ru.strict.swing.views.dialogs.StrictDialog;

/**
 * Контроллера управления формой диалога
 */
public class StrictControllerDialog<O extends StrictDialog, M extends StrictModelDialog>
        extends StrictControllerFormBase<O, M> {

    public StrictControllerDialog(O object, M model){
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
