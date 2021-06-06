package ru.strict.patterns.composite;

/**
 * Pattern 'Composite'. Интерфейс реализации паттерна "Компоновщик"*
 *
 * @param <SOURCE> Элемент композиции. Используется для указания родительского класса композии
 */
public interface IComposite<SOURCE extends IComposite<SOURCE>> {

    /**
     * Добавить элемент в структуру
     */
    void add(SOURCE component);

    /**
     * Удалить элемент из структуры
     */
    void remove(SOURCE component);

    /**
     * Получить дочерний элемент по определенной позиции
     */
    SOURCE getChild(int i);

    /**
     * Получить родительский элемент
     */
    SOURCE getParent();

    /**
     * Установить родительский элемент
     */
    void setParent(SOURCE parent);
}
