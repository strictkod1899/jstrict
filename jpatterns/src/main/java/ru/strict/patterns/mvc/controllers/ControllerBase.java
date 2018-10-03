package ru.strict.patterns.mvc.controllers;

import ru.strict.patterns.mvc.views.IView;

public abstract class ControllerBase<V extends IView, M> implements IController {

    private V view;
    private M model;

    public ControllerBase(V view, M model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void destroy() {
        getView().destroy();
        view = null;
        model = null;
    }

    protected V getView() {
        return view;
    }

    protected M getModel() {
        return model;
    }
}
