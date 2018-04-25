package ru.strict.db.connections;

import java.sql.Connection;

/**
 * Конструктор соединения с базой данных
 */
public interface StrictCreateConnectionAny {
    Connection createConnection();
}
