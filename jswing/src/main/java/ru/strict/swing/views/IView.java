package ru.strict.swing.views;

import ru.strict.swing.models.ModelViewBase;

/**
 * Базовая реализация графического элемента
 */
public interface IView<M extends ModelViewBase>{

    /**
     * Конструирование графического элемента
     * @param model Модель по которой создается графический элемент
     * @return Созданный графический элемент
     */
    IView build(M model);

    /**
     * Определить, состояние сборки объекта: собран он или нет
     * @return
     */
    boolean isBuilt();

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
