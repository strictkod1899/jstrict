package ru.strict.view.console;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.annotation.Nonnull;

@Getter
@RequiredArgsConstructor
public abstract class BaseConsoleView<S, M extends BaseViewModel<S>>
        extends BaseConsole
        implements View<S, M> {
    protected final M model;

    @Override
    public void refresh(@Nonnull S state) {
        model.setState(state);
        refresh();
    }
}
