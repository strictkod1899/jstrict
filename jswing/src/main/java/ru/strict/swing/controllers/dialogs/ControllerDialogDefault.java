package ru.strict.swing.controllers.dialogs;

import ru.strict.swing.controllers.utils.CommonControllerMethods;
import ru.strict.swing.models.ModelFormDefault;
import ru.strict.swing.views.dialogs.DialogDefault;

/**
 * Контроллер для управления стандартным окном диалога с панелью состояния
 */
public class ControllerDialogDefault<O extends DialogDefault, M extends ModelFormDefault>
        extends ControllerDialog<O, M> {

    public ControllerDialogDefault(O object, M model) {
        super(object, model);
    }

    @Override
    public O build() {
        getObject().setPanelState(CommonControllerMethods.createPanelState(getModel(), getObject(), this::destroy));
        getObject().setPanelContent(CommonControllerMethods.createPanelContent());
        return super.build();
    }
}
