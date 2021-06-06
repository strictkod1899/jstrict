package ru.strict.db.core.connection;

/**
 * Фабрика соединения с базой данных
 */
public interface IConnectionCreator<CONNECTION> {

    /**
     * Создать соединение с базой данных
     *
     * @return Соединение с базой данных
     */
    CONNECTION createConnection();
}
