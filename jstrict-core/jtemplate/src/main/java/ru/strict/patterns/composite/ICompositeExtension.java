package ru.strict.patterns.composite;

/**
 * Pattern 'Composite'. Интерфейс расширенной реализации паттерна "Компоновщик"
 *
 * @param <SOURCE> Элемент композиции. Используется для указания родительского класса композии и дочерних классов
 * @param <COLLECTION> Источник хранения дочерних элементов, например, массив, Collection, List и др.
 */
public interface ICompositeExtension<COLLECTION, SOURCE extends IComposite<SOURCE>> extends IComposite<SOURCE> {

    /**
     * Заменить элемент на определенной позиции новым
     */
    void set(int i, SOURCE component);

    /**
     * Получить все дочерние элементы
     */
    COLLECTION getChilds();

    /**
     * Количество хранимых элементов
     */
    int size();

    /**
     * Список хранимых элементов пустой?
     */
    boolean isEmpty();

    /**
     * Содержит ли компонент указанный элемент
     */
    boolean contains(SOURCE component);

    /**
     * Очистить список хранимых элементов
     */
    void clear();
}
