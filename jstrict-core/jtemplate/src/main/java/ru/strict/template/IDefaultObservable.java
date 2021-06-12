package ru.strict.template;

/**
 * Pattern 'Observer'. Описание базовой функциональности наблюдаемого класса (класс за которым наблюдает Observer),
 * который также позволяет оповестить наблюдателей о смене состояния
 *
 * @param <T> Типы объектов, которые могут наблюдать за данным экземпляром
 */
public interface IDefaultObservable<T> extends IObservable<T> {

    /**
     * Оповестить все классы-наблюдателей о смене состояния объекта
     */
    void notifyObservers();
}
