package ru.strict.db.core.connections;

import java.sql.Connection;

/**
 * Конструктор соединения с базой данных
 */
public interface StrictCreateConnectionAny {

    /**
     * Создать соединение с базой данных
     * @return Соединение с базой данных
     */
    Connection createConnection();
}
