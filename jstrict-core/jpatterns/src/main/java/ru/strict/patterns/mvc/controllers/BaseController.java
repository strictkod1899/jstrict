package ru.strict.patterns.mvc.controllers;

import ru.strict.patterns.mvc.views.IView;

public abstract class BaseController<V extends IView, M> implements IController {

    private V view;
    private M model;

    public BaseController() {
    }

    public BaseController(V view, M model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void launch() {
        view.launch();
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

    protected void setView(V view) {
        this.view = view;
    }

    protected void setModel(M model) {
        this.model = model;
    }
}
