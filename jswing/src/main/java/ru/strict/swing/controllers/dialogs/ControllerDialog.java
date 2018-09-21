package ru.strict.swing.controllers.dialogs;

import ru.strict.swing.controllers.ControllerFormBase;
import ru.strict.swing.models.ModelFormBase;
import ru.strict.swing.views.dialogs.DialogBase;

/**
 * Контроллера управления формой диалога
 */
public class ControllerDialog<O extends DialogBase, M extends ModelFormBase>
        extends ControllerFormBase<O, M> {

    public ControllerDialog(O object, M model){
        super(object, model);
    }
}
