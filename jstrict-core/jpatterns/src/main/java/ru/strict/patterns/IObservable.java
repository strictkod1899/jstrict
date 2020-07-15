package ru.strict.patterns;

/**
 * Pattern 'Observer'. Описание базовой функциональности наблюдаемого класса (класс за которым наблюдает Observer)
 *
 * @param <T> Типы объектов, которые могут наблюдать за данным экземпляром
 */
public interface IObservable<T> {

    /**
     * Добавить класс-наблюдатель за текущим объектом
     */
    void addObserver(T observer);

    /**
     * Удалить класс-наблюдатель
     */
    void removeObserver(T observer);
}
