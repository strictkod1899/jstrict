package ru.strict.swing.controllers;

import ru.strict.swing.models.ModelFormBase;
import ru.strict.swing.views.IForm;

import java.awt.event.ActionEvent;

/**
 * Контроллер базовой формы
 */
public abstract class ControllerFormBase<O extends IForm, M extends ModelFormBase>
        extends ControllerViewBase<O, M>
        implements IControllerForm<O, M> {

    public ControllerFormBase(O object, M model) {
        super(object, model);
    }

    /**
     * Запуск формы
     */
    @Override
    public void launch(){
        if(!isBuilt()){
            build();
        }

        getObject().launch();
    }

    /**
     * Завершение работы формы
     */
    @Override
    public void destroy() {
        getObject().destroy();
    }

    /**
     * Событие завершения работы формы
     */
    public void destroy(ActionEvent event) {
        destroy();
    }

    /**
     * Обновление состояния формы
     */
    @Override
    public void updateForm() {
        getObject().updateView();
    }

    @Override
    public void setModel(M model) {
        super.setModel(model);
    }

    @Override
    public M getModel() {
        return super.getModel();
    }

    @Override
    public void setObject(O object) {
        super.setObject(object);
    }

    @Override
    public O getObject() {
        return super.getObject();
    }
}
