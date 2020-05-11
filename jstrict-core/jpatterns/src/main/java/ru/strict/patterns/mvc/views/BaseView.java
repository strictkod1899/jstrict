package ru.strict.patterns.mvc.views;

public abstract class BaseView<M> implements IView {

    private M model;

    public BaseView(M model) {
        this.model = model;
    }

    protected M getModel() {
        return model;
    }

    @Override
    public void destroy() {
        model = null;
    }
}
