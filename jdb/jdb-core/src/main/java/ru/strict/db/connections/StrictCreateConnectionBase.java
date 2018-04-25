package ru.strict.db.connections;

/**
 * Конструктор соединения с базой данных
 * @param <SOURCE> Тип источника подключения к базе данных (например, DataSource, StrictConnectionInfo и др.)
 */
public abstract class StrictCreateConnectionBase<SOURCE> implements StrictCreateConnectionAny {

    /**
     * Источник подключения к базе данных (используется для получения объекта Connection),
     * например, DataSource, StringConnectionInfo и др.
     */
    private SOURCE connectionSource;

    public StrictCreateConnectionBase(SOURCE connectionSource) {
        this.connectionSource = connectionSource;
    }

    public SOURCE getConnectionSource() {
        return connectionSource;
    }
}
