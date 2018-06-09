package ru.strict.swing.views;

import ru.strict.swing.models.StrictModelViewBase;

/**
 * Базовая реализация графического элемента
 */
public interface StrictViewBase<M extends StrictModelViewBase>{

    /**
     * Конструирование графического элемента
     * @param model Модель по которой создается графический элемент
     * @return Созданный графический элемент
     */
    StrictViewBase build(M model);

    /**
     * Установить новую действующую модель
     * @param model Новая действующая модель
     */
    void setModel(M model);

    /**
     * Получить действующую модель
     * @return
     */
    M getModel();
}
