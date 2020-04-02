package ru.strict.patterns;

public interface IDefaultObservable<O> extends IObservable<O> {

    /**
     * Оповестить все классы-наблюдателей о смене состояния объекта
     */
    void notifyObservers();
}
