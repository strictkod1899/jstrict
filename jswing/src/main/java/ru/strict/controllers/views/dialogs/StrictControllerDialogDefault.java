package ru.strict.controllers.views.dialogs;

import ru.strict.controllers.views.StrictGeneralControllerView;
import ru.strict.models.dialogs.StrictModelDialogDefault;
import ru.strict.views.dialogs.StrictDialogDefault;

/**
 * Контроллер для управления стандартным окном диалога с панелью состояния
 */
public class StrictControllerDialogDefault<O extends StrictDialogDefault, M extends StrictModelDialogDefault>
        extends StrictControllerDialog<O, M> {

    private StrictGeneralControllerView generalMethods;

    public StrictControllerDialogDefault(O object, M model) {
        super(object, model);
    }

    @Override
    public O build() {
        generalMethods = new StrictGeneralControllerView(this);
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

    public StrictGeneralControllerView getGeneralMethods() {
        return generalMethods;
    }
}
