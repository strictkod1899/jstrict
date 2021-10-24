package ru.strict.view.console;

import lombok.Data;

@Data
public class BaseViewModel<T> implements ViewModel<T> {
    private T state;
}
