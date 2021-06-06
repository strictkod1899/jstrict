package ru.strict.patterns.view.console;

/**
 * Model-View сервис. Разбивает логику контроллера на отдельные части
 * @param <M> Модель
 * @param <V> Представление
 */
public abstract class BaseUIService<M, V> {
    private M model;
    private V view;

    public BaseUIService(M model) {
        if (model == null) {
            throw new IllegalArgumentException("model is NULL");
        }
        this.model = model;
    }

    public BaseUIService(M model, V view) {
        if (model == null) {
            throw new IllegalArgumentException("model is NULL");
        }
        if (view == null) {
            throw new IllegalArgumentException("view is NULL");
        }
        this.model = model;
        this.view = view;
    }

    public M getModel() {
        return model;
    }

    public V getView() {
        return view;
    }
}
