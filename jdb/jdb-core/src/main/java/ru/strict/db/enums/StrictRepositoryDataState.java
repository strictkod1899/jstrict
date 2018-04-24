package ru.strict.db.enums;

public enum StrictRepositoryDataState {
    /**
     * Кэшированные данные в актуальном состоянии
     */
    ACTUAL,
    /**
     * В базу данных была добавлена новая запись
     */
    CREATED,
    /**
     * Некоторые записи были изменены
     */
    UPDATED,
    /**
     * Кэшированные данные не инициализированы
     */
    NONE
}