package ru.strict.view.boundary;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import javax.annotation.Nonnull;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public abstract class BaseView<S, M extends BaseViewModel<S>> implements View<S, M>{
    M model;

    public BaseView(@Nonnull M model) {
        this.model = model;
    }

    @Override
    public void refresh(@Nonnull S state) {
        var syncObject = getLockObject(state);

        synchronized (syncObject) {
            model.setState(state);
            refresh();
            model.resetState();
        }
    }

    protected abstract void refresh();

    @Nonnull
    protected abstract Object getLockObject(@Nonnull S state);
}
