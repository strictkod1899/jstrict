package ru.strict.db.core.connections;

import java.sql.Connection;

/**
 * Конструктор соединения с базой данных
 */
public interface ICreateConnection<CONNECTION> {

    /**
     * Создать соединение с базой данных
     * @return Соединение с базой данных
     */
    CONNECTION createConnection();
}
