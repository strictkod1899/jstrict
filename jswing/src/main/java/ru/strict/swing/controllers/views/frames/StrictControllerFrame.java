package ru.strict.swing.controllers.views.frames;

import ru.strict.swing.controllers.views.StrictControllerFormBase;
import ru.strict.swing.models.frames.StrictModelFrame;
import ru.strict.swing.views.frames.StrictFrame;

/**
 * Контроллер фрейма
 */
public class StrictControllerFrame<O extends StrictFrame, M extends StrictModelFrame>
        extends StrictControllerFormBase<O, M> {

    public StrictControllerFrame(O object, M model){
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
