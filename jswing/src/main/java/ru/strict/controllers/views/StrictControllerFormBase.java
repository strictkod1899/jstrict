package ru.strict.controllers.views;

import ru.strict.models.StrictModelFormBase;
import ru.strict.views.StrictFormBase;

import java.awt.event.ActionEvent;

/**
 * Контроллер базовой формы
 */
public abstract class StrictControllerFormBase<O extends StrictFormBase, M extends StrictModelFormBase>
        extends StrictControllerViewBase<O, M> {

    public StrictControllerFormBase(O object, M model) {
        super(object, model);
    }

    @Override
    public O build() {
        return super.build();
    }

    /**
     * Запуск формы
     */
    public void launch(){
        getObject().launch();
    }

    /**
     * Завершение работы формы
     */
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
