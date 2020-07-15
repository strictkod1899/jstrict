package ru.strict.patterns.view.console;

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
