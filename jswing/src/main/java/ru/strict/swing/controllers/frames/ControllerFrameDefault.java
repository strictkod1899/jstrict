package ru.strict.swing.controllers.frames;

import ru.strict.swing.controllers.utils.CommonControllerMethods;
import ru.strict.swing.models.ModelFormDefault;
import ru.strict.swing.views.frames.FrameDefault;

/**
 * Контроллер для управления стандартным окном с панелью состояния
 */
public class ControllerFrameDefault<O extends FrameDefault, M extends ModelFormDefault>
        extends ControllerFrame<O, M> {

    public ControllerFrameDefault(O object, M model) {
        super(object, model);
    }

    @Override
    public O build() {
        getObject().setPanelState(CommonControllerMethods.createPanelState(getModel(), getObject(), this::destroy));
        getObject().setPanelContent(CommonControllerMethods.createPanelContent());
        return super.build();
    }
}
