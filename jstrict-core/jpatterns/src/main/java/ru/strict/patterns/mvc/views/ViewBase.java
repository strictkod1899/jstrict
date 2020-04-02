package ru.strict.patterns.mvc.views;

public abstract class ViewBase<M> implements IView {

    private M model;

    public ViewBase(M model) {
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
