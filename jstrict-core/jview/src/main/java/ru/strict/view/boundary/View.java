package ru.strict.view.boundary;

import javax.annotation.Nonnull;

/**
 * View в стиле MVC.
 * Принимает модель, которая хранит текущее состояние системы и при вызове метода refresh обновляет view
 * @param <S> state (состояние), которое хранит model для взаимодействия с view
 * @param <M> модель mvc
 */
public interface View<S, M extends ViewModel<S>> {
    void refresh();
    void refresh(@Nonnull S state);
    @Nonnull M getModel();
}
