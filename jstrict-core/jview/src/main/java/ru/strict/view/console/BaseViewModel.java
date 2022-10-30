package ru.strict.view.console;

import lombok.Data;
import ru.strict.view.boundary.ViewModel;

@Data
public class BaseViewModel<T> implements ViewModel<T> {
    private T state;
}
