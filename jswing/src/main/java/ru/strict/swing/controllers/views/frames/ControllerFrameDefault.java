package ru.strict.swing.controllers.views.frames;

import ru.strict.swing.controllers.views.GeneralControllerView;
import ru.strict.swing.models.frames.ModelFrameDefault;
import ru.strict.swing.views.frames.FrameDefault;

/**
 * Контроллер для управления стандартным окном с панелью состояния
 */
public class ControllerFrameDefault<O extends FrameDefault, M extends ModelFrameDefault>
        extends ControllerFrame<O, M> {

    private GeneralControllerView generalMethods;

    public ControllerFrameDefault(O object, M model) {
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
