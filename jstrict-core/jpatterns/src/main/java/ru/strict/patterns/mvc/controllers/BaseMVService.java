package ru.strict.patterns.mvc.controllers;

/**
 * Model-View сервис. Разбивает логику контроллера на отдельные части
 *
 * @param <M> Модель
 * @param <V> Представление
 */
public abstract class BaseMVService<M, V> {
    private M model;
    private V view;

    public BaseMVService(M model) {
        if (model == null) {
            throw new IllegalArgumentException("model is NULL");
        }
        this.model = model;
    }

    public BaseMVService(M model, V view) {
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