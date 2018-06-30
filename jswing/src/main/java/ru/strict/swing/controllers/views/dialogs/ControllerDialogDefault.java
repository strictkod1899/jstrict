package ru.strict.swing.controllers.views.dialogs;

import ru.strict.swing.controllers.views.GeneralControllerView;
import ru.strict.swing.models.dialogs.ModelDialogDefault;
import ru.strict.swing.views.dialogs.DialogDefault;

/**
 * Контроллер для управления стандартным окном диалога с панелью состояния
 */
public class ControllerDialogDefault<O extends DialogDefault, M extends ModelDialogDefault>
        extends ControllerDialog<O, M> {

    private GeneralControllerView generalMethods;

    public ControllerDialogDefault(O object, M model) {
        super(object, model);
    }

    @Override
    public O build() {
        generalMethods = new GeneralControllerView(this);
        generalMethods.initControllerPanelState();
        generalMethods.initControllerPanelContent();
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

    public GeneralControllerView getGeneralMethods() {
        return generalMethods;
    }
}
