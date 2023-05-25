package ru.strict.view.boundary;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.annotation.Nonnull;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class BaseViewModel<S> implements ViewModel<S> {
    S state;

    public BaseViewModel() {
        state = getUnknownState();
    }

    public void resetState() {
        this.state = getUnknownState();
    }

    @Nonnull
    protected abstract S getUnknownState();
}
