package ru.strict.template;

/**
 * Pattern 'Observer'. Описание базовой функциональности наблюдаемого класса (класс за которым наблюдает Observer)
 *
 * <pre>
 *     Добавить класс-наблюдатель за текущим объектом
 *     void addObserver(T observer);
 *
 *     Удалить класс-наблюдатель
 *     void removeObserver(T observer);
 * </pre>
 * @param <T> Типы объектов, которые могут наблюдать за данным экземпляром
 */
public interface Observable {
}
