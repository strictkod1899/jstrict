package ru.strict.template.view.console;

import lombok.Data;

@Data
public class BaseViewModel<T> implements IViewModel<T> {
    private T state;
}
