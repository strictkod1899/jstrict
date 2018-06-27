package ru.strict.swing.controllers.views.dialogs;

import ru.strict.swing.controllers.views.ControllerFormBase;
import ru.strict.swing.models.dialogs.ModelDialog;
import ru.strict.swing.views.dialogs.Dialog;

/**
 * Контроллера управления формой диалога
 */
public class ControllerDialog<O extends Dialog, M extends ModelDialog>
        extends ControllerFormBase<O, M> {

    public ControllerDialog(O object, M model){
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
