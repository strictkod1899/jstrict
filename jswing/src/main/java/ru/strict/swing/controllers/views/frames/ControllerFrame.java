package ru.strict.swing.controllers.views.frames;

import ru.strict.swing.controllers.views.ControllerFormBase;
import ru.strict.swing.models.frames.ModelFrame;
import ru.strict.swing.views.frames.Frame;

/**
 * Контроллер фрейма
 */
public class ControllerFrame<O extends Frame, M extends ModelFrame>
        extends ControllerFormBase<O, M> {

    public ControllerFrame(O object, M model){
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
