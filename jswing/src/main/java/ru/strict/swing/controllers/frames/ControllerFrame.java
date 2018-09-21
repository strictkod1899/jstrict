package ru.strict.swing.controllers.frames;

import ru.strict.swing.controllers.ControllerFormBase;
import ru.strict.swing.models.ModelFormBase;
import ru.strict.swing.views.frames.FrameBase;

/**
 * Контроллер фрейма
 */
public class ControllerFrame<O extends FrameBase, M extends ModelFormBase>
        extends ControllerFormBase<O, M> {

    public ControllerFrame(O object, M model){
        super(object, model);
    }
}
