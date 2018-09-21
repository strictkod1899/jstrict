package ru.strict.swing.controllers;

import ru.strict.swing.models.ModelViewBase;
import ru.strict.swing.views.IView;

/**
 * Контроллер управляющий графическим элементом
 */
public abstract class ControllerViewBase<O extends IView, M extends ModelViewBase>
        extends ControllerBase<O, M> {

    public ControllerViewBase(O object, M model){
        super(object, model);
    }

    //TODO: Не известно как будет работать установка размеров для панели (JPanel)
    @Override
    public O build(){
        if(getModel().getMaxWidth()>0 && getModel().getWidth()>getModel().getMaxWidth()) {
            getModel().setWidth(getModel().getMaxWidth());
        }

        if(getModel().getMaxHeight()>0 && getModel().getHeight()>getModel().getMaxHeight()) {
            getModel().setHeight(getModel().getMaxHeight());
        }

        if(getModel().getMinWidth()>0 && getModel().getWidth()<getModel().getMinWidth()) {
            getModel().setWidth(getModel().getMinWidth());
        }

        if(getModel().getMinHeight()>0 && getModel().getHeight()<getModel().getMinHeight()) {
            getModel().setHeight(getModel().getMinHeight());
        }

        return (O) getObject().build(getModel());
    }

    protected boolean isBuilt() {
        return getObject().isBuilt();
    }
}
