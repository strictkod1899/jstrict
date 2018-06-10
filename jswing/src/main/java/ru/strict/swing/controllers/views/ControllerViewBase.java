package ru.strict.swing.controllers.views;

import ru.strict.swing.controllers.ControllerBase;
import ru.strict.swing.models.ModelViewBase;
import ru.strict.swing.views.ViewBase;

/**
 * Контроллер управляющий графическим элементом
 */
public abstract class ControllerViewBase<O extends ViewBase, M extends ModelViewBase>
        extends ControllerBase<O, M> {

    public ControllerViewBase(O object, M model){
        super(object, model);
    }

    //TODO: Не известно как будет работать установка размеров для панели (JPanel)
    @Override
    public O build(){
        if(getModel().getMaxWidth()>0 && getModel().getWidth()>getModel().getMaxWidth())
            getModel().setWidth(getModel().getMaxWidth());

        if(getModel().getMaxHeight()>0 && getModel().getHeight()>getModel().getMaxHeight())
            getModel().setHeight(getModel().getMaxHeight());

        if(getModel().getMinWidth()>0 && getModel().getWidth()<getModel().getMinWidth())
            getModel().setWidth(getModel().getMinWidth());

        if(getModel().getMinHeight()>0 && getModel().getHeight()<getModel().getMinHeight())
            getModel().setHeight(getModel().getMinHeight());

        return (O) getObject().build(getModel());
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
