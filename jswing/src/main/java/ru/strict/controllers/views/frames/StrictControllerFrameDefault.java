package ru.strict.controllers.views.frames;

import ru.strict.controllers.views.StrictGeneralControllerView;
import ru.strict.models.frames.StrictModelFrameDefault;
import ru.strict.views.frames.StrictFrameDefault;

/**
 * Контроллер для управления стандартным окном с панелью состояния
 */
public class StrictControllerFrameDefault<O extends StrictFrameDefault, M extends StrictModelFrameDefault>
        extends StrictControllerFrame<O, M> {

    private StrictGeneralControllerView generalMethods;

    public StrictControllerFrameDefault(O object, M model) {
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
