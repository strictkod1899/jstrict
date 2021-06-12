package ru.strict.template.view.console;

import javax.annotation.Nonnull;

/**
 * Модель mvc. Хранит текущее состояние для взаимодействия с view
 * @param <T> state (состояние) для view
 */
public interface IViewModel<T> {
    void setState(@Nonnull T state);
    @Nonnull T getState();
}
